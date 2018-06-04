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

package shapeCollection;

import static arithmetic.Arithmetic.ceil;
import static arithmetic.Arithmetic.div;
import static java.lang.Math.abs;
import static java.lang.Math.signum;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import static shape.ShapeUtil.err;

/**
 *
 * @author mtomono
 */
public class ScaledAxis extends AbstractList<Double> {
    double pitch;
    double from;
    double to;
    double zero;
    int fromStep;
    int toStep;
    int size;
    double dir;
    
    public ScaledAxis(double zero, double pitch, double from, double to) {
        assert pitch>0;
        this.zero = zero;
        this.pitch = pitch;
        this.from = from;
        this.to = to;
        this.dir = signum(to-from);
        this.fromStep = step(this.from);
        this.toStep = step(this.to);
        this.size = toStep-fromStep;
    }
        
    final int step(double value) {
        return (int)ceil.o(div.o(value-zero, pitch*dir()));
    }

    @Override
    public Double get(int index) {
        if (index<0||index>size())
            throw new NoSuchElementException("Scale#get()");
        return zero+(fromStep+index)*pitch*dir();
    }

    @Override
    public int size() {
        return size;
    }
    
    public double dir() {
        return dir;
    }
    
    public ScaledAxis shift(double diff) {
        return new ScaledAxis(zero+diff, pitch, from+diff, to+diff);
    }
    
    public ScaledAxis scale(double rate) {
        return new ScaledAxis(zero*rate, pitch*abs(rate), from*rate, to*rate);
    }
    
    public ScaledAxis fit(double newFrom, double newTo) {
        if (abs(to-from) < err)
            return this;
        return shift(newFrom-from).scale((newTo-newFrom)/(to-from));
    }
    
    @Override
    public String toString() {
        return "zero:"+zero+",pitch:"+pitch+",from:"+from+",to:"+to+",dir:"+dir+",fromStep:"+fromStep+",toStep:"+toStep+",size:"+size;
    }
}
