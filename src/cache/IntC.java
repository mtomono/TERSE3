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

package cache;

import java.util.function.IntSupplier;

/**
 *
 * @author mtomono
 */
public class IntC {
    boolean cached;
    int value;
    IntSupplier supplier;
    
    public IntC(IntSupplier supplier) {
        this.supplier = supplier;
        this.cached = false;
    }
    
    public IntC(int value) {
        this.value = value;
        this.cached = true;
    }
    
    public IntC recalc() {
        calc();
        return this;
    }
    
    public int v() {
        if (!cached)
            calc();
        return value;
    }

    public void calc() {
        value = supplier.getAsInt();
        cached = true;
    }
    
    @Override
    public String toString() {
        return cached?Integer.toString(value):"--";
    }
}
