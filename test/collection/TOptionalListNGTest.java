/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static collection.c.a2l;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author masao
 */
public class TOptionalListNGTest {
    
    static <T> Optional<T> o(T t) {
        return Optional.of(t);
    }
    
    static <T> Optional<T> e() {
        return Optional.empty();
    }

    public TOptionalListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.set(a2l(o(0), o(1), o(2), o(3)));
        List<Optional<Integer>> expected = a2l(o(0), o(1), o(2), o(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEmpty() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.empty();
        List<Optional<Integer>> expected = Collections.emptyList();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOf_OptionalArr() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TOptionalList.of(o(0), o(1), o(2));
        List<Optional<Integer>> expected = a2l(o(0), o(1), o(2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConcat_ListArr() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.concat(a2l(o(0), o(1), o(2)), a2l(o(3), o(4)));
        List<Optional<Integer>> expected = a2l(o(0), o(1), o(2), o(3), o(4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConcat_List() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.concat(a2l(a2l(o(0), o(1), o(2)), a2l(o(3), o(4))));
        List<Optional<Integer>> expected = a2l(o(0), o(1), o(2), o(3), o(4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFilter() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.of(o(0), o(1), e(), o(2), o(3), o(4)).filter(i->i%3!=0);
        List<Optional<Integer>> expected = a2l(o(1), o(2), o(4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.of(o(0), e(), o(1), o(2)).map(i->i*2);
        List<Optional<Integer>> expected = a2l(o(0), e(), o(2), o(4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFlatMap() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<String> result = TOptionalList.of(o("0,1,2"), e(), o("3,4,5")).flatMap(s->a2l(s.split(",")));
        List<Optional<String>> expected = a2l(o("0"), o("1"), o("2"), o("3"), o("4"), o("5"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<P<Integer, Integer>> result = TOptionalList.of(o(0), o(1), e(), o(2), o(3)).pair(a2l(o(1), o(2), o(3), o(4)));
        List<Optional<P<Integer, Integer>>> expected = a2l(o(P.p(0, 1)), o(P.p(1, 2)), e(), o(P.p(2, 4)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShow() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.of(o(0), o(1), o(2), o(3), e(), o(5), e(), o(7)).show(i->i%3==0);
        List<Optional<Integer>> expected = a2l(o(0), e(), e(), o(3), e(), e(), e(), e());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHide() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.of(o(0), o(1), o(2), o(3), e(), o(5), e(), o(7)).hide(i->i%3==0);
        List<Optional<Integer>> expected = a2l(e(), o(1), o(2), e(), e(), o(5), e(), o(7));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFill() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<Integer> result = TOptionalList.of(o(0), o(1), o(2), o(3)).fill(2, 3);
        List<Optional<Integer>> expected = a2l(o(0), o(1), e(), e(), e(), o(2), o(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRectangle() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Optional<Integer>>> result = TOptionalList.of(o(a2l(0, 1, 2)), o(a2l(3, 4)), e(), o(a2l(5))).rectangle(l->l);
        List<List<Optional<Integer>>> expected = a2l(
                a2l(o(0), o(1), o(2)),
                a2l(o(3), o(4), e()),
                a2l(e(), e(), e()),
                a2l(o(5), e(), e())
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSquare() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Optional<Integer>>> result = TOptionalList.of(o(a2l(0, 1, 2)), o(a2l(3, 4)), e(), o(a2l(5))).square(l->l);
        List<List<Optional<Integer>>> expected = a2l(
                a2l(o(0), o(1), o(2), e()),
                a2l(o(3), o(4), e(), e()),
                a2l(e(), e(), e(), e()),
                a2l(o(5), e(), e(), e())
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSquare2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Optional<Integer>>> result = TOptionalList.of(o(a2l(0, 1, 2)), e()).square(l->l);
        List<List<Optional<Integer>>> expected = a2l(
                a2l(o(0), o(1), o(2)),
                a2l(e(), e(), e()),
                a2l(e(), e(), e())
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNormalize_0args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TOptionalList.of(o(0), o(1), e(), o(2)).normalize();
        List<Integer> expected = a2l(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNormalize_GenericType() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TOptionalList.of(o(0), o(1), e(), o(2)).normalize(100);
        List<Integer> expected = a2l(0, 1, 100, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsList_Function() {
        System.out.println(test.TestUtils.methodName(0));
        TOptionalList<String> result = TOptionalList.of(o(0), o(1), e(), o(2)).asList(t->t.map(o->o.map(i->Optional.of(Integer.toString(i))).orElse(Optional.of("p"))));
        List<Optional<String>> expected = a2l(o("0"), o("1"), o("p"), o("2"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
