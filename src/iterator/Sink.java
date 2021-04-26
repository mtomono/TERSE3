/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.function.Consumer;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Sink<T> {
    public Consumer<TIterator<T>> out();
}
