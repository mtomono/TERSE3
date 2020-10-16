/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import collection.ArrayInt.ArrayIntIterator;
import collection.ArrayInt.IntersectIterator;
import collection.ArrayInt.MaskIterator;
import collection.ArrayInt.MergeIterator;
import static collection.ArrayInt.arrayInt;
import static collection.c.i2l;
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
        int result = arrayInt(3,2,0,4,1).minIndex(i->i);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMax() {
        System.out.println(test.TestUtils.methodName(0));
        int result = arrayInt(3,2,0,4,1).maxIndex(i->i);
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
    public void testFilterIter() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).filterIter(i->i%2==0).asArray().asList();
        TList<Integer> expected = TList.sofi(0,2,4);
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

    @Test
    public void testMask0() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(2,3,6,8,12,14,14,15,16,16,16,21);
        ArrayInt tested1=arrayInt(0,4,8,12,16,20);
        TList<Integer> result = new MaskIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(2,3,6,14,14,15,21);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testMask1() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(2,3,6,8,12,14,14,15,16,16,16,21);
        ArrayInt tested1=arrayInt(0,4,8,12,16,20,22,23);
        TList<Integer> result = new MaskIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(2,3,6,14,14,15,21);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMerge00() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(0,5,6,9);
        ArrayInt tested1=arrayInt(1,5,7,10);
        TList<Integer> result = new MergeIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(0,1,5,5,6,7,9,10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMerge01() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(0,5,6,9,10,13);
        ArrayInt tested1=arrayInt(1,5,7,10,14);
        TList<Integer> result = new MergeIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(0,1,5,5,6,7,9,10,10,13,14);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIntersect0() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(0,4,8,12,16,20);
        ArrayInt tested1=arrayInt(2,3,6,8,12,14,14,15,16,16,16,21);
        TList<Integer> result = new IntersectIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(8,12,16);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testIntersect1() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested0=arrayInt(2,3,6,8,12,14,14,15,16,16,16,21);
        ArrayInt tested1=arrayInt(0,4,8,12,16,20);
        TList<Integer> result = new IntersectIterator(tested0.iterator(),tested1.iterator()).asArray().asList();
        TList<Integer> expected = TList.sof(8,12,16);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
