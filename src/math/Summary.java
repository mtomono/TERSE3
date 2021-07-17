/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.Holder;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 *
 * @author masao
 * @param <K>
 * @param <C>
 */
public class Summary<K,C extends Context<K,C>> {
    final public long count;
    final public C sum;
    public Summary(long count, C value) {
        this.count=count;
        this.sum=value;
    }
    public Summary<K,C> add(C value) {
        return new Summary(count+1,this.sum.add(value));
    }
    public Summary<K,C> add(Summary<K,C> average) {
        return new Summary(count+average.count,sum.add(average.sum));
    }
    public long count() {
        return count;
    }
    public C sum() {
        return sum;
    }
    public C average() {
        return sum.div(sum.b().b(count));
    }
    public static <T,K,C extends Context<K,C>> Collector<T,?,Summary<K,C>> summary(Function<T,C> mapper, C zero) {
        return Collector.of(()->new Holder<Summary<K,C>>(new Summary<>(0,zero)), (a,t)->a.set(a.get().add(mapper.apply(t))), (a,b)->a.set(a.get().add(b.get())),a->a.get());
    }
}
