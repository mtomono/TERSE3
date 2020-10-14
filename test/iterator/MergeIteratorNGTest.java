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
public class MergeIteratorNGTest {
    
    public MergeIteratorNGTest() {
    }

    @Test
    public void testMerge00() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested0=TList.sof(0,5,6,9);
        TList<Integer> tested1=TList.sofi(1,5,7,10);
        TList<Integer> result = TList.set(i2l(new MergeIterator<>(tested0.iterator(),tested1.iterator(),(a,b)->a-b)));
        TList<Integer> expected = TList.sof(0,1,5,5,6,7,9,10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMerge01() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested0=TList.sof(0,5,6,9,10,13);
        TList<Integer> tested1=TList.sofi(1,5,7,10,14);
        TList<Integer> result = TList.set(i2l(new MergeIterator<>(tested0.iterator(),tested1.iterator(),(a,b)->a-b)));
        TList<Integer> expected = TList.sof(0,1,5,5,6,7,9,10,10,13,14);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
