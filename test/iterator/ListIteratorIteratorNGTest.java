/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2l;
import static collection.c.i2l;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class ListIteratorIteratorNGTest {
    
    public ListIteratorIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    public List<Integer> nextN(ListIterator<Integer> iter, int n) {
        List<Integer> retval = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            assert iter.hasNext();
            retval.add(iter.next());
        }
        return retval;
    }

    public List<Integer> prevN(ListIterator<Integer> iter, int n) {
        List<Integer> retval = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            assert iter.hasPrevious();
            retval.add(iter.previous());
        }
        return retval;
    }

    public void check(ListIterator<Integer> iter) {
        testCase1(iter);
        testCase2(iter);
        testCase3(iter);
        testCase4(iter);
    }
    
    public void testCase1(ListIterator<Integer> iter) {
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    public void testCase2(ListIterator<Integer> iter) {
        List<Integer> result = prevN(iter, 5);
        List<Integer> expected = a2l(7, 6, 5, 4, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    public void testCase3(ListIterator<Integer> iter) {
        List<Integer> result = nextN(iter, 4);
        List<Integer> expected = a2l(3, 4, 5, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    public void testCase4(ListIterator<Integer> iter) {
        List<Integer> result = prevN(iter, 7);
        List<Integer> expected = a2l(6, 5, 4, 3, 2, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        check(ListIteratorIterator.create(a2l(a2l(0, 1, 2), a2l(3, 4, 5), a2l(6, 7))));
    }
    
    @Test
    public void testNav2() {
        System.out.println(methodName(0));
        check(ListIteratorIterator.create(a2l(a2l(0, 1, 2), a2l(3, 4, 5), a2l(6, 7)), 0));
    }

    @Test
    public void testWithEmptyList() {
        System.out.println(methodName(0));
        check(ListIteratorIterator.create(a2l(a2l(), a2l(0, 1, 2, 3, 4, 5), a2l(6, 7))));
    }

    @Test
    public void testWithEmptyList2() {
        System.out.println(methodName(0));
        check(ListIteratorIterator.create(a2l(a2l(0, 1, 2, 3, 4, 5), a2l(), a2l(6, 7))));
    }

    @Test
    public void testReverse() {
        System.out.println(methodName(0));
        ListIterator<Integer> iter = ListIteratorIterator.create(a2l(a2l(7, 6, 5), a2l(4, 3, 2), a2l(1, 0)), 8);
        check(new ReverseIterator<>(iter));
    }
}
