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

import java.util.function.*;

/**
 *
 * @author mtomono
 * @param <IN>
 */
@FunctionalInterface
public interface TDoubleSupplier extends DoubleSupplier {
    
    default <NEW> TSupplier<NEW> andThen(DoubleFunction<NEW> func) {
        return ()->func.apply(this.getAsDouble());
    }
    
    default TIntSupplier andThenInt(DoubleToIntFunction func) {
        return ()->func.applyAsInt(this.getAsDouble());
    }
    
    default TLongSupplier andThenLong(DoubleToLongFunction func) {
        return ()->func.applyAsLong(this.getAsDouble());
    }
    
    default TDoubleSupplier andThenDouble(DoubleUnaryOperator func) {
        return ()->func.applyAsDouble(this.getAsDouble());
    }
    
    public static TDoubleSupplier f(TDoubleSupplier f) {
        return f;
    }

    public static TDoubleSupplier w(DoubleSupplier f) {
        return new Wrapper(f);
    }
    
    static public class Wrapper implements TDoubleSupplier {
        DoubleSupplier base;
        public Wrapper(DoubleSupplier base) {
            this.base = base;
        }
        @Override
        public double getAsDouble() {
            return this.base.getAsDouble();
        }
    }
}
