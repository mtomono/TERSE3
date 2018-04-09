/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2i;
import static collection.c.a2l;
import static collection.c.i2l;
import static collection.c.l2i;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class IteratorIteratorNGTest {
    
    public IteratorIteratorNGTest() {
    }

    @Test
    public void testIterator01() {
        System.out.println("testIterator01");
        List<Integer> result = i2l(new IteratorIterator<Integer>(l2i(a2l(a2i(0, 1, 2), a2i(3, 4, 5), a2i(6, 7)))));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIterator02() {
        System.out.println("testIterator02");
        List<Integer> result = i2l(new IteratorIterator<Integer>(l2i(a2l(a2i(), a2i(0, 1, 2), a2i(), a2i(), a2i(3, 4), a2i()))));
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
