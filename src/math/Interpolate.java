/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.math.BigDecimal;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class Interpolate {
    static public double interpolateD(Range<Double> range, double ratio) {
        return interpolateD(range.start(),range.end(),ratio);
    }
    
    static public BigDecimal interpolateB(Range<BigDecimal> range, BigDecimal ratio) {
        return interpolateB(range.start(),range.end(),ratio);
    }
    
    static public double interpolateD(double from, double to,double ratio) {
        return from*(1-ratio)+to*ratio;
    }
    
    static public BigDecimal interpolateB(BigDecimal from, BigDecimal to, BigDecimal ratio) {
        return from.multiply(BigDecimal.ONE.subtract(ratio)).add(to.multiply(ratio));
    }
}
