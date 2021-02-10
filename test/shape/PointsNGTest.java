/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import math2.C;
import math2.CList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PointsNGTest {
    
    public PointsNGTest() {
    }

    @Test
    public void testRectangle_CList_CList() {
    }

    @Test
    public void testRectangle_Rectangle() {
    }

    @Test
    public void testMerge_TList() {
    }

    @Test
    public void testMerge_Points() {
    }

    @Test
    public void testDiff() {
        System.out.println(test.TestUtils.methodName(0));
        CList<Integer> result = Points.c(C.i, 2, -2, -1, 2, 1).diff().get(0);
        CList<Integer> expected = CList.c(C.i,4,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMin() {
        System.out.println(test.TestUtils.methodName(0));
        CList<Integer> result = Points.c(C.i, 2, -2, 1, 2, -1).min().get();
        CList<Integer> expected = CList.c(C.i,-2,-1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMax() {
        System.out.println(test.TestUtils.methodName(0));
        CList<Integer> result = Points.c(C.i, 2, -2, 1, 2, -1).max().get();
        CList<Integer> expected = CList.c(C.i,2,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRect() {
        System.out.println(test.TestUtils.methodName(0));
        Points<Integer> result = Points.c(C.i, 2, -2, 1, 2, -1).rect().get();
        Points<Integer> expected = Points.c(C.i,2,-2,-1,2,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
