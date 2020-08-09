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

package solver.dp.graph;


import collection.TList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import static math.VectorOp.subI;
import static shape.ShapeUtil.pni;
import shapeCollection.GridCoord;
import static shapeCollection.GridCoord.gcoord;
import shapeCollection.GridOverList;
import solver.dp.graph.KnapsackDPg.Edge;

/**
 * KnapsackDP based on grid.
 * It was totally a bad idea to build DP based on grid.
 * in that case, the program simply has to secure the whole DP space.
 * especially when we think about Levenshtein Distance, which can be applied to 
 * documents as diff, it will take up memory a.filesize()*b.filesize(). that's
 * simply a nightmare.
 * i intentionally leave this program here, but this is only a reminder.
 * @author masao
 */
public class KnapsackDPg<T,R> {
    static List<Integer> xx=TList.sof(1,0);
    static List<Integer> yy=TList.sof(0,1);
    static List<Integer> xy=TList.sof(1,1);
    GridOverList<R> grid;
    public KnapsackDPg(TList<Integer> from, TList<Integer> to, Function<TList<Integer>,R> f) {
        this.grid=new GridOverList<>(GridCoord.gcoord(from, to),f).fix();
    }
    public KnapsackDPg(GridOverList<R> grid) {
        this.grid=grid;
    }

    static <T> KnapsackDPg<T,Integer> levenshtein(TList<T>x,TList<T>y) {
        GridCoord space=gcoord(TList.sof(-1,-1),TList.sof(x.size()-1,y.size()-1));
        TList<Integer> mem=TList.set(new ArrayList<>(Collections.nCopies(space.size(),0)));
        KnapsackDPg<T,Integer> retval = new KnapsackDPg<>(new GridOverList<>(space,mem));
        Edge<Integer> insert =new Edge<>(p->retval.get(subI(p,yy))+1);
        Edge<Integer> delete =new Edge<>(p->retval.get(subI(p,xx))+1);
        Edge<Integer> replace=new Edge<>(p->retval.get(subI(p,xy))+1, p->!x.get(p.get(0)).equals(y.get(p.get(1))));
        Edge<Integer> match  =new Edge<>(p->retval.get(subI(p,xy)), p->x.get(p.get(0)).equals(y.get(p.get(1))));
        retval.grid.set(0,-1,-1);
        TList<Edge<Integer>> starts=TList.sof(insert,delete);
        TList.range(0,x.size()).map(i->pni(i,-1)).forEach(p->retval.grid.set(candidates(starts,p).min(i->i).get(),p));
        TList.range(0,y.size()).map(j->pni(-1,j)).forEach(p->retval.grid.set(candidates(starts,p).min(i->i).get(),p));
        TList<Edge<Integer>> edges=TList.sof(insert,delete,replace,match);
        TList.range(0,x.size()).flatMapc(i->TList.range(0,y.size()).map(j->pni(i,j)))
                .forEach(p->retval.grid.set(candidates(edges,p).min(i->i).get(),p));
        return retval;
    }
    
    static public <R> TList<R> candidates(TList<Edge<R>> edges, List<Integer> p) {
        return edges.flatMapc(e->e.get(p));
    }
    public static class OutOfScope extends Exception {}
    public R get(List<Integer>p) throws OutOfScope{
        if (!grid.contains(p)) throw new OutOfScope();
        return grid.get(p);
    }
    @FunctionalInterface
    interface Traverse<S,T> {
        T go(S from) throws OutOfScope;
    }
    static public class Edge<R> {
        Traverse<List<Integer>,R> traverse;
        Predicate<List<Integer>> condition;
        Edge(Traverse<List<Integer>,R> traverse,Predicate<List<Integer>> condition) {
            this.traverse=traverse;
            this.condition=condition;
        }
        Edge(Traverse<List<Integer>,R> traverse) {
            this(traverse,l->true);
        }
        public TList<R> get(List<Integer>p) {
            if (!condition.test(p)) return TList.empty();
            try {
                return TList.wrap(traverse.go(p));
            } catch (OutOfScope e) {
                return TList.empty();
            }
        }
    }
}
