/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2l;
import java.util.ListIterator;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PairListIteratorNGTest {
    
    public PairListIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNext() {
        ListIterator<Integer> iter = new PairListIterator<>(a2l(0, 1, 2, 3).listIterator(), a2l(0, 1, 2).listIterator(), (a, b)->a+b, 0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)4);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testPrevious() {
        ListIterator<Integer> iter = new PairListIterator<>(a2l(0, 1, 2, 3).listIterator(), a2l(0, 1, 2).listIterator(), (a, b)->a+b, 0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)4);
        assertEquals(iter.previous(), (Integer)4);
        assertEquals(iter.previous(), (Integer)2);
    }
    
}
