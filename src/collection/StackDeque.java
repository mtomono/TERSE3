/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import iterator.TIterator;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class StackDeque<T> extends ArrayDeque<T> {
    public StackDeque() {
        super();
    }
    static public <T> StackDeque<T> c(Collection<T> t) {
        StackDeque<T> retval=new StackDeque();
        retval.addAll(t);
        return retval;
    }
    public StackDeque(T t) {
        this();
        push(t);
    }
    public void push(Iterator<? extends T> t) {
        t.forEachRemaining(e->push(e));
    }
    public void pushR(Collection<? extends T> t) {
        TList.set(t).sfix().reverse().forEach(e->push(e));
    }
    public void exec(Function<? super T, ? extends Collection<? extends T>> f) {
        execi(f.andThen(c->c.iterator()));
    }
    public void execi(Function<? super T, ? extends Iterator<? extends T>> f) {
        while (!isEmpty()) push(f.apply(pop()));
    }
    public void execr(Function<? super T, ? extends Collection<? extends T>> f) {
        while (!isEmpty()) pushR(f.apply(pop()));
    }
    public void execri(Function<? super T, ? extends Iterator<? extends T>> f) {
        while (!isEmpty()) pushR(TIterator.set(f.apply(pop())).asList());
    }
}
