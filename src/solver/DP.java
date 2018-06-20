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
abstract public class DP<T> {
    static <T> Function<TPoint2i,Result<T>> basic(DP<T> k, TList<TPoint2i> c) {
        return pi->k.valueCore(pi.x, pi.y, c);
    }

    static <T> Function<TPoint2i,Result<T>> memo(DP<T> k, Map<TPoint2i, Result<T>> map) {
        final Function<TPoint2i, Result<T>> f = k.value;
        return pi->map.computeIfAbsent(pi, f);
    }
    
    static <T> Function<TPoint2i,Result<T>> memo(DP<T> k, Grid<Result<T>> map) {
        final Function<TPoint2i, Result<T>> f = k.value;
        return pi->map.computeIfNull(pi, f);
    }

    Function<TPoint2i, Result<T>> value;
        
    public DP() {
    }
    
    /**
     * memo.
     * thanks to abstracting memo by lambda, the DP object itself is free of those fields. thus, making it possible
     * for other classes to use those static methods.
     * @param map
     * @return 
     */
    public DP<T> memo(Map<TPoint2i, Result<T>> map) {
        return setValue(memo(this,map));
    }
    
    public DP<T> memo() {
        return memo(new TreeMap<>(Comparators.<TPoint2i>sof(p->p.x, p->p.y).compile()));
    }
    
    public DP<T> memo(Grid<Result<T>> map) {
        return setValue(memo(this,map));
    }
    
    public DP<T> target(TList<TPoint2i> c) {
        return setValue(basic(this,c));
    }
    
    public DP<T> setValue(Function<TPoint2i, Result<T>> value) {
        this.value = value;
        return this;
    }
    
    Result<T> value(int i, int rest) {
        return value.apply(p2i(i,rest));
    }
    
    abstract Result<T> valueCore(int i, int rest, TList<TPoint2i> c);
    
    public Result<T> solve(int capacity) {
        return value(0, capacity);
    }
}
