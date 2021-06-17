/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.P;
import collection.TList;
import static collection.TList.toTList;
import static collection.c.a2i;
import static collection.c.i2l;
import static collection.c.a2l;
import function.IntHolder;
import static iterator.TIterator.range;
import static iterator.TIterator.set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static iterator.TIterator.generate;
import math.C2;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class TIteratorNGTest {
    
    public TIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        List<List<Integer>> target = a2l(a2l(0, 1, 2, 3), a2l(4, 5, 6), a2l(7, 8, 9));
        Iterator<Integer> iter = set(target.iterator()).flatMap(l->l.iterator()).map(i->i+10).filter(i->i%3 == 0);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(12, 15, 18);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testWeave() {
        System.out.println(methodName(0));
        List<List<Integer>> target = a2l(a2l(0, 1, 2, 3), a2l(4, 5, 6, 7), a2l(8, 9, 10, 11));
        Iterator<Integer> iter = set(target.iterator()).weave(l->l.iterator());
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 4, 8, 1, 5, 9, 2, 6, 10, 3, 7, 11);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testTwist() {
        System.out.println(methodName(0));
        List<Integer> target = a2l(0, 1, 2, 3, 4);
        Iterator<Integer> iter = set(target.iterator()).twist(a2i(5, 5, 5, 5, 5, 5));
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 5, 1, 5, 2, 5, 3, 5, 4, 5, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDelimt() {
        System.out.println(methodName(0));
        List<Integer> target = a2l(0, 1, 2, 3, 4);
        Iterator<Integer> iter = set(target.iterator()).delimit(()->5);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 5, 1, 5, 2, 5, 3, 5, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIterable() {
        System.out.println(methodName(0));
        List<Integer> result = new ArrayList<>();
        for (Integer i : set(a2i(0, 1, 2, 3, 4, 5, 6)).filter(e->e%2==0).i())
            result.add(i);
        List<Integer> expected = a2l(0, 2, 4, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testLimit() {
        System.out.println(methodName(0));
        List<Integer> target = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Iterator<Integer> iter = set(target.iterator()).limit(5);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testLimit2() {
        System.out.println(methodName(0));
        IntHolder h = new IntHolder(0);
        Iterator<Integer> iter = generate(()->h.push(h.get() + 1)).limit(5);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRange() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = range(0, 5);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testUntil() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = range(0, 20).until(i->i == 4);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testBefore() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(range(0, 20).before(i->i==4));
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testConverge() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println("in this sequence, the difference between previous item should be the item itself.");
        List<C2<Double>> result=TIterator.iterate(C2.derr.b(1.0), v->v.div(C2.derr.b(2))).converge(v->v,C2.derr.b(0.0001)).asList();
        List<C2<Double>> expected=TList.sof(1.0, 0.5, 0.25, 0.125, 0.0625, 0.03125, 0.015625, 0.0078125, 0.00390625, 0.001953125, 9.765625E-4, 4.8828125E-4, 2.44140625E-4, 1.220703125E-4, 6.103515625E-5).map(v->C2.derr.b(v));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeek() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(range(0, 20).seek(i->i==15));
        List<Integer> expected = a2l(15, 16, 17, 18, 19);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeekCount() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(range(0, 20).seek(15));
        List<Integer> expected = a2l(15, 16, 17, 18, 19);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
    
    @Test
    public void testSupplier() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = range(0, 5).supplier(()->5).iterator().limit(9);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 5, 5, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiff() {
        System.out.println(methodName(0));
        Iterator<P<Integer, Integer>> iter = range(0, 5).diff();
        List<P<Integer, Integer>> result = i2l(iter);
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(1, 2), P.p(2, 3), P.p(3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffMap() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = range(0, 5).diff().map(p->p.r()-p.l());
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 1, 1, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffWithMap() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = range(0, 5).diff((a,b)->b-a);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 1, 1, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffPMap() {
        System.out.println(methodName(0));
        Iterator<P<P<Integer, Integer>, Integer>> iter = range(0, 5).diff().pmap(p->p.r()-p.l());
        List<P<P<Integer, Integer>, Integer>> result = i2l(iter);
        List<P<P<Integer, Integer>, Integer>> expected = a2l(P.p(P.p(0, 1), 1), P.p(P.p(1, 2), 1), P.p(P.p(2, 3), 1), P.p(P.p(3, 4), 1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiff2() {
        System.out.println(methodName(0));
        List<Integer> l = i2l(range(0, 5));
        Iterator<P<Integer, Integer>> iter = set(l.iterator()).diff(l.iterator());
        List<P<Integer, Integer>> result = i2l(iter);
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(1, 2), P.p(2, 3), P.p(3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair() {
        System.out.println(methodName(0));
        List<Integer> l = i2l(range(0, 5));
        Iterator<P<Integer, Integer>> iter = set(l.iterator()).pair(l.iterator());
        List<P<Integer, Integer>> result = i2l(iter);
        List<P<Integer, Integer>> expected = a2l(P.p(0, 0), P.p(1, 1), P.p(2, 2), P.p(3, 3), P.p(4, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccum() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = generate(()->1).limit(5).accum(1, (a, b)->a+b);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 2, 3, 4, 5, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccum_BinaryOperator() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(2, 5).iterator().accum((a,b)->a+b).stream().collect(toTList());
        TList<Integer> expected = TList.of(2, 5, 9);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLast_Int() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TIterator.range(0, 10).last(3);
        List<Integer> expected = TList.range(7,10);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFlatten() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sof(TList.sof(TList.sof(0,1,2),TList.sof(3,4)),TList.sof(TList.sof(5,6),TList.sof(7,8,9)))
                .iterator().flatten(l->l).flatten(l->l).collect(toTList());
        TList<Integer> expected = TList.sof(0,1,2,3,4,5,6,7,8,9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
