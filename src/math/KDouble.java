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
    public final double body;
    
    static public final KDouble ZERO = new KDouble(0);
    static public final KDouble ONE = new KDouble(0);
    
    public KDouble(double body) {
        this.body = body;
    }

    @Override
    public KDouble add(KDouble value) {
        return new KDouble(body+value.body);
    }

    @Override
    public KDouble sub(KDouble value) {
        return new KDouble(body-value.body);
    }

    @Override
    public KDouble mul(KDouble value) {
        return new KDouble(body*value.body);
    }

    @Override
    public KDouble div(KDouble value) {
        return new KDouble(body/value.body);
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
        return new KDouble(-body);
    }

    @Override
    public KDouble abs() {
        return new KDouble(Math.abs(body));
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
        return round(body)==body;
    }
    
    @Override
    public int getIntegerPart() {
        return (int)floor(body);
    }

    @Override
    public long getLongPart() {
        return (long)floor(body);
    }
    
    @Override
    public int compareTo(KDouble o) {
        return Double.compare(body, o.body);
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
        return body==t.body;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.body) ^ (Double.doubleToLongBits(this.body) >>> 32));
        return hash;
    }
    
    @Override
    public String toString() {
        return Double.toString(body);
    }
}
