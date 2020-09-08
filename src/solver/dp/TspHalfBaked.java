/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.MVMap;
import collection.MVOrderedMap;
import collection.P;
import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import debug.Te;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * in part, its greedy.
 * it's not trying to find the best answer from the begining.
 * but tried to avoid loop. but that simply means hamilton cycle 
 * problem which is as hard as tsp itself.
 * so i gave up to restrict the applicable graph to complete graphs.
 * 
 * @author masao
 */
public class TspHalfBaked {
    static public Builder builder(int vertices) {
        return new Builder(vertices);
    }
    static public class Builder {
        TList<TList<Integer>> graph;
        int vertices;
        public Builder(int vertices) {
            this.vertices=vertices;
            this.graph=TList.empty();
        }
        public Builder e(TList<Integer> graph) {
            this.graph=this.graph.append(graph.fold(3));
            return this;
        }
        public Builder e(int... graph) {
            return e(TList.set(wrap(graph)));
        }
        public TspHalfBaked build() {
            MVOrderedMap<Integer,P<Integer,Integer>> map=new MVOrderedMap<>(new HashMap<>(), (a,b)->a.r().compareTo(b.r()));
            graph.forEach(r->map.add(r.get(0), P.p(r.get(1), r.get(2))));
            return new TspHalfBaked(vertices,map);
        }
    }
    MVMap<Integer,P<Integer,Integer>> graph;
    int vertices;
    int start=0;
    public TspHalfBaked(int vertices,MVMap<Integer,P<Integer,Integer>> graph) {
        this.vertices=vertices;
        this.graph=graph;
    }
    class Cut extends RuntimeException {
        TList<Integer> result;
        Cut(TList<Integer> result) {
            this.result=result;
        }
    }
    public void solveRecurse(TList<Integer> result) {
        if (result.size()==vertices)
            if (graph.getList(result.last()).map(p->p.l()).contains(start))
                throw new Cut(result.addOne(start));
            else
                return;
        if (result.isEmpty())
            throw new RuntimeException("unsolvable");
        graph.getList(result.last()).filter(p->!result.contains(p.l())).forEach(p->solveRecurse(result.fix().addOne(p.l())));
    }
    public TList<Integer> solve() {
        try {
            solveRecurse(TList.of(start));
        } catch(Cut c) {
            return c.result;
        }
        throw new RuntimeException("unsolvable");
    }
    public TList<Integer> solveLoop() {
        Deque<TList<Integer>> stack = new ArrayDeque<>();
        stack.push(TList.sof(start));
        while(!stack.isEmpty()) {
            TList<Integer> one = stack.pop();
            if (one.size()==vertices)
                if (graph.getList(one.last()).map(p->p.l()).contains(start))
                    return one.addOne(start);
            graph.getList(one.last()).reverse().sfix().filter(p->!one.contains(p.l())).forEach(p->stack.push(one.fix().addOne(p.l())));
        }
        throw new RuntimeException("unsolvable");
    }
}
