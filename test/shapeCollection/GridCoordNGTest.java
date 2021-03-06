/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import java.util.List;
import math.VectorOp;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import shape.TPoint3i;
import static shapeCollection.GridCoord.gcoord;
import static shapeCollection.GridCoord.dirsAround;
import static shapeCollection.GridCoord.dirsAlternate;

/**
 *
 * @author masao
 */
public class GridCoordNGTest {
    
    public GridCoordNGTest() {
    }
    

    @Test
    public void testAllDirs_around() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = dirsAround(3);
        TList<List<Integer>> expected = TList.sof(
                TList.sof(1,0,0),TList.sof(0,1,0),TList.sof(0,0,1),
                TList.sof(-1,0,0),TList.sof(0,-1,0),TList.sof(0,0,-1)
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAllDirs_alt() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = dirsAlternate(3);
        TList<List<Integer>> expected = TList.sof(
                TList.sof(1,0,0),TList.sof(-1,0,0),
                TList.sof(0,1,0),TList.sof(0,-1,0),
                TList.sof(0,0,1),TList.sof(0,0,-1)
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new GridCoord(new GridAxis(10,0)).size();
        int expected = 11;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize2() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new GridCoord(new GridAxis(1,10),new GridAxis(10,0)).size();
        int expected = 110;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize3() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new GridCoord(new GridAxis(0,9),new GridAxis(1,10),new GridAxis(10,0)).size();
        int expected = 1100;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSerialize() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        int result = ge.index(3,5,4);
        int expected = 643;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSerialize2() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        int result = ge.index(3,5,4);
        int expected = 643;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAddress() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        TList<Integer> result = ge.address(643);
        TList<Integer> expected = TList.sof(3,5,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAddress2() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(1,1,10),TList.sof(10,10,1));
        TList<Integer> result = ge.address(643);
        TList<Integer> expected = TList.sof(4,5,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains_LIST_true() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        boolean result = ge.contains(TList.sof(5,5,5));
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains_LIST_false() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        boolean result = ge.contains(TList.sof(5,-1,5));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains_P3_true() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        boolean result = ge.contains(new TPoint3i(5,5,5));
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testContains_P3_false() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        boolean result = ge.contains(new TPoint3i(5,-1,5));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains_GridCoord() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        GridCoord counter = gcoord(TList.sof(0,1,10), TList.sof(9,10,2));
        boolean result = ge.contains(counter);
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testContains_GridCoord2() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,1,10),TList.sof(9,10,1));
        GridCoord counter = gcoord(TList.sof(0,1,10), TList.sof(9,10,0));
        boolean result = ge.contains(counter);
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = gcoord(TList.sof(0,0,0),TList.sof(2,2,2));
        GridCoord counter = gcoord(TList.sof(2,0,0),TList.sof(2,3,3));
        GridCoord result = ge.intersect(counter);
        GridCoord expected = gcoord(2,0,0, 2,2,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridCoord tested = axis.reverse(1);
        int result = tested.index(0,2,0);
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse2() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridCoord tested = axis.reverse(0,2);
        int result = tested.index(2,0,2);
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void test4d() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord coord = GridCoord.gcoord(0,0,0,0, 3,3,3,0);
        TList<List<Integer>> result = coord.addresses();
        TList<List<Integer>> expected = TList.sof(
                0,0,0,0, 1,0,0,0, 2,0,0,0, 3,0,0,0, 
                0,1,0,0, 1,1,0,0, 2,1,0,0, 3,1,0,0,
                0,2,0,0, 1,2,0,0, 2,2,0,0, 3,2,0,0,
                0,3,0,0, 1,3,0,0, 2,3,0,0, 3,3,0,0,
                0,0,1,0, 1,0,1,0, 2,0,1,0, 3,0,1,0, 
                0,1,1,0, 1,1,1,0, 2,1,1,0, 3,1,1,0,
                0,2,1,0, 1,2,1,0, 2,2,1,0, 3,2,1,0,
                0,3,1,0, 1,3,1,0, 2,3,1,0, 3,3,1,0,
                0,0,2,0, 1,0,2,0, 2,0,2,0, 3,0,2,0, 
                0,1,2,0, 1,1,2,0, 2,1,2,0, 3,1,2,0,
                0,2,2,0, 1,2,2,0, 2,2,2,0, 3,2,2,0,
                0,3,2,0, 1,3,2,0, 2,3,2,0, 3,3,2,0,
                0,0,3,0, 1,0,3,0, 2,0,3,0, 3,0,3,0, 
                0,1,3,0, 1,1,3,0, 2,1,3,0, 3,1,3,0,
                0,2,3,0, 1,2,3,0, 2,2,3,0, 3,2,3,0,
                0,3,3,0, 1,3,3,0, 2,3,3,0, 3,3,3,0
        ).fold(4).map(l->l);
        System.out.println("result  : " + result.fold(4).toWrappedString());
        System.out.println("expected: " + expected.fold(4).toWrappedString());
        assertEquals(result, expected);
    }
    
    @Test
    public void test4d2() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord coord = GridCoord.gcoord(0,0,0,1, 3,3,3,1);
        TList<List<Integer>> result = coord.addresses();
        TList<List<Integer>> expected = TList.sof(
                0,0,0,1, 1,0,0,1, 2,0,0,1, 3,0,0,1, 
                0,1,0,1, 1,1,0,1, 2,1,0,1, 3,1,0,1,
                0,2,0,1, 1,2,0,1, 2,2,0,1, 3,2,0,1,
                0,3,0,1, 1,3,0,1, 2,3,0,1, 3,3,0,1,
                0,0,1,1, 1,0,1,1, 2,0,1,1, 3,0,1,1, 
                0,1,1,1, 1,1,1,1, 2,1,1,1, 3,1,1,1,
                0,2,1,1, 1,2,1,1, 2,2,1,1, 3,2,1,1,
                0,3,1,1, 1,3,1,1, 2,3,1,1, 3,3,1,1,
                0,0,2,1, 1,0,2,1, 2,0,2,1, 3,0,2,1, 
                0,1,2,1, 1,1,2,1, 2,1,2,1, 3,1,2,1,
                0,2,2,1, 1,2,2,1, 2,2,2,1, 3,2,2,1,
                0,3,2,1, 1,3,2,1, 2,3,2,1, 3,3,2,1,
                0,0,3,1, 1,0,3,1, 2,0,3,1, 3,0,3,1, 
                0,1,3,1, 1,1,3,1, 2,1,3,1, 3,1,3,1,
                0,2,3,1, 1,2,3,1, 2,2,3,1, 3,2,3,1,
                0,3,3,1, 1,3,3,1, 2,3,3,1, 3,3,3,1
        ).fold(4).map(l->l);
        System.out.println("result  : " + result.fold(4).toWrappedString());
        System.out.println("expected: " + expected.fold(4).toWrappedString());
        assertEquals(result, expected);
    }
}
