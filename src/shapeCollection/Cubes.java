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

import static arithmetic.Arithmetic.ceil;
import collection.P;
import collection.Scale;
import collection.TList;
import static collection.c.a2l;
import static java.lang.Math.floor;
import java.util.List;

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

    static TList<Integer> toStartingCube(List<Double> factor, List<Double> from) {
        assert factor.size()==from.size();
        return TList.ofStatic(factor, from).transpose(l->l).map(l->(int) ceil.o(l.get(1)/l.get(0)));
    }

    static Integer[] toStartingCubeArr(List<Double> factor, List<Double> from) {
        return toStartingCube(factor, from).toArray(intArray);
    }

    static TList<ScaledAxis> toAxis(List<Double> zero, List<Double> factor, List<Double> from, List<Double> to) {
        assert TList.ofStatic(zero, factor, from, to).map(l->l.size()).isUniform();
        return TList.ofStatic(zero, factor, from, to).transpose(l->l).map(l->new ScaledAxis(l.get(0), l.get(1), l.get(2), l.get(3)));
    }

    static TList<ScaledAxis> normalize(TList<ScaledAxis> scales, double normalSize) {
        return scales.map(scale->scale.fit(0d, normalSize));
    }

    static TList<List<P<Double, Hit>>> hits(TList<ScaledAxis> normalized, TList<ScaledAxis> original) {
        return normalized.pair(new Scale(), (scale, i)->TList.set(scale).map(d->P.p(d, new Hit(i, (int) original.get(i).dir()))));
    }

    static TList<P<Double, Hit>> mergeAndSortHits(TList<List<P<Double, Hit>>> list) {
        return list.flatMap(l->l).sortTo((a, b)->(int) floor(a.l()-b.l()));
    }

    static TList<List<Integer>> hitToCube(List<Integer> start, TList<P<Double, Hit>> hits) {
        return hits.accum(start, (a, b)->b.r().hit(a));
    }

    static TList<Integer[]> hitToCubeArr(TList<P<Double, Hit>> hits, Integer[] start) {
        return hits.accum(start, (a, b)->b.r().hit(a));
    }

    static TList<List<Integer>> toCubes(List<Double> zero, List<Double> factor, List<Double> from, List<Double> to, double norm) {
        TList<Integer> initial = toStartingCube(factor, from).fix();
        TList<ScaledAxis> axis = toAxis(zero, factor, from, to);
        return hitToCube(initial, mergeAndSortHits(hits(normalize(axis, norm).fix(), axis).fix()));
    }

    static TList<Integer> add(TList<Integer> cube, List<Integer> vector) {
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
}
