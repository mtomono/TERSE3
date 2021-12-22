/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package shapeCollection;

import collection.P;
import static collection.PrimitiveArrayWrap.wrap;
import collection.Scale;
import collection.TList;
import static collection.c.a2l;
import debug.Te;
import iterator.TIterator;
import static java.lang.Math.signum;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Cubes. calculate cubes of 1 edge of movement 1. calculate the starting cube
 * 2. project movement to each axis (calculate the hits) 3. normalize all the
 * projection on to the same line (this makes all the hits comparable) depend
 * upon the direction of hits, direction of hit can be plus or minus. 4. sort
 * normalized hits 5. from the starting cube, walk through cubes along the hits.
 *
 * @author mtomono
 */
public class Cubes {
    final public List<Double> zero;
    final public List<Double> factor;
    final public double norm;

    public Cubes(List<Double> zero, List<Double> factor, double norm) {
        this.zero = zero;
        this.factor = factor;
        this.norm = norm;
    }

    public Cubes(List<Double> zero, List<Double> factor) {
        this(zero, factor, 1d);
    }

    public TList<List<Integer>> go(List<Double> from, List<Double> to) {
        return toCubes(zero, factor, from, to, norm);
    }
    static Integer[] intArray = new Integer[]{0};

    static TList<Integer> toStartingCube(TList<ScaledAxis> axis) {
        return axis.map(a->a.start());
    }

    static Integer[] toStartingCubeArr(TList<ScaledAxis> axis) {
        return toStartingCube(axis).toArray(intArray);
    }

    static TList<ScaledAxis> toAxis(List<Double> zero, List<Double> factor, List<Double> from, List<Double> to) {
        assert TList.sof(zero, factor, from, to).map(l->l.size()).isUniform();
        return TList.sof(zero, factor, from, to).transpose(l->l).map(l->new ScaledAxis(l.get(0), l.get(1), l.get(2), l.get(3))).sfix();
    }

    static TList<ScaledAxis> normalize(TList<ScaledAxis> scales, double normalSize) {
        return scales.map(scale->scale.fit(0d, normalSize)).sfix();
    }

    static TList<TList<P<Double, Hit>>> hits(TList<ScaledAxis> normalized, TList<ScaledAxis> original) {
        return normalized.pair(new Scale(), (scale, i)->TList.set(scale).map(d->P.p(d, new Hit(i, (int) original.get(i).dir()))).sfix()).sfix();
    }

    static TList<P<Double, Hit>> mergeAndSortHits(TList<TList<P<Double, Hit>>> list) {
        return list.flatMap(l->l).sortTo((a, b)->(int)signum(a.l()-b.l())).sfix();
    }

    static TList<List<Integer>> hitToCube(List<Integer> start, TList<P<Double, Hit>> hits) {
        return hits.accum(start, (a, b)->b.r().hit(a)).sfix();
    }

    static TList<Integer[]> hitToCubeArr(TList<P<Double, Hit>> hits, Integer[] start) {
        return hits.accum(start, (a, b)->b.r().hit(a));
    }

    static public TList<List<Integer>> toCubes(List<Double> zero, List<Double> factor, List<Double> from, List<Double> to, double norm) {
        TList<ScaledAxis> axis = toAxis(zero, factor, from, to);
        TList<Integer> initial = toStartingCube(axis);
        return hitToCube(initial, mergeAndSortHits(hits(normalize(axis, norm), axis))).map(c->TList.set(c).sfix());
    }

    static public TList<Integer> add(TList<Integer> cube, List<Integer> vector) {
        return cube.pair(vector, (a, b)->a+b);
    }

    static public class Hit {
        int axis;
        int add;

        Hit(int axis, int add) {
            this.axis = axis;
            this.add = add;
        }

        List<Integer> hit(List<Integer> x) {
            List<Integer> retval = a2l(x.toArray(Cubes.intArray));
            retval.set(axis, retval.get(axis)+add);
            return retval;
        }

        Integer[] hit(Integer[] x) {
            Integer[] retval = x.clone();
            retval[axis] = retval[axis]+add;
            return retval;
        }

        @Override
        public String toString() {
            return axis+":"+add;
        }
    }
    
    public static Function<TList<Integer>,TList<TList<Integer>>> expandMap(int times, int axis) {
        return c->TList.rangeSym(-times,times).map(i->c.replaceAt(axis, c.get(axis)+i));
    }
    public static Function<TList<Integer>,TList<TList<Integer>>> expandMap(int times, int... axis) {
        if (axis.length==0) return c->TList.wrap(c);
        return TList.set(wrap(axis)).map(i->expandMap(times,i)).stream().reduce((a,b)->a.andThen(l->l.flatMap(b))).get();
    }
    public static TList<TList<Integer>> expand(TList<Integer> base, int times, int... axis) {
        return expandMap(times,axis).apply(base);
    }
    public static Function<TList<Integer>,TIterator<TList<Integer>>> expand(int dimension, int times, int... axis) {
        TList<TList<Integer>> added=expand(TList.nCopies(dimension, 0),times,axis).map(l->l.sfix()).sfix();
        return c->added.iterator().map(a->add(c,a));
    }
    
    public static TList<Set<TList<Integer>>> divideByConnection(Set<TList<Integer>> cubes) {
        TList<Set<TList<Integer>>> retval=TList.c();
        while (!cubes.isEmpty()) {
            TList<Integer> start=cubes.iterator().next();
            retval.add(new HashSet<>(TList.sof(start)));
            cubes.remove(start);
            separateCubesConnected(start,cubes,retval.last());
        }
        return retval;
    }
    public static void separateCubesConnected(TList<Integer> start, Set<TList<Integer>> cubes, Set<TList<Integer>>separated) {
        Deque<TList<Integer>> stack=new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            TList<Integer> x=stack.pop();
            x.index().flatten(i->TList.sof(x.replaceAt(i, x.get(i)+1).sfix(),x.replaceAt(i, x.get(i)-1).sfix()))
                    .filter(c->cubes.remove(c)).tee(c->separated.add(c)).forEachRemaining(c->stack.push(c));
        }
    }
    
    /**
     * this is the original of separateCubesConnected().
     * 
     * @param start
     * @param cubes
     * @param separated 
     */
    public static void separateCubesConnectedRecursive(TList<Integer> start, Set<TList<Integer>> cubes, Set<TList<Integer>>separated) {
        start.index().flatten(i->TList.sof(start.replaceAt(i, start.get(i)+1).sfix(),start.replaceAt(i, start.get(i)-1).sfix()))
                .filter(c->cubes.remove(c)).tee(c->separated.add(c)).forEachRemaining(c->separateCubesConnected(c,cubes,separated));
    }
}
