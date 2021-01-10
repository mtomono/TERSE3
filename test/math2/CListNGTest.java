/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CListNGTest {
    
    public CListNGTest() {
    }

    @Test
    public void testAverage() {
    }

    @Test
    public void testSigma() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Integer> b=C.b(new IntegerOp());
        C<Integer> result = new CList<>(TList.sofi(0,1,2,3,4),b).sigma(i->i);
        C<Integer> expected = b.b(10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPai() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Integer> b=C.b(new IntegerOp());
        C<Integer> result = new CList<>(TList.sofi(1,2,3,4),b).pai(i->i);
        C<Integer> expected = b.b(24);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        TList<C<Long>> result = new CList<>(TList.sofi(0,1,2,3,4).map(i->i.longValue()),b).add(i->i);
        TList<C<Long>> expected = TList.sof(0,1,3,6,10).map(i->b.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Double> b=C.b(new DoubleOp());
        TList<C<Double>> result = new CList<>(TList.sofi(1,2,3,4).map(i->i.doubleValue()),b).mul(i->i);
        TList<C<Double>> expected = TList.sof(1,2,6,24).map(i->b.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
