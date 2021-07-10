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
public class AverageMutable<K,C extends Context<K,C>> {
    long count;
    C sum;
    public AverageMutable(long count, C value) {
        this.count=count;
        this.sum=value;
    }
    public long count() {
        return count;
    }
    public C sum() {
        return sum;
    }
    public AverageMutable<K,C> add(C value) {
        count++;
        this.sum=this.sum.add(value);
        return this;
    }
    public AverageMutable<K,C> add(AverageMutable<K,C> average) {
        this.count=count+average.count;
        this.sum=sum.add(average.sum);
        return this;
    }
    public C average() {
        return sum.div(sum.b().b(count));
    }
    static <T,K,C extends Context<K,C>> Collector<T,?,C> collector(Function<T,C> mapper, C zero) {
        return Collector.of(()->new AverageMutable<K,C>(0,zero), (a,t)->a.add(mapper.apply(t)), (a,b)->a.add(b), a->a.average());
    }
}
