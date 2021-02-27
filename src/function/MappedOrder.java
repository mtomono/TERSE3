/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import function.Order;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <K>
 */
public interface MappedOrder<K, C> extends Order<C> {
    public Order<K> baseOrder();
    public Function<C,K> map();
    @Override default int compare(C a, C b) {return baseOrder().compare(map().apply(a), map().apply(b));}
    @Override default boolean eq(C a, C b) {return baseOrder().eq(map().apply(a), map().apply(b));}
    @Override default boolean ne(C a, C b) {return baseOrder().ne(map().apply(a), map().apply(b));}
    @Override default boolean lt(C a, C b) {return baseOrder().lt(map().apply(a), map().apply(b));}
    @Override default boolean le(C a, C b) {return baseOrder().le(map().apply(a), map().apply(b));}
    @Override default boolean gt(C a, C b) {return baseOrder().gt(map().apply(a), map().apply(b));}
    @Override default boolean ge(C a, C b) {return baseOrder().ge(map().apply(a), map().apply(b));}
    static public <S,T> Order<S> map(Order<T> base, Function<S,T> f) {
        return new MappedOrder<T,S>() {
            @Override
            public Order<T> baseOrder() {
                return base;
            }
            @Override
            public Function<S, T> map() {
                return f;
            }
        };
    }
}
