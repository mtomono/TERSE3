/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.P;
import collection.TList;
import function.Holder;
import java.util.List;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class ReverseDP {
    public static class RList extends TList<TList<P<Integer,Integer>>> {
        public RList(List<TList<P<Integer, Integer>>> body) {
            super(body);
        }
        public TList<TList<Integer>> items() {
            return this.map(l->l.seek(-1).map(p->p.r()));
        }
    }
    static public RList cut(TList<Integer>items, TList<TList<Boolean>> output) {
        if (output.last().isEmpty()) return new RList(TList.empty());
        if (!output.last().last()) return new RList(TList.empty());
        Holder<TList<TList<P<Integer,Integer>>>> path = new Holder<>(TList.of(TList.of(P.p(output.last().size()-1,-1))));
        TList.range(0,items.size()).reverse().forEach(i->{
            TList<TList<P<Integer,Integer>>> wo = path.get().filter(p->output.get(i).get(p.get(0).l()));
            TList<TList<P<Integer,Integer>>> wi = path.get().map(p->p.startFrom(P.p(p.get(0).l()-items.get(i),i)))
                    .filter(p->p.get(0).l()>=0)
                    .filter(p->output.get(i).get(p.get(0).l()));
            path.set(wo.append(wi).fix());
        });
        return new RList(path.get());
    }
    static public RList knapsack(TList<TPoint2i>items, TList<TList<Integer>> output) {
        if (output.last().isEmpty()) return new RList(TList.empty());
        Holder<TList<TList<P<Integer,Integer>>>> path = new Holder<>(TList.of(TList.of(P.p(output.last().size()-1,-1))));
        TList.range(0,items.size()).reverse().forEach(i->{
            TList<TList<P<Integer,Integer>>> wo = path.get().filter(p->output.get(i+1).get(p.get(0).l()).equals(output.get(i).get(p.get(0).l())));
            TList<TList<P<Integer,Integer>>> wi = path.get().map(p->p.startFrom(P.p(p.get(0).l()-items.get(i).x,i)))
                    .filter(p->p.get(0).l()>=0)
                    .filter(p->output.get(i+1).get(p.get(1).l()).equals(output.get(i).get(p.get(0).l())+items.get(i).y));
            path.set(wo.append(wi).fix());
        });
        return new RList(path.get());
    }
}
