/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.i2l;
import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static collection.TIterable.set;
import static collection.TIterable.range;
import function.IntHolder;
import iterator.TIterator;
import java.util.Iterator;
import static collection.TIterable.generate;
import static collection.TIterable.iterate;
import static collection.TIterable.of;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class TIterableNGTest {
    
    public TIterableNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testSeti() {
        System.out.println(methodName(0));
        check3(set(a2l(1, 2, 3, 4, 5)));
    }
    
    public void check3(Iterable<Integer> i) {
        check(i.iterator());
        check(i.iterator());
        check(i.iterator());
    }
    
    public void check(Iterator<Integer> iter) {
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOf() {
        System.out.println(methodName(0));
        check3(of(1, 2, 3, 4, 5));
    }

    @Test
    public void testGenerate() {
        System.out.println(methodName(0));
        check3(generate(()->{
            IntHolder h = new IntHolder(1);
            return ()->h.push(h.get()+1);
        }).limit(5));
    }
    
    @Test
    public void testIterate() {
        System.out.println(methodName(0));
        check3(iterate(1, i->i+1).limit(5));
    }    

    @Test
    public void testRange() {
        System.out.println(methodName(0));
        check3(range(1, 6));
    }

    @Test
    public void testConcat() {
        System.out.println(methodName(0));
        check3(range(1, 3).concat(range(3, 6)));
    }

    @Test
    public void testMap() {
        System.out.println(methodName(0));
        check3(range(7, 12).map(e->e-6));
    }

    @Test
    public void testFilter() {
        System.out.println(methodName(0));
        check3(range(-25, 25).filter(e->1<=e&&e<=5));
    }

    @Test
    public void testFlatMap() {
        System.out.println(methodName(0));
        check3(of(range(1, 3), range(3, 4), range(4, 6)).flatMap(l->l));
    }

    @Test
    public void testWeave() {
        System.out.println(methodName(0));
        check3(of(of(1, 4), of(2, 5), of(3)).weave(e->e));
    }

    @Test
    public void testTwist() {
        System.out.println(methodName(0));
        check3(of(1, 3, 5).twist(of(2, 4)));
    }

    @Test
    public void testBefore() {
        System.out.println(methodName(0));
        check3(range(1, 20).before(e->e==6));
    }

    @Test
    public void testUntil() {
        System.out.println(methodName(0));
        check3(range(1, 20).until(e->e==5));
    }

    @Test
    public void testSeek_Predicate() {
        System.out.println(methodName(0));
        check3(range(-20, 6).seek(e->e==1));
    }

    @Test
    public void testSeek_int() {
        System.out.println(methodName(0));
        check3(range(-20, 6).seek(21));
    }

    @Test
    public void testLimit() {
        System.out.println(methodName(0));
        check3(range(1, 20).limit(5));
    }

    @Test
    public void testDelimit() {
        System.out.println(methodName(0));
        check3(of(1, 3, 5).delimit(()->TIterator.of(2, 4).supplier()));
    }

    @Test
    public void testDiffIterator() {
        System.out.println(methodName(0));
        TIterable<Integer> i = of(1, 2, 3, 4, 5);
        Iterator<P<Integer, Integer>> iter = i.diffIterator(i.iterator(), 2);
        List<P<Integer, Integer>> result = i2l(iter);
        List<P<Integer, Integer>> expected = a2l(P.p(1, 3), P.p(2, 4), P.p(3, 5));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    public void checkP(Iterable<P<Integer, Integer>> i) {
        checkPiter(i.iterator());
        checkPiter(i.iterator());
        checkPiter(i.iterator());   
    }
    
    public void checkPiter(Iterator<P<Integer, Integer>> iter) {
        List<P<Integer, Integer>> result = i2l(iter);
        List<P<Integer, Integer>> expected = a2l(P.p(1, 2), P.p(2, 3), P.p(3, 4), P.p(4, 5));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiff_0args() {
        System.out.println(methodName(0));
        checkP(range(1, 6).diff());
    }

    @Test
    public void testDiff_int() {
        System.out.println(methodName(0));
        checkP(range(1, 6).diff(1));
    }

    @Test
    public void testDiff_TIterable() {
        System.out.println(methodName(0));
        checkP(range(1, 6).diff(range(1, 6)));
    }

    @Test
    public void testDiff_TIterable_int() {
        System.out.println(methodName(0));
        checkP(range(1, 6).diff(range(1, 6), 1));
    }

    @Test
    public void testPair() {
        System.out.println(methodName(0));
        checkP(range(1, 5).pair(range(2, 6)));
    }

    @Test
    public void testPmap() {
        System.out.println(methodName(0));
        checkP(range(1, 5).pmap(e->e+1));
    }

    @Test
    public void testAccum() {
        System.out.println(methodName(0));
        check3(generate(()->()->1).limit(4).accum(1, (a, b)->a+b));
    }

    @Test
    public void testRepeat() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = of(1, 2, 3).repeat().iterator().limit(7);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 2, 3, 1, 2, 3, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffP() {
        System.out.println(methodName(0));
        Iterable<P<P<Integer, Integer>, Integer>> i = of(1, 2, 3, 4, 5).diff().pmap(p->p.r()-p.l());
        List<P<P<Integer, Integer>, Integer>> result = i2l(i.iterator());
        List<P<P<Integer, Integer>, Integer>> expected = a2l(P.p(P.p(1, 2), 1), P.p(P.p(2, 3), 1), P.p(P.p(3, 4), 1), P.p(P.p(4, 5), 1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
