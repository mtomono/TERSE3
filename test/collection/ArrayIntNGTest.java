/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

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
        int result = arrayInt(3,2,0,4,1).min(0,5,i->i);
        int expected = 2;
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
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = arrayInt(0,1,2,3,4).map(i->i+1).asList();
        TList<Integer> expected = TList.sofi(1,2,3,4,5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
