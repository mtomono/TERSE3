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

/**
 *
 * @author masao
 */
public class DoubleK implements Decimal<DoubleK> {
    public final double body;
    
    static public final DoubleK ZERO = new DoubleK(0);
    static public final DoubleK ONE = new DoubleK(0);
    
    public DoubleK(double body) {
        this.body = body;
    }

    @Override
    public DoubleK add(DoubleK value) {
        return new DoubleK(body+value.body);
    }

    @Override
    public DoubleK sub(DoubleK value) {
        return new DoubleK(body-value.body);
    }

    @Override
    public DoubleK mul(DoubleK value) {
        return new DoubleK(body*value.body);
    }

    @Override
    public DoubleK div(DoubleK value) {
        return new DoubleK(body/value.body);
    }

    @Override
    public DoubleK zero() {
        return ZERO;
    }
    
    @Override
    public DoubleK one() {
        return ONE;
    }

    @Override
    public DoubleK negate() {
        return new DoubleK(-body);
    }

    @Override
    public DoubleK abs() {
        return new DoubleK(Math.abs(body));
    }

    @Override
    public DoubleK mul(int v) {
        return mul(v);
    }

    @Override
    public DoubleK mul(long v) {
        return mul(v);
    }

    @Override
    public DoubleK div(int v) {
        return div(v);
    }

    @Override
    public DoubleK div(long v) {
        return div(v);
    }

    @Override
    public int compareTo(DoubleK o) {
        return Double.compare(body, o.body);
    }
}
