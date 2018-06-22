/*
 Copyright 2017, 2018 Masao Tomono

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

import iterator.TIterator;
import java.util.function.*;

/**
 *
 * @author mtomono
 * @param <IN>
 */
@FunctionalInterface
public interface TSupplier<IN> extends Supplier<IN> {
    
    default <NEW> TSupplier<NEW> andThen(Function<IN, NEW> func) {
        return ()->func.apply(get());
    }
    
    default TIntSupplier andThenInt(ToIntFunction<IN> func) {
        return ()->func.applyAsInt(get());
    }
    
    default TLongSupplier andThenLong(ToLongFunction<IN> func) {
        return ()->func.applyAsLong(get());
    }
    
    default TDoubleSupplier andThenDouble(ToDoubleFunction<IN> func) {
        return ()->func.applyAsDouble(get());
    }
    
    default TIterator<IN> iterator() {
        return TIterator.generate(this);
    }
    
    public static <T> TSupplier<T> f(TSupplier<T> f) {
        return f;
    }

    public static <T> TSupplier<T> w(Supplier<T> f) {
        return new Wrapper(f);
    }
    
    static public class Wrapper<T> implements TSupplier<T> {
        Supplier<T> base;
        public Wrapper(Supplier<T> base) {
            this.base = base;
        }
        @Override
        public T get() {
            return this.base.get();
        }
    }
}
