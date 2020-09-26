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
public class TListFilterAndForEachNGTest {
    
    public TListFilterAndForEachNGTest() {
    }
    
    @Test
    public void testCase00() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sofi(4,-1,-1,-1,-1,-1,-1,-1);
        TList.range(2,8).filter(i->result.get(i-2)>0).forEach(i->result.set(i, result.get(i-2)-1));
        TList<Integer> expected = TList.sofi(4,-1,3,-1,2,-1,1,-1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCase00i() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sofi(4,-1,-1,-1,-1,-1,-1,-1);
        TList.range(2,8).iterator().filter(i->result.get(i-2)>0).forEachRemaining(i->result.set(i, result.get(i-2)-1));
        TList<Integer> expected = TList.sofi(4,-1,3,-1,2,-1,1,-1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCase00a() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt result = arrayInt(4,-1,-1,-1,-1,-1,-1,-1);
        ArrayInt.range(2,8).filterIter(i->result.get(i-2)>0).forEach(i->result.set(i, result.get(i-2)-1));
        TList<Integer> expected = TList.sofi(4,-1,3,-1,2,-1,1,-1);
        System.out.println("result  : " + result.asList());
        System.out.println("expected: " + expected);
        assertEquals(result.asList(), expected);
    }

    @Test
    public void testCase00ia() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt result = arrayInt(4,-1,-1,-1,-1,-1,-1,-1);
        ArrayInt.range(2,8).iterator().filter(i->result.get(i-2)>0).forEach(i->result.set(i, result.get(i-2)-1));
        TList<Integer> expected = TList.sofi(4,-1,3,-1,2,-1,1,-1);
        System.out.println("result  : " + result.asList());
        System.out.println("expected: " + expected);
        assertEquals(result.asList(), expected);
    }
}
