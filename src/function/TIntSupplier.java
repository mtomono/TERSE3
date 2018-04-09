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
public interface TIntSupplier extends IntSupplier {
    
    default <NEW> TSupplier<NEW> andThen(IntFunction<NEW> func) {
        return ()->func.apply(this.getAsInt());
    }
    
    default TIntSupplier andThenInt(IntUnaryOperator func) {
        return ()->func.applyAsInt(this.getAsInt());
    }
    
    default TLongSupplier andThenLong(IntToLongFunction func) {
        return ()->func.applyAsLong(this.getAsInt());
    }
    
    default TDoubleSupplier andThenDouble(IntToDoubleFunction func) {
        return ()->func.applyAsDouble(this.getAsInt());
    }
    
    public static TIntSupplier f(TIntSupplier f) {
        return f;
    }
    
    public static TIntSupplier w(IntSupplier f) {
        return new Wrapper(f);
    }
    
    static public class Wrapper implements TIntSupplier {
        IntSupplier base;
        public Wrapper(IntSupplier base) {
            this.base = base;
        }
        @Override
        public int getAsInt() {
            return this.base.getAsInt();
        }
    }
}
