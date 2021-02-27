/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.MappedOrder;
import function.Order;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public interface ContextOrder<K,C extends Context<K,C>> extends MappedOrder<K,C> {
    @Override
    default Function<C, K> map() {
        return c->c.body();
    }
}
