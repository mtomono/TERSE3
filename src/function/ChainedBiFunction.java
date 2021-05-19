/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface ChainedBiFunction<S,T,U> extends BiFunction<S,T,U>{
    public static <S,T,U> ChainedBiFunction<S,T,U> b(ChainedBiFunction<S,T,U> f) {
        return f;
    }
    default <V,W> ChainedBiFunction<V,W,U> compose(Function<V,S> l, Function<W,T> r) {
        return (ll,rr)->apply(l.apply(ll),r.apply(rr));
    }
    default <V> Function<V,U> unify(Function<V,S> l, Function<V,T> r) {
        return x->apply(l.apply(x),r.apply(x));
    }
}
