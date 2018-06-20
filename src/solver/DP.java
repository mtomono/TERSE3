/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import orderedSet.Comparators;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;

/**
 *
 * @author masao
 */
abstract public class DP<S,R> {
    static <S,R> Function<TPoint2i,R> basic(DP<S,R> k, TList<S> c) {
        return pi->k.valueCore(pi.x, pi.y, c);
    }

    static <S,R> Function<TPoint2i,R> memo(DP<S,R> k, Map<TPoint2i, R> map) {
        final Function<TPoint2i, R> f = k.value;
        return pi->map.computeIfAbsent(pi, f);
    }
    
    static <S,R> Function<TPoint2i,R> memo(DP<S,R> k, Grid<R> map) {
        final Function<TPoint2i, R> f = k.value;
        return pi->map.computeIfNull(pi, f);
    }

    abstract R valueCore(int i, int rest, TList<S> c);

    Function<TPoint2i, R> value;
        
    public DP() {
    }
    
    /**
     * memo.
     * thanks to abstracting memo by lambda, the DP object itself is free of those fields. thus, making it possible
     * for other classes to use those static methods.
     * @param map
     * @return 
     */
    public DP<S,R> memo(Map<TPoint2i, R> map) {
        return setValue(memo(this,map));
    }
    
    public DP<S,R> memo() {
        return memo(new TreeMap<>(Comparators.<TPoint2i>sof(p->p.x, p->p.y).compile()));
    }
    
    public DP<S,R> memo(Grid<R> map) {
        return setValue(memo(this,map));
    }
    
    public DP<S,R> target(TList<S> c) {
        return setValue(basic(this,c));
    }
    
    public DP<S,R> setValue(Function<TPoint2i, R> value) {
        this.value = value;
        return this;
    }
    
    R value(int i, int rest) {
        return value.apply(p2i(i,rest));
    }
    
    
    public R solve(int capacity) {
        return value(0, capacity);
    }
}
