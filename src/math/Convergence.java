/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.Holder;
import iterator.TIterator;
import static iterator.TIterator.of;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class Convergence {
    public static <S,K,T extends ContextOrdered<K,T>> TIterator<S> diff(TIterator<S> target,Function<S,T> f,T threshold) {
        return converge(target,f,(p,c)->p.sub(c).abs().lt(threshold));
    }
    
    public static <S,K,T extends ContextOrdered<K,T>> TIterator<S> converge(TIterator<S> target,Function<S,T> f,BiPredicate<T,T> cond) {
        TIterator<T> convSeq=target.map(f);
        if (!target.hasNext())
            return target;
        S start=target.next();
        Holder<T> prev=new Holder<>(f.apply(start));
        return of(start).append(target.until(t->{var v=f.apply(t);return cond.test(prev.push(v),v);}));
    }
}
