/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class Chain<T> {
    T body;
    public static <T> Chain<T> chain(T body) {
        return new Chain<>(body);
    }
    public Chain(T body) {
        this.body=body;
    }
    public <S> Chain<S> f(Function<T,S> f) {
        return new Chain<>(f.apply(body));
    }
    public Chain<T> c(Consumer<T> c) {
        c.accept(body);
        return this;
    }
    public T get() {
        return body;
    }
}
