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

package solver.dp;

import collection.P;
import collection.TList;
import iterator.AbstractBufferedIterator;
import java.util.function.UnaryOperator;
import shape.TPoint2i;
import test.PTe;

/**
 *
 * @author masao
 * @param <T>
 * @param <R>
 */
abstract public class ReverseDP<T,R> {
    static public ReverseDP<TPoint2i,Integer> knapsack(TList<TPoint2i> items,TList<TList<Integer>> output) {
        return new ReverseDP<TPoint2i,Integer>(items,output) {
            @Override
            public TList<P<Integer,Integer>> taken(P<Integer,Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1).x))
                        .filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+items.get(n.l()-1).y));
            }
        };
    }
    static public ReverseDP<Integer,Boolean> cut(TList<Integer> items, TList<TList<Boolean>> output) {
        return new ReverseDP<Integer,Boolean>(items,output) {
            @Override
            public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)));
            }
        };
    }
    static public ReverseDP<Integer,Integer> shortest(TList<Integer> items, TList<TList<Integer>> output) {
        return new ReverseDP<Integer,Integer>(items,output) {
        @Override
            public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+1));
            }
        };
    }
    TList<TList<R>> output;
    TList<T> items;
    P<Integer,Integer> start;
    static public UnaryOperator<TList<TList<P<Integer,Integer>>>> left=l->l;
    static public UnaryOperator<TList<TList<P<Integer,Integer>>>> right=l->l.reverse();
    
    public ReverseDP(TList<T> items, TList<TList<R>> output) {
        this.output=output;
        this.items=items;
        this.start=P.p(items.size(),output.last().size()-1);
    }
    abstract public TList<P<Integer,Integer>> taken(P<Integer,Integer> n);
    public TList<P<Integer,Integer>> notTaken(P<Integer,Integer> n) {
        return TList.sof(P.p(n.l()-1,n.r())).filter(nn->value(n).equals(value(nn)));
    }
    public R value(P<Integer,Integer> n) {
        return output.get(n.l()).get(n.r());
    }    
    
    public Map right() {
        return new Map(right);
    }
    public Map left() {
        return new Map(left);
    }
    
    public class Map {
        TList<TList<TList<TList<P<Integer,Integer>>>>> map;
        UnaryOperator<TList<TList<P<Integer,Integer>>>> dir;
        public Map(UnaryOperator<TList<TList<P<Integer,Integer>>>> dir) {
            this.dir=dir;
            this.map=TList.range(1,start.l()+1).map(i->TList.range(0,start.r()+1).map(j->P.p(i,j)).map(p->TList.sof(taken(p), notTaken(p))).sfix()).sfix();
        }
        public TList<P<Integer,Integer>> find(TList<P<Integer,Integer>> start) {
            if (start.get(0).l()==0) return start;
            return trace(start).map(nn->find(nn)).get(0);
        }
        public TList<TList<P<Integer,Integer>>> develop(TList<TList<P<Integer,Integer>>> start) {
            if (PTe.ex(start.get(0).get(0).l())==0) return start;
            return develop(start.flatMapc(l->trace(l)).sfix());
        }
        public TList<TList<P<Integer,Integer>>> trace(TList<P<Integer,Integer>> l) {
            return branches(l.get(0)).flatMap(x->x).map(nn->l.startFrom(nn).sfix()).sfix();
        }
        public TList<TList<P<Integer,Integer>>> branches(P<Integer,Integer> n) {
            return dir.apply(map.get(n.l()-1).get(n.r()));
        }
        public TList<Path> whole() {
            return develop(TList.of(TList.of(start))).map(p->new Path(p));
        }
        public Path first() {
            return new Path(find(TList.of(start)));
        }
        public PathIterator iterator() {
            return new PathIterator(first());
        }
        public class Path {
            TList<P<Integer,Integer>> body;
            public Path(TList<P<Integer,Integer>> body) {
                this.body=body;
            }
            public Path next() {
                return body.diff()
                        .filterAt(p->branches(p.r()).get(0).equals(TList.wrap(p.l())))
                        .filter(i->!branches(body.get(i+1)).get(1).isEmpty())
                        .map(i->find(branches(body.get(i+1)).get(1)).sfix())
                        .filter(l->!l.isEmpty())
                        .map(l->l.append(body.seek(l.size())))
                        .getOpt(0).map(l->new Path(l)).orElse(new Path(TList.empty()));
            }
            public TList<Integer> items() {
                if (body.isEmpty()) return TList.empty();
                return body.diff().filterAt(p->!p.l().r().equals(p.r().r()));
            }
        }
        public class PathIterator extends AbstractBufferedIterator<Path> {
            Path current;
            PathIterator(Path start) {
                this.current=start;
            }
            @Override
            protected void findNext() {
                if (!current.body.isEmpty())
                    nextFound(current);
                this.current=current.next();
            }
        }
    }
}
