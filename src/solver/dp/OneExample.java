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
import java.util.Iterator;
import java.util.function.UnaryOperator;
import shape.TPoint2i;

/**
 *
 * @author masao
 * @param <T>
 * @param <R>
 */
abstract public class OneExample<T,R> {
    static public OneExample<TPoint2i,Integer> knapsack(TList<TPoint2i> items,TList<TList<Integer>> output) {
        return new OneExample<TPoint2i,Integer>(items,output) {
            @Override
            public TList<P<Integer,Integer>> taken(P<Integer,Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1).x))
                        .filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+items.get(n.l()-1).y));
            }
        };
    }
    static public OneExample<Integer,Boolean> cut(TList<Integer> items, TList<TList<Boolean>> output) {
        return new OneExample<Integer,Boolean>(items,output) {
            @Override
            public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)));
            }
        };
    }
    static public OneExample<Integer,Integer> shortest(TList<Integer> items, TList<TList<Integer>> output) {
        return new OneExample<Integer,Integer>(items,output) {
        @Override
            public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
                return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+1));
            }
        };
    }
    TList<TList<R>> output;
    TList<TList<TList<TList<P<Integer,Integer>>>>> map;
    TList<T> items;
    P<Integer,Integer> start;
    static public UnaryOperator<TList<TList<P<Integer,Integer>>>> left=l->l;
    static public UnaryOperator<TList<TList<P<Integer,Integer>>>> right=l->l.reverse();
    
    public OneExample(TList<T> items, TList<TList<R>> output) {
        this.output=output;
        this.items=items;
        this.start=P.p(items.size(),output.last().size()-1);
        this.map=TList.range(1,start.l()+1).map(i->TList.range(0,start.r()+1).map(j->P.p(i, j)).map(p->TList.sof(taken(p), notTaken(p))).sfix()).sfix();
    }
    abstract public TList<P<Integer,Integer>> taken(P<Integer,Integer> n);
    public TList<P<Integer,Integer>> notTaken(P<Integer,Integer> n) {
        return TList.sof(P.p(n.l()-1,n.r())).filter(nn->value(n).equals(value(nn)));
    }
    public R value(P<Integer,Integer> n) {
        return output.get(n.l()).get(n.r());
    }    
    public Path right() {
        return new Path(right);
    }
    public Path left() {
        return new Path(left);
    }
        
    /**
     * path in output.
     */
    public class Path {
        TList<P<Integer,Integer>> body;
        UnaryOperator<TList<TList<P<Integer,Integer>>>> dir;
        public Path(TList<P<Integer,Integer>> body,UnaryOperator<TList<TList<P<Integer,Integer>>>> dir) {
            this.dir=dir;
            this.body=body;
        }
        public Path(UnaryOperator<TList<TList<P<Integer,Integer>>>> dir) {
            this.dir=dir;
            this.body=find(TList.of(start));
        }
        public Path inherit(TList<P<Integer,Integer>> body) {
            return new Path(body, dir);
        }
        public TList<P<Integer,Integer>> find(TList<P<Integer,Integer>> start) {
            if (start.get(0).l()==0) return start;
            return trace(start).map(nn->find(nn)).get(0);
        }
        public TList<TList<P<Integer,Integer>>> trace(TList<P<Integer,Integer>> l) {
            return branches(l.get(0)).flatMap(x->x).map(nn->l.startFrom(nn));
        }
        public TList<TList<P<Integer,Integer>>> branches(P<Integer,Integer> n) {
            return dir.apply(map.get(n.l()-1).get(n.r()));
        } 
        public Path next() {
            return body.diff().filterAt(p->branches(p.r()).get(0).equals(TList.wrap(p.l())))
                    .filter(i->!branches(body.get(i+1)).get(1).isEmpty())
                    .map(i->find(branches(body.get(i+1)).get(1)).sfix())
                    .filter(l->!l.isEmpty())
                    .map(l->l.append(body.seek(l.size())))
                    .getOpt(0).map(l->inherit(l)).orElse(inherit(TList.empty()));
        }
        public TList<Integer> items() {
            if (body.isEmpty()) return TList.empty();
            return body.diff().filterAt(p->!p.l().r().equals(p.r().r()));
        }
        public Iterator<Path> iterator() {
            return new PathIterator(this);
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
