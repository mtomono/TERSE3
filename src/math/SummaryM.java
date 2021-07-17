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
public class SummaryM<K,C extends Context<K,C>> {
    long count;
    C sum;
    public SummaryM(long count, C value) {
        this.count=count;
        this.sum=value;
    }
    public long count() {
        return count;
    }
    public C sum() {
        return sum;
    }
    public SummaryM<K,C> add(C value) {
        count++;
        this.sum=this.sum.add(value);
        return this;
    }
    public SummaryM<K,C> add(SummaryM<K,C> average) {
        this.count=count+average.count;
        this.sum=sum.add(average.sum);
        return this;
    }
    public C average() {
        return sum.div(sum.b().b(count));
    }
    public static <T,K,C extends Context<K,C>> Collector<T,?,SummaryM<K,C>> summary(Function<T,C> mapper, C zero) {
        return Collector.of(()->new SummaryM<K,C>(0,zero), (a,t)->a.add(mapper.apply(t)), (a,b)->a.add(b));
    }
}
