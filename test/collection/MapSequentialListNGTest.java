/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2al;
import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class MapSequentialListNGTest {
    
    public MapSequentialListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new MapSequentialList<>(a2l(0, 1, 2), i->i+2);
        List<Integer> expected = a2l(2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new MapSequentialList<>(a2al(0, 1, 2), i->i+2, i->i-2);
        result.remove(1);
        List<Integer> expected = a2l(2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new MapSequentialList<>(a2al(0, 1, 2), i->i+2, i->i-2);
        result.set(1, 5);
        List<Integer> expected = a2l(2, 5, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new MapSequentialList<>(a2al(0, 1, 2), i->i+2, i->i-2);
        result.add(1, 5);
        List<Integer> expected = a2l(2, 5, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
