/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class cNGTest {
    
    public cNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testI2l() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = c.i2l(Arrays.asList(0, 1, 2).iterator());
        List<Integer> expected = Arrays.asList(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testL2i() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = c.i2l(c.l2i(Arrays.asList(0, 1, 2)));
        List<Integer> expected = Arrays.asList(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testA2l() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = c.a2l(0, 1, 2);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testA2al() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = c.a2al(0, 1, 2);
        List<Integer> expected = c.a2l(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testL2a() {
        System.out.println(test.TestUtils.methodName(0));
        List<Object> result = c.a2l(c.l2a(c.a2l(0, 1, 2)));
        List<Integer> expected = c.a2l(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testI2a() {
        System.out.println(test.TestUtils.methodName(0));
        List<Object> result = c.a2l(c.i2a(c.a2l(0, 1, 2).iterator()));
        List<Integer> expected = c.a2l(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testA2i() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = c.i2l(c.a2i(0, 1, 2));
        List<Integer> expected = c.a2l(0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testL2aInt() {
        System.out.println(test.TestUtils.methodName(0));
        int[] result = c.l2aInt(c.a2l(0, 1, 2));
        int[] expected = new int[]{0, 1, 2};
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
