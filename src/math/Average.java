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
public class Average<K,C extends Context<K,C>> {
    final public int count;
    final public C sum;
    public Average(int count, C value) {
        this.count=count;
        this.sum=value;
    }
    public Average<K,C> add(C value) {
        return new Average(count+1,this.sum.add(value));
    }
    public Average<K,C> add(Average<K,C> average) {
        return new Average(count+average.count,sum.add(average.sum));
    }
    public C average() {
        return sum.div(sum.b().b(count));
    }
    static <T,K,C extends Context<K,C>> Collector<T,?,C> collector(Function<T,C> mapper, C zero) {
        return Collector.of(()->new Holder<Average<K,C>>(new Average<>(0,zero)), (a,t)->a.set(a.get().add(mapper.apply(t))), (a,b)->a.set(a.get().add(b.get())), a->a.get().average());
    }
}
