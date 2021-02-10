/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.function.Function;

/**
 *
 * @author masao
 */
public interface Transformable<K> {
    public K self();
    public default <L> L transform(Function<K,L> f) {
        return f.apply(self());
    }
}
