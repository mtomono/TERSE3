/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shape.Rect2i;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.p2is;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class TList_Reverse_SpecificNGTest {
    
    public TList_Reverse_SpecificNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    @Test
    public void testReenact() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(p2i(2,-10),p2i(4,-5)).twoToOne().reverse();
        TList<TPoint2i> expected = p2is(2,-10, 3,-10, 4,-10, 4,-9, 4,-8, 4,-7, 4,-6, 4,-5);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testSimplify0_negative() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = p2is(2,-10, 3,-10, 4,-10, 4,-9, 4,-8, 4,-7, 4,-6, 4,-5).reverse().fix().reverse();
        TList<TPoint2i> expected = p2is(2,-10, 3,-10, 4,-10, 4,-9, 4,-8, 4,-7, 4,-6, 4,-5);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testSimplify1_negative() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(p2i(2,-10),p2i(4,-5)).edge2().reverse();
        TList<TPoint2i> expected = p2is(4,-10, 4,-9, 4,-8, 4,-7, 4,-6, 4,-5);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimplify2_negative() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(0,10).reverse();
        TList<Integer> expected = TList.rangeSym(9,0);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    // this problem has been fixed by letting ShiftedScale to implement RandomAccess
    @Test
    public void testSimplify3_positive() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(0,5).append(TList.range(5,10)).reverse();
        TList<Integer> expected = TList.rangeSym(9,0);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(result.get(9));
    }

    // this problem has been fixed by letting ShiftedScale to implement RandomAccess
    @Test
    public void testSimplify4_positive() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0,1,2,3,4).append(TList.range(5,10)).reverse();
        TList<Integer> expected = TList.rangeSym(9,0);
        System.out.println(result.get(0));
        System.out.println(result.get(1));
        System.out.println(result.get(2));
        System.out.println(result.get(3));
        System.out.println(result.get(4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
        System.out.println(result.get(9));
    }
    
    @Test
    public void testSimplify5_negative() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4,5,6,7,8,9).fix2seq().reverse().get(9);
        Integer expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimplify6_negative() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).get(0);
        Integer expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimplify7_positive() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).reverse().get(9);
        Integer expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimplify8_positive() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).listIterator(6).previous();
        Integer expected = 5;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name = "previous")
    public Object[][] previous() {
        return new Object[][]{
            {1,0},
            {2,1},
            {3,2},
            {4,3},
            {5,4},
            {6,5},
            {7,6},
            {8,7},
            {9,8},
            {10,9},
        };
    }

    @Test(dataProvider = "previous")
    public void testSimplify81_positive(Integer index, Integer value) {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).listIterator(index).previous();
        Integer expected = value;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSimplify10_positive() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).listIterator(5).previous();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name = "next")
    public Object[][] next() {
        return new Object[][]{
            {0,0},
            {1,1},
            {2,2},
            {3,3},
            {4,4},
            {5,5},
            {6,6},
            {7,7},
            {8,8},
            {9,9},
        };
    }

    @Test(dataProvider = "next")
    public void testSimplify82_positive(Integer index, Integer value) {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.of(0,1,2,3,4).append(TList.of(5,6,7,8,9).fix2seq()).listIterator(index).next();
        Integer expected = value;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
