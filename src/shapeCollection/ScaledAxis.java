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
import static arithmetic.Arithmetic.floor;
import static java.lang.Math.abs;
import static java.lang.Math.signum;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import static shape.ShapeUtil.err;

/**
 * Axis which are scaled.
 * scale is defined by zero and pitch.
 * starting from the point of zero and pitched regularly.
 * from and to means the range on this pitch.
 * as a list, ScaledAxis contains readings which are between from and to.
 * in that sense, fromStep and toStep form a pseudo-range which means 
 * the range starts from fromStep and range stops right before toStop.
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
    
    /**
     * @param zero
     * @param pitch
     * @param from
     * @param to 
     */
    public ScaledAxis(double zero, double pitch, double from, double to) {
        assert pitch>0 : "pitch for scaled axis has to be positive";
        // if pitch gets negative, size gets negative.
        this.zero = zero;
        this.pitch = pitch;
        this.from = from;
        this.to = to;
        this.dir = signum(to-from);
        this.fromStep = step(this.from);
        this.toStep = step(this.to);
        this.size = abs(toStep-fromStep);
    }
        
    final int step(double value) {
        if (dir > 0)
            return (int)ceil.o(div.o(value-zero, pitch));
        else 
            return (int)floor.o(div.o(value-zero, pitch));
    }
    
    public int start() {
        return fromStep-(dir>0?1:0);
    }

    @Override
    public Double get(int index) {
        if (index<0||size()<index)
            throw new NoSuchElementException("ScaleAxis#get()");
        return zero+(fromStep+index*dir())*pitch;
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
    
    /**
     * scale the axis using from as center.
     * this preserves containing readings.
     * @param rate
     * @return 
     */
    public ScaledAxis scale(double rate) {
        return new ScaledAxis((zero-from)*rate+from, pitch*abs(rate), from, (to-from)*rate+from);
    }
    
    public ScaledAxis fit(double newFrom, double newTo) {
        if (abs(to-from) < err)
            return this;
        return scale((newTo-newFrom)/(to-from)).shift(newFrom-from);
    }
    
    @Override
    public String toString() {
        return "zero:"+zero+",pitch:"+pitch+",from:"+from+",to:"+to+",dir:"+dir+",fromStep:"+fromStep+",toStep:"+toStep+",size:"+size;
    }
}
