/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.P;
import collection.TList;
import static collection.c.a2l;
import static collection.c.i2l;
import java.util.Iterator;
import java.util.List;
import static orderedSet.Builder.intRange;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import walker.Walker;
import static test.TestUtils.methodNamePrint;

/**
 *
 * @author masao
 */
public class RangeWalkerNGTest {
    
    public RangeWalkerNGTest() {
    }

    private static Range<Integer> _b(Integer from, Integer to) {
        return intRange.r(from, to);
    }
    
    @Test
    public void testRr01() {
        methodNamePrint();
        testRrBase(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(_b(7, 8), _b(9, 12), _b(15, 21)), 
                  a2l(_b(0, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)),
                  a2l(_b(7, 8), _b( 7,  8), _b( 9, 12), _b(15, 21), _b(15, 21))
        );
    }

    @Test
    public void testRr02() {
        methodNamePrint();
        testRrBase(a2l(_b(4, 5), _b(10, 15), _b(20, 25), _b(30, 35)), 
                  a2l(_b(0, 2), _b(7, 8), _b(9, 12), _b(15, 21)), 
                  a2l(_b(4, 5), _b(4, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)), 
                  a2l(_b(0, 2), _b(7, 8), _b( 7,  8), _b( 9, 12), _b(15, 21), _b(15, 21))
        );
    }

    @Test
    public void testRr03() {
        methodNamePrint();
        testRrBase(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)), 
                  a2l(_b(1, 3), _b(7, 8), _b(9, 12), _b(15, 21)), 
                  a2l(_b(0, 5), _b(0, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)), 
                  a2l(_b(1, 3), _b(7, 8), _b( 7,  8), _b( 9, 12), _b(15, 21), _b(15, 21))
        );
    }

    public void testRrBase(List<Range<Integer>> leftIn, List<Range<Integer>> rightIn, List<Range<Integer>> leftOut, List<Range<Integer>> rightOut) {
        Walker<Range<Integer>, Range<Integer>> tested = new WalkerRr<>(leftIn.iterator(), rightIn.iterator());
        List<P<Range<Integer>, Range<Integer>>> result = i2l(tested);
        List<P<Range<Integer>, Range<Integer>>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRr04() {
        methodNamePrint();
        testRangeOverlap(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(_b(7, 8), _b(9, 12), _b(15, 21)), 
                  a2l(_b(10, 15), _b(20, 25)),
                  a2l(_b( 9, 12), _b(15, 21))
        );
    }

    public void testRangeOverlap(List<Range<Integer>> leftIn, List<Range<Integer>> rightIn, List<Range<Integer>> leftOut, List<Range<Integer>> rightOut) {
        Walker<Range<Integer>, Range<Integer>> tested = new WalkerRr<>(leftIn.iterator(), rightIn.iterator()).overlap();
        List<P<Range<Integer>, Range<Integer>>> result = i2l(tested);
        List<P<Range<Integer>, Range<Integer>>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRr05() {
        methodNamePrint();
        testRrIntersect(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(_b(7, 8), _b(9, 12), _b(15, 21)), 
                  a2l(_b(10, 12), _b(20, 21))
        );
    }

    public void testRrIntersect(List<Range<Integer>> leftIn, List<Range<Integer>> rightIn, List<Range<Integer>> out) {
        Iterator<Range<Integer>> tested = new WalkerRr<>(leftIn.iterator(), rightIn.iterator()).intersect();
        List<Range<Integer>> result = i2l(tested);
        List<Range<Integer>> expected = out;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRp01() {
        methodNamePrint();
        testRpBase(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(7, 12, 21),
                  a2l(_b(0, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)),
                  a2l(7,        7,          12,         21,         21)
        );
    }

    @Test
    public void testRp02() {
        methodNamePrint();
        testRpBase(a2l(_b(4, 5), _b(10, 15), _b(20, 25), _b(30, 35)), 
                  a2l(0       , 7         , 10        , 20), 
                  a2l(_b(4, 5), _b(4, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)), 
                  a2l(0       , 7       , 7         , 10         , 20       , 20)
        );
    }

    @Test
    public void testRp03() {
        methodNamePrint();
        testRpBase(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(1       , 7         , 11        , 20),
                  a2l(_b(0, 5), _b(0, 5), _b(10, 15), _b(10, 15), _b(10, 15), _b(20, 25)),
                  a2l(1       , 7       , 7         , 11        , 20        , 20)
        );
    }

    public void testRpBase(List<Range<Integer>> leftIn, List<Integer> rightIn, List<Range<Integer>> leftOut, List<Integer> rightOut) {
        Walker<Range<Integer>, Integer> tested = new WalkerRp<>(leftIn.iterator(), rightIn.iterator());
        List<P<Range<Integer>, Integer>> result = i2l(tested);
        List<P<Range<Integer>, Integer>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRp04() {
        methodNamePrint();
        testRpOverlap(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(1       , 7         , 11        , 20),
                  a2l(_b(0, 5), _b(10, 15), _b(20, 25)),
                  a2l(1       , 11        , 20)
        );
    }

    public void testRpOverlap(List<Range<Integer>> leftIn, List<Integer> rightIn, List<Range<Integer>> leftOut, List<Integer> rightOut) {
        Walker<Range<Integer>, Integer> tested = new WalkerRp<>(leftIn.iterator(), rightIn.iterator()).overlap();
        List<P<Range<Integer>, Integer>> result = i2l(tested);
        List<P<Range<Integer>, Integer>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRp05() {
        methodNamePrint();
        testRpIntersect(a2l(_b(0, 5), _b(10, 15), _b(20, 25), _b(30, 35)),
                  a2l(1       , 7         , 11        , 20),
                  a2l(1       , 11        , 20)
        );
    }

    public void testRpIntersect(List<Range<Integer>> leftIn, List<Integer> rightIn, List<Integer> out) {
        Iterator<Integer> tested = new WalkerRp<>(leftIn.iterator(), rightIn.iterator()).intersect();
        List<Integer> result = i2l(tested);
        List<Integer> expected = out;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPp01() {
        methodNamePrint();
        testPpBase(a2l(0, 10, 12, 30),
                   a2l(7, 12, 21),
                   a2l(0, 10, 10, 12, 12, 30),
                   a2l(7, 7, 12, 12, 21, 21)
        );
    }

    @Test
    public void testPp02() {
        methodNamePrint();
        testPpBase(a2l(4, 10, 20, 30), 
                   a2l(0, 7, 10, 20), 
                   a2l(4, 4, 10, 10, 10, 20), 
                   a2l(0, 7, 7, 10, 20, 20)
        );
    }

    @Test
    public void testPp03() {
        methodNamePrint();
        testPpBase(a2l(1, 10, 20, 30),
                   a2l(1, 7, 11, 20),
                   a2l(1, 1, 10, 10, 20, 20),
                   a2l(1, 7, 7, 11, 11, 20)
        );
    }

    public void testPpBase(List<Integer> leftIn, List<Integer> rightIn, List<Integer> leftOut, List<Integer> rightOut) {
        Walker<Integer, Integer> tested = new WalkerPp<>(leftIn.iterator(), rightIn.iterator());
        List<P<Integer, Integer>> result = i2l(tested);
        List<P<Integer, Integer>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPp04() {
        methodNamePrint();
        testPpOverlap(a2l(1, 10, 20, 30),
                      a2l(1, 7, 11, 20),
                      a2l(1, 20),
                      a2l(1, 20)
        );
    }

    public void testPpOverlap(List<Integer> leftIn, List<Integer> rightIn, List<Integer> leftOut, List<Integer> rightOut) {
        Walker<Integer, Integer> tested = new WalkerPp<>(leftIn.iterator(), rightIn.iterator()).overlap();
        List<P<Integer, Integer>> result = i2l(tested);
        List<P<Integer, Integer>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPp05() {
        methodNamePrint();
        testPpIntersect(a2l(1, 10, 20, 30),
                        a2l(1, 7, 11, 20),
                        a2l(1, 20)
        );
    }

    public void testPpIntersect(List<Integer> leftIn, List<Integer> rightIn, List<Integer> out) {
        Iterator<Integer> tested = new WalkerPp(leftIn.iterator(), rightIn.iterator()).intersect();
        List<Integer> result = i2l(tested);
        List<Integer> expected = out;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
