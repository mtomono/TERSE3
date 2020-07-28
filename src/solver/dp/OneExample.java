/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.P;
import collection.TList;
import debug.Te;
import iterator.AbstractBufferedIterator;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;
import shape.TPoint2i;

/**
 *
 * @author masao
 * @param <T>
 * @param <R>
 */
abstract public class OneExample<T,R> {
    static public OneExample<TPoint2i,Integer> knapsack(TList<TPoint2i> items,TList<TList<Integer>> output) {
        return new Knapsack(items,output);
    }
    static public OneExample<Integer,Boolean> cut(TList<Integer> items, TList<TList<Boolean>> output) {
        return new Cut(items,output);
    }
    static public OneExample<Integer,Integer> shortest(TList<Integer> items, TList<TList<Integer>> output) {
        return new Shortest(items,output);
    }
    TList<TList<R>> output;
    TList<TList<TList<TList<P<Integer,Integer>>>>> map;
    TList<T> items;
    P<Integer,Integer> start;
    
    public OneExample(TList<T> items, TList<TList<R>> output) {
        this.output=output;
        this.items=items;
        this.start=P.p(items.size(),output.last().size()-1);
        this.map=TList.range(1,start.l()+1).map(i->TList.range(0,start.r()+1).map(j->P.p(i, j)).map(p->TList.sof(taken(p), notTaken(p))).sfix()).sfix();
    }
        
    public TList<P<Integer,Integer>> findLoop(TList<P<Integer,Integer>> start) {
        Stack<TList<P<Integer,Integer>>> stack = new Stack<>();
        stack.push(start);
        while (true) {
            if (stack.empty()) return TList.empty();
            TList<P<Integer,Integer>> l = stack.pop();
            if (l.get(0).l()==0) return l;
            right(l).reverse().forEach(nn->stack.push(nn));
        }
    }
    public TList<P<Integer,Integer>> find(TList<P<Integer,Integer>> start) {
        if (start.get(0).l()==0) return start;
        return right(start).map(nn->find(nn)).get(0);
    } 
    public TList<TList<P<Integer,Integer>>> left(TList<P<Integer,Integer>> l) {
        return Te.e(taken(l.get(0)).append(notTaken(l.get(0)))).map(nn->l.startFrom(nn));
    }
    public TList<TList<P<Integer,Integer>>> right(TList<P<Integer,Integer>> l) {
        return left(l).reverse();
    }
    
    /**
     * get value in output.
     * @param n
     * @return 
     */
    public R value(P<Integer,Integer> n) {
        return output.get(n.l()).get(n.r());
    }
    
    public TList<TList<P<Integer,Integer>>> back(P<Integer,Integer> n) {
        return Te.e(map.get(n.l()-1).get(n.r()));
    }
    
    abstract public TList<P<Integer,Integer>> taken(P<Integer,Integer> n);
    public TList<P<Integer,Integer>> notTaken(P<Integer,Integer> n) {
        return TList.sof(P.p(n.l()-1,n.r())).filter(nn->value(n).equals(value(nn)));
    }
    
    static public class Knapsack extends OneExample<TPoint2i,Integer> {
        public Knapsack(TList<TPoint2i> items, TList<TList<Integer>> output) {
            super(items, output);
        }
        @Override
        public TList<P<Integer,Integer>> taken(P<Integer,Integer> n) {
            return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1).x)).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+items.get(n.l()-1).y));
        }
    }
    
    static public class Cut extends OneExample<Integer,Boolean> {
        public Cut(TList<Integer> items, TList<TList<Boolean>> output) {
            super(items, output);
        }
        @Override
        public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
            return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)));
        }
    }
    
    static public class Shortest extends OneExample<Integer,Integer> {
        public Shortest(TList<Integer> items, TList<TList<Integer>> output) {
            super(items, output);
        }
        @Override
        public TList<P<Integer, Integer>> taken(P<Integer, Integer> n) {
            return TList.sof(P.p(n.l()-1,n.r()-items.get(n.l()-1))).filter(nn->nn.r()>=0).filter(nn->value(n).equals(value(nn)+1));
        }
    }

    public Path path() {
        return new Path(find(TList.of(start)));
    }
    
    public Iterator<Path> iterator() {
        return new PathIterator();
    }
    
    /**
     * path in output.
     */
    public class Path {
        TList<P<Integer,Integer>> body;
        public Path(TList<P<Integer,Integer>> body) {
            this.body=body;
        }
        public TList<Integer> items() {
            return body.diff().filterAt(p->!p.l().r().equals(p.r().r()));
        }
        public Path next() {
            throw new RuntimeException();
        }
        public Path left() {
            return new Path(body.diff().filterAt(p->p.l().r().equals(p.r().r()))
                    .filter(i->!taken(body.get(i+1)).isEmpty())
                    .filter(i->!find(taken(body.get(i+1))).isEmpty())
                    .map(i->find(taken(body.get(i+1))).append(body.seek(i+1))).get(0));
        }
    }
    
    public class PathIterator extends AbstractBufferedIterator<Path> {
        boolean first;
        Path current;
        PathIterator() {
            this.first=true;
            this.current=new Path(TList.empty());
        }
        @Override
        protected void findNext() {
            if (first==true) {
                first=false;
                current=path();
                if (!current.body.isEmpty())
                    nextFound(current);
            } else {
                
            }
        }
    }
}
