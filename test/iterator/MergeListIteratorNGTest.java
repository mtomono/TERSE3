/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2l;
import static collection.c.i2l;
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
public class MergeListIteratorNGTest {
    
    public MergeListIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testIterate1() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(new MergeListIterator<>(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6).listIterator(), (a, b)->a-b));
        List<Integer> expected = a2l(0, 2, 5, 5, 6, 7, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIterate2() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(new MergeListIterator<>(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6, 11, 12).listIterator(), (a, b)->a-b));
        List<Integer> expected = a2l(0, 2, 5, 5, 6, 7, 9, 11, 12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIterate3() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(new MergeListIterator<>(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6, 9).listIterator(), (a, b)->a-b));
        List<Integer> expected = a2l(0, 2, 5, 5, 6, 7, 9, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHasPrevious() {
        ListIterator<Integer> iter = new MergeListIterator<>(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6, 9).listIterator(), (a, b)->a-b);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)0);
    }

    @Test
    public void testHasPrevious1() {
        ListIterator<Integer> iter = MergeListIterator.c(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6, 9).listIterator(), (a, b)->a-b, 0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)0);
    }

        @Test
    public void testHasPrevious2() {
        ListIterator<Integer> iter = MergeListIterator.c(a2l(0, 5, 7, 9).listIterator(), a2l(2, 5, 6, 9).listIterator(), (a, b)->a-b, 2);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.next(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)5);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)0);
    }

}
