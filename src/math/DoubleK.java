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
public class DoubleK implements FieldM<DoubleK> {
    public final double body;
    
    static public final DoubleK zero = new DoubleK(0);
    static public final DoubleK one = new DoubleK(0);
    
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
        return zero;
    }
    
    @Override
    public DoubleK one() {
        return one;
    }
}
