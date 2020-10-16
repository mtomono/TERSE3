/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import static collection.c.i2l;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class MaskIteratorNGTest {
    
    public MaskIteratorNGTest() {
    }

    @Test
    public void testIntersect0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested0=TList.sof(2,3,6,8,12,14,14,15,16,16,16,21);
        TList<Integer> tested1=TList.sof(0,4,8,12,16,20);
        TList<Integer> result = TList.set(i2l(new MaskIterator<>(tested0.iterator(),tested1.iterator(),(a,b)->a-b)));
        TList<Integer> expected = TList.sof(2,3,6,14,14,15,21);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect1() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested0=TList.sof(2,3,6,8,12,14,14,15,16,16,16,21);
        TList<Integer> tested1=TList.sof(0,4,8,12,16,20,22,23);
        TList<Integer> result = TList.set(i2l(new MaskIterator<>(tested0.iterator(),tested1.iterator(),(a,b)->a-b)));
        TList<Integer> expected = TList.sof(2,3,6,14,14,15,21);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
