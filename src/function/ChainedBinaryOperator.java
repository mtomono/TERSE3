/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package function;

import collection.TList;
import java.util.function.Function;

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
