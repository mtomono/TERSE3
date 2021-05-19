/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import collection.TList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface ChainedBinaryOperator<T> extends ChainedBiFunction<T,T,T> {
    public static <T> ChainedBinaryOperator<T> bo(ChainedBinaryOperator<T> f) {
        return f;
    }
    public static <T> ChainedBinaryOperator<T> bo(ChainedBiFunction<T,T,T> f) {
        return (a,b)->f.apply(a,b);
    }
    default Function<T,T> reduce(Function<T,T>... fs) {
        return reduce(TList.sof(fs));
    }
    default Function<T,T> reduce(TList<Function<T,T>> fs) {
        assert !fs.isEmpty() : "reduce() has to have at least one parameter";
        return reduce((a,b)->apply(a,b),fs.stream()).get();
    }
    public static <T> Optional<Function<T,T>> reduce(ChainedBinaryOperator<T> combiner, Stream<Function<T,T>> fs) {
        return fs.reduce((a,b)->combiner.unify(a, b));
    }
}
