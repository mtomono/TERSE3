/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import orderedSet.Range;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class DivideNGTest {
    
    public DivideNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testDivides() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.divides(4, 1);
        TList<TList<Integer>> expected = TList.range(0,5).map(n->TList.of(n));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
        
    Range<Integer> r(int from, int to) {
        return Range.create(from, to);
    }

    @Test
    public void testRanges() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Range<Integer>>> result = Divide.ranges(4, Divide.divides(4, 1));
        TList<TList<Range<Integer>>> expected = TList.of(TList.of(r(0,0),r(0,4)),TList.of(r(0,1),r(1,4)),TList.of(r(0,2),r(2,4)),TList.of(r(0,3),r(3,4)),TList.of(r(0,4),r(4,4)));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDividesAtLeastOne() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.dividesAtLeastOne(4, 1);
        TList<TList<Integer>> expected = TList.range(1,4).map(n->TList.of(n));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testWidth() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.width(4, Divide.divides(4, 1));
        TList<TList<Integer>> expected = TList.of(TList.of(0,4),TList.of(1,3),TList.of(2,2),TList.of(3,1),TList.of(4,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntegerDivide () {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.integerDivide(7, 1, 2);
        TList<TList<Integer>> expected = TList.of(TList.of(2,5),TList.of(3,4),TList.of(4,3),TList.of(5,2));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(Divide.integerDivide(7,1,2));
    }
    
    @Test
    public void testIntegerDivide0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.integerDivide(7, 0, 2);
        TList<TList<Integer>> expected = TList.of(TList.of(7));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(Divide.integerDivide(7,1,2));
    }
    
    @Test
    public void testDividesAtLeast () {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.dividesAtLeast(7, 1, 2);
        TList<TList<Integer>> expected = TList.of(TList.of(2),TList.of(3),TList.of(4),TList.of(5));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(Divide.integerDivide(7,1,2));
    }

    @Test
    public void testDividesAtLeast2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = Divide.dividesAtLeast(7, 2, 2);
        TList<TList<Integer>> expected = TList.of(TList.of(2,4),TList.of(2,5),TList.of(3,5));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(Divide.integerDivide(7,1,2));
    }

    @Test
    public void testDivide_TList_int() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<TList<Integer>>> result = Divide.divide(TList.range(0, 4), 1);
        TList<TList<TList<Integer>>> expected = TList.of(
                                                TList.of(TList.empty(),TList.range(0, 4)),
                                                TList.of(TList.of(0), TList.range(1,4)),
                                                TList.of(TList.of(0,1), TList.range(2,4)),
                                                TList.of(TList.range(0,3), TList.of(3)),
                                                TList.of(TList.range(0,4), TList.empty())
                                              );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDivide_3args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<TList<Integer>>> result = Divide.divide(TList.range(0, 4), 1, 1);
        TList<TList<TList<Integer>>> expected = TList.of(
                                                TList.of(TList.of(0), TList.range(1,4)),
                                                TList.of(TList.of(0,1), TList.range(2,4)),
                                                TList.of(TList.range(0,3), TList.of(3))
                                              );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDivide_4args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<TList<Integer>>> result = Divide.divide(4, 0, 1, 1);
        TList<TList<TList<Integer>>> expected = TList.of(
                                                TList.of(TList.of(0), TList.nCopies(3,0)),
                                                TList.of(TList.nCopies(2,0), TList.nCopies(2,0)),
                                                TList.of(TList.nCopies(3,0), TList.of(0))
                                              );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
