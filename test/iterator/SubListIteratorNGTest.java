/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Arrays;
import java.util.ListIterator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class SubListIteratorNGTest {
    
    public SubListIteratorNGTest() {
    }

    @Test
    public void testNorm() {
        testMain(Arrays.asList(0, 1, 2).listIterator());
    }
    
    @Test
    public void testSubIterator() {
        ListIterator<Integer> iter = Arrays.asList(-1, 0, 1, 2, 3).listIterator();
        iter.next();
        testMain(new SubListIterator<>(iter, 1, 4));
    }
    
    @Test
    public void testSubIterator2() {
        ListIterator<Integer> iter = Arrays.asList(0, 1, 2, 3).listIterator();
        testMain(new SubListIterator<>(iter, 3));
    }
    
    public void testMain(ListIterator<Integer> iter) {
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 0);
        assertEquals((int)iter.next(), 0);
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 1);
        assertEquals((int)iter.next(), 1);
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 2);
        assertEquals((int)iter.next(), 2);
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 2);
        assertEquals((int)iter.previous(), 2);
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 1);
        assertEquals((int)iter.previous(), 1);
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 0);
        assertEquals((int)iter.previous(), 0);
        assertFalse(iter.hasPrevious());
    }
    
}
