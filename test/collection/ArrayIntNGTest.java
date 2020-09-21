/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.ArrayInt.ArrayIntIterator;
import static collection.ArrayInt.arrayInt;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ArrayIntNGTest {
    
    public ArrayIntNGTest() {
    }

    @Test
    public void testSwap() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).swap(1, 3).asList();
        TList<Integer> expected = TList.sof(0,3,2,1,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMin() {
        System.out.println(test.TestUtils.methodName(0));
        int result = arrayInt(3,2,0,4,1).min(i->i);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMax() {
        System.out.println(test.TestUtils.methodName(0));
        int result = arrayInt(3,2,0,4,1).max(i->i);
        int expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSubArray() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).subArray(1, 3).asList();
        TList<Integer> expected = TList.sofi(1,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSeek_minus() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).seek(-1).asList();
        TList<Integer> expected = TList.sofi(0,1,2,3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReset() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).reset(arrayInt(5,6,7)).asList();
        TList<Integer> expected = TList.sofi(5,6,7,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReset2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).reset(arrayInt(5,6,7),1).asList();
        TList<Integer> expected = TList.sofi(0,5,6,7,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPair() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).pair(arrayInt(5,6,7),(a,b)->a+b).asList();
        TList<Integer> expected = TList.sofi(5,7,9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiff() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).diff(1,(a,b)->b-a).asList();
        TList<Integer> expected = TList.sofi(0,1,2,3,4).diff(1,(a,b)->b-a);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAccumWithSeed() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).accumWithSeed(10,(a,b)->a-b).asList();
        TList<Integer> expected = TList.sofi(0,1,2,3,4).accumWithSeed(10,(a,b)->a-b);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccum() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).accum(10,(a,b)->a-b).asList();
        TList<Integer> expected = TList.sof(0,1,2,3,4).accum(10,(a,b)->a-b);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccumFromStart() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(10,1,2,3,4).accumFromStart(a->a,(a,b)->a-b).asList();
        TList<Integer> expected = TList.sof(10,1,2,3,4).accumFromStart(a->a,(a,b)->a-b);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReverseChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = arrayInt(10,11,1,12,13,14,2,15).reverseChunk(i->i<10).map(a->a.asList());
        TList<TList<Integer>> expected = TList.sof(10,11,1,12,13,14,2,15).reverseChunk(i->i<10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).map(i->i+1).asList();
        TList<Integer> expected = TList.sofi(1,2,3,4,5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFilter() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).filter(i->i%2==0).asList();
        TList<Integer> expected = TList.sofi(0,2,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testCiterator() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayIntIterator iter=ArrayInt.concatIter(arrayInt(0,1,2).iterator(),arrayInt(3,4,5).iterator());
        TList<Integer> result = iter.asArray().asList();
        TList<Integer> expected = TList.sofi(0,1,2,3,4,5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOne() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = ArrayIntIterator.one(1).asArray().asList();
        TList<Integer> expected = TList.sof(1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRangeSubArray() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = ArrayInt.range(0, 10).seek(1).asList();
        TList<Integer> expected = TList.range(1,10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
