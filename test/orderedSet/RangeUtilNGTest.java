/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import java.util.Optional;
import static orderedSet.RangeUtil.inEitherWay;
import static orderedSet.RangeUtil.intersectRI;
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
        TList<Range<Integer>> tested = TList.sof(0,10, 1,11, 0,11).fold(2).map(l->new Range<>(l.get(0),l.get(1)));
        Optional<Range<Integer>> result = intersectRI(tested);
        Optional<Range<Integer>> expected = Optional.of(new Range(1,10));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersectREmpty() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof(0,10, 10,12, 0,11).fold(2).map(l->new Range<>(l.get(0),l.get(1)));
        Optional<Range<Integer>> result = intersectRI(tested);
        Optional<Range<Integer>> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntersectREmptyList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Range<Integer>> tested = TList.sof();
        Optional<Range<Integer>> result = intersectRI(tested);
        Optional<Range<Integer>> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInEitherWay() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> result = inEitherWay(0,1);
        Range<Integer> expected = new Range<>(0,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInEitherWayR() {
        System.out.println(test.TestUtils.methodName(0));
        Range<Integer> result = inEitherWay(1,0);
        Range<Integer> expected = new Range<>(0,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
