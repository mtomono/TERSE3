/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2i;
import java.util.List;
import static collection.c.a2l;
import static collection.c.i2l;
import java.util.Collections;
import java.util.Iterator;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class WeavedIteratorNGTest {
    
    public WeavedIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNavigate1() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = new WeaveIterator<>(a2l(a2i(0, 3, 6, 9), a2i(1, 4, 7, 10), a2i(2, 5, 8, 11)));
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNavigate2() {
        System.out.println(methodName(0));
        Iterator<Integer> twice = a2i(1, 2, 4, 5, 7, 8, 10, 11);
        Iterator<Integer> iter = new WeaveIterator<>(a2l(a2i(0, 3, 6, 9), twice, twice));
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testInterweave() {
        System.out.println(methodName(0));
        List<Integer> target = a2l(1, 2, 3);
        Iterator<Integer> iter = new WeaveIterator<>(target.iterator(), Collections.nCopies(target.size()-1, 0).iterator());
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 0, 2, 0, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDelimiter() {
        System.out.println(methodName(0));
        List<Integer> target = a2l(1, 2, 3);
        Iterator<Integer> iter = WeaveIterator.delimiter(target, 0);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 0, 2, 0, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
