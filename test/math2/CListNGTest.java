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
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Double> b=C.b(new DoubleOp());
        C<Double> result = CList.c(b, TList.sofi(10,11,12,13,4).map(i->(double)i)).average();
        C<Double> expected = b.b(10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSigma() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Integer> b=C.b(new IntegerOp());
        C<Integer> result = CList.c(b, TList.sofi(0,1,2,3,4)).sigma();
        C<Integer> expected = b.b(10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPai() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Integer> b=C.b(new IntegerOp());
        C<Integer> result = CList.c(b, TList.sofi(1,2,3,4)).pai();
        C<Integer> expected = b.b(24);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd_CListSimpler() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        CList<Long> result = CList.c(b, TList.sofi(0,1,2,3,4).map(i->i.longValue())).add(TList.sofi(0,1,2,3,4).map(i->i.longValue()));
        CList expected = CList.c(b, TList.sof(0,2,4,6,8).map(i->i.longValue()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul_CListSimpler() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        CList<Long> result = CList.c(b, TList.sofi(0,1,2,3,4).map(i->i.longValue())).mul(TList.sofi(0,1,2,3,4).map(i->i.longValue()));
        CList expected = CList.c(b, TList.sof(0,1,4,9,16).map(i->i.longValue()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSub_CListSimpler() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        CList<Long> result = CList.c(b, TList.sofi(0,1,2,3,4).map(i->i.longValue())).sub(TList.sofi(0,1,2,3,4).map(i->i.longValue()));
        CList expected = CList.c(b, TList.sof(0,0,0,0,0).map(i->i.longValue()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiv_CListSimpler() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        TList<C<Long>> result = CList.c(b, TList.sofi(0,1,2,3,4).map(i->i.longValue())).div(CList.c(b, TList.sofi(1,1,2,3,4).map(i->i.longValue()))).body();
        TList<C<Long>> expected = TList.sof(0,1,1,1,1).map(i->b.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Long> b=C.b(new LongOp());
        CList<Long> result = CList.c(b, TList.sofi(0,1,2,3,4).map(i->i.longValue())).add();
        CList<Long> expected = CList.c(b, TList.sof(0,1,3,6,10).map(i->i.longValue()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMul() {
        System.out.println(test.TestUtils.methodName(0));
        C.Builder<Double> b=C.b(new DoubleOp());
        TList<C<Double>> result = CList.c(b, TList.sofi(1,2,3,4).map(i->i.doubleValue())).mul().body();
        TList<C<Double>> expected = TList.sof(1,2,6,24).map(i->b.b(i));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
