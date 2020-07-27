/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.P;
import collection.TList;
import java.util.Stack;
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
    TList<T> items;
    P<Integer,Integer> start;
    public OneExample(TList<T> items, TList<TList<R>> output) {
        this.output=output;
        this.items=items;
        this.start=P.p(items.size(),output.last().size()-1);
    }
    
    public TList<P<Integer,Integer>> path() {
        return findLoop(TList.of(start));
    }
    public TList<P<Integer,Integer>> findLoop(TList<P<Integer,Integer>> start) {
        Stack<TList<P<Integer,Integer>>> stack = new Stack<>();
        stack.push(start);
        while (true) {
            if (stack.empty()) return TList.empty();
            TList<P<Integer,Integer>> l = stack.pop();
            if (l.get(0).l()==0) return l;
            find(l).forEach(nn->stack.push(nn));
        }
    }
    public TList<P<Integer,Integer>> findRec(TList<P<Integer,Integer>> start) {
        if (start.get(0).l()==0) return start;
        return find(start).map(nn->findRec(nn)).get(0);
    } 
    public TList<TList<P<Integer,Integer>>> find(TList<P<Integer,Integer>> l) {
        return taken(l.get(0)).append(notTaken(l.get(0))).map(nn->l.startFrom(nn));
    }
    
    public R value(P<Integer,Integer> n) {
        return output.get(n.l()).get(n.r());
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
}
