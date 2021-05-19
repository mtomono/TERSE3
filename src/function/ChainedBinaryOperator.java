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
    default Function<T,T> unify(Function<T,T>... fs) {
        return unify(TList.sof(fs));
    }
    default Function<T,T> unify(TList<Function<T,T>> fs) {
        assert !fs.isEmpty() : "reduce() has to have at least one parameter";
        return fs.stream().reduce((a,b)->unify(a,b)).get();
    }
}
