/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import static collection.c.i2l;
import iterator.ReverseIterator;
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
public class FilterListNGTest {
    
    public FilterListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        List<Integer> result = new FilterList<>(a2l(0, 1, 2, 3, 4, 5), e->e%2==0);
        List<Integer> expected = a2l(0, 2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNav2() {
        System.out.println(methodName(0));
        List<Integer> target = new FilterList<>(a2l(0, 1, 2, 3, 4, 5), e->e%2==0);
        List<Integer> result = i2l(target.listIterator(1));
        List<Integer> expected = a2l(2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    

    @Test
    public void testNav3() {
        System.out.println(methodName(0));
        List<Integer> target = new FilterList<>(a2l(0, 1, 2, 3, 4, 5), e->e%2==0);
        List<Integer> result = i2l(target.listIterator(3));
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNav4() {
        System.out.println(methodName(0));
        List<Integer> target = new FilterList<>(a2l(0, 1, 2, 3, 4, 5), e->e%2==0);
        List<Integer> result = i2l(new ReverseIterator(target.listIterator(3)));
        List<Integer> expected = a2l(4, 2, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    

    @Test
    public void testSubList() {
        System.out.println(methodName(0));
        List<List<Integer>> target = a2l(a2l(0, 1, 2), a2l(3, 4, 5), a2l(6, 7));
        ListIterator<List<Integer>> iter = new FilterList<>(target, l->!l.isEmpty()).subList(1, 3).listIterator();
        List<List<Integer>> result = i2l(iter);
        List<List<Integer>> expected = a2l(a2l(3, 4, 5), a2l(6, 7));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
