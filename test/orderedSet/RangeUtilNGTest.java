/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import java.util.Optional;
import static orderedSet.Range.intRange;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class RangeUtilNGTest {
    
    public RangeUtilNGTest() {
    }
    
    @Test
    public void testIntersectR() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 1,11, 0,11).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        Optional<Range<Integer>> result = intRange.intersectMany(tested);
        Optional<Range<Integer>> expected = Optional.of(intRange.r(1,10));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersectREmpty() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 10,12, 0,11).fold(2).map(l->intRange.r(l.get(0),l.get(1)));
        Optional<Range<Integer>> result = intRange.intersectMany(tested);
        Optional<Range<Integer>> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntersectREmptyList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof();
        Optional<Range<Integer>> result = intRange.intersectMany(tested);
        Optional<Range<Integer>> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInEitherWay() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> result = intRange.inEitherWay(0,1);
        Range<Integer> expected = intRange.r(0,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInEitherWayR() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> result = intRange.inEitherWay(1,0);
        Range<Integer> expected = intRange.r(0,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
