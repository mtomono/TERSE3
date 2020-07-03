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
package math.quantity;

import java.util.Objects;
import math.Decimal;

/**
 *
 * @author masao
 * @param <K>
 */
public class Exchange<K extends Decimal<K>, U> {
    final public U from;
    final public U to;
    final public K rate;
    public Exchange(K rate, U from, U to) {
        this.rate=rate;
        this.from=from;
        this.to=to;
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Exchange)) {
            return false;
        }
        Exchange t = (Exchange) e;
        return from.equals(t.from)&&to.equals(t.to)&&rate.equals(t.rate);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.from);
        hash = 41 * hash + Objects.hashCode(this.to);
        hash = 41 * hash + Objects.hashCode(this.rate);
        return hash;
    }
    
    public String toString() {
        return "1"+from+"="+rate+to;
    }
}
