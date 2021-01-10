/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import java.util.function.DoubleUnaryOperator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CalculationSpeedNGTest {
    
    public CalculationSpeedNGTest() {
    }

    @Test
    public void testPrimitive() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        for(;result<1000000000;result=result+1+1);
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    static public double addOne(double v) {
        return v+1;
    }
    
    static public Double addOne(Double v) {
        return v+1.0;
    }
    
    @Test
    public void testMethod() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0;
        for(;result<1000000000;result=addOne(addOne(result)));
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    static public DoubleUnaryOperator addOneL(double r) {
        return v->v+r;
    }
    
    @Test
    public void testLambda() {
        System.out.println(test.TestUtils.methodName(0));
        double result = 0.0;
        for(;result<1000000000;result=addOneL(1.0).applyAsDouble(result));
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testLCalc() {
        System.out.println(test.TestUtils.methodName(0));
        DoubleUnaryOperator addOne=addOneL(1).andThen(addOneL(1));
        double result = 0.0;
        for(;result<1000000000;result=addOne.applyAsDouble(result));
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    

    @Test
    public void testBoxing() {
        System.out.println(test.TestUtils.methodName(0));
        Double result = 0.0;
        for(;result<1000000000;result=addOne(result));
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    static class XDouble {
        Double v;
        public XDouble(Double v) {
            this.v=v;
        }
        public XDouble addOne() {
            return new XDouble(v+1.0);
        }
    }
    
    @Test
    public void testXDouble() {
        System.out.println(test.TestUtils.methodName(0));
        XDouble result = new XDouble(0.0);
        for(;result.v<1000000000;result=result.addOne());
        double expected = 1000000000;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.v, expected);
    }

}
