/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import static collection.c.i2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ContextReverseChunkIteratorNGTest {
    
    public ContextReverseChunkIteratorNGTest() {
    }

    @Test
    public void testChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(0,1,100,3,4,100,6,7);
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk01() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(100,0,1,100,3,4,100,6,7);
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk02() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(0,1,100,100,3,4,100,6,7,100);
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk03() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(0,1,3,4,6,7);
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk04() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(0,1,3,4,6,7,100);
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk05() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof();
        List<List<Integer>> result = i2l(new ContextReverseChunkIterator<>(tested.iterator(),i->i>10));
        List<TList<Integer>> expected = tested.reverseChunk(i->i>10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
