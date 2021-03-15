/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class FractionPoweredNGTest {
    
    public FractionPoweredNGTest() {
    }

    @Test
    public void testSimple9800() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(9800,2).simplify();
        FractionPowered expected = new FractionPowered(new Rational(70,1),2,new Rational(1,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimple16() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(16,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(2,1),2,new Rational(1,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimple625() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(625,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(5,1),5,new Rational(1,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimple32() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(32,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(2,1),2,new Rational(2,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimple64() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(64,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(4,1),1,new Rational(0,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSimple32_81() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(32*81,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(6,1),12,new Rational(1,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimple32_243() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(32*243,3).simplify();
        FractionPowered expected = new FractionPowered(new Rational(6,1),6,new Rational(2,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCreate1_2() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(new Rational(1,2),new Rational(1,2)).simplify();
        FractionPowered expected = new FractionPowered(new Rational(1,2),2,new Rational(1,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMulSqrt2Sqrt2() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(2, 2).mul(FractionPowered.create(2, 2)).simplify();
        FractionPowered expected = new FractionPowered(new Rational(2,1),1,Rational.ZERO);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMulSqrt1_2Sqrt1_2() {
        System.out.println(test.TestUtils.methodName(0));
        FractionPowered result = FractionPowered.create(new Rational(1,2), new Rational(1,2)).mul(FractionPowered.create(new Rational(1,2), new Rational(1,2))).simplify();
        FractionPowered expected = new FractionPowered(new Rational(1,2),1,Rational.ZERO);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
