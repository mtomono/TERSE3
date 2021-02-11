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
package math;

import static java.lang.Math.floor;
import static java.lang.Math.round;

/**
 *
 * @author masao
 */
public class KDouble implements Decimal<KDouble> {
    public final double v;
    
    static public final KDouble ZERO = new KDouble(0);
    static public final KDouble ONE = new KDouble(0);
    static public KDouble b(double value) {
        return new KDouble(value);
    }
    static public KDouble b(String value) {
        return b(Double.parseDouble(value));
    }
    public KDouble(double body) {
        this.v = body;
    }

    @Override
    public KDouble add(KDouble value) {
        return new KDouble(v+value.v);
    }

    @Override
    public KDouble sub(KDouble value) {
        return new KDouble(v-value.v);
    }

    @Override
    public KDouble mul(KDouble value) {
        return new KDouble(v*value.v);
    }

    @Override
    public KDouble div(KDouble value) {
        return new KDouble(v/value.v);
    }

    @Override
    public KDouble zero() {
        return ZERO;
    }
    
    @Override
    public KDouble one() {
        return ONE;
    }

    @Override
    public KDouble negate() {
        return new KDouble(-v);
    }

    @Override
    public KDouble abs() {
        return new KDouble(Math.abs(v));
    }

    @Override
    public KDouble mul(int v) {
        return mul(new KDouble(v));
    }

    @Override
    public KDouble mul(long v) {
        return mul(new KDouble(v));
    }

    @Override
    public KDouble div(int v) {
        return div(new KDouble(v));
    }

    @Override
    public KDouble div(long v) {
        return div(new KDouble(v));
    }
    
    @Override
    public boolean isInteger() {
        return round(v)==v;
    }
    
    @Override
    public int intPart() {
        return (int)floor(v);
    }

    @Override
    public long longPart() {
        return (long)floor(v);
    }
        @Override
    public int compareTo(KDouble o) {
        return Double.compare(v, o.v);
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KDouble)) {
            return false;
        }
        KDouble t = (KDouble) e;
        return v==t.v;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.v) ^ (Double.doubleToLongBits(this.v) >>> 32));
        return hash;
    }
    
    @Override
    public String toString() {
        return Double.toString(v);
    }
}
