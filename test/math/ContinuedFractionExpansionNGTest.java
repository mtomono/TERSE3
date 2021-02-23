/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static java.lang.Math.sqrt;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ContinuedFractionExpansionNGTest {
    
    public ContinuedFractionExpansionNGTest() {
    }

    @Test
    public void testCfe43IntPart() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result=new CFE<Double>().exec(C2.d.b(1.33333333333333333333),1.0e-9).intPart();
        TList<Integer> expected = TList.sofi(1,3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfe43Fraction() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result=new CFE<Double>().exec(C2.d.b(1.33333333333333333333),1.0e-9).fraction().last();
        Rational expected = new Rational(4,3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfe17Fraction() {
        System.out.println(test.TestUtils.methodName(0));
        Rational result=new CFE<Double>().exec(C2.d.b(0.14285714285),1.0e-9).fraction().last();
        Rational expected = new Rational(1,7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfeSqrt2IntPart() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result=new CFE<Double>().exec(C2.d.b(sqrt(2)),1.0e-9).intPart();
        TList<Integer> expected = TList.sofi(1,2,2,2,2,2,2,2,2,2,2,2,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfeSqrt2Fraction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Rational> result=new CFE<Double>().exec(C2.d.b(sqrt(2)),1.0e-9).fraction();
        TList<Rational> expected = TList.sof("1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, 8119/5741, 19601/13860, 47321/33461".split(", ")).map(s->Rational.parseRational(s));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfeRational3217IntPart() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result=new CFE<Rational>().exec(C2.r.b("32/17"),Rational.parseRational("1/1000000000")).intPart();
        TList<Integer> expected = TList.sofi(1,1,7,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testCfeRational3217Fraction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Rational> result=new CFE<Rational>().exec(C2.r.b("32/17"),Rational.parseRational("1/1000000000")).fraction();
        TList<Rational> expected = TList.sof("1, 2, 15/8, 32/17".split(", ")).map(s->Rational.parseRational(s));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
