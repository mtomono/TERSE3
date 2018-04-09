/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class OptIteratorNGTest {
    
    public OptIteratorNGTest() {
    }

    @Test
    public void testNorm() {
        testMain(Arrays.asList(Optional.of(0), Optional.of(1), Optional.of(2), Optional.<Integer>empty(), Optional.<Integer>empty()).listIterator());
    }
    
    @Test
    public void testOptIterator() {
        testMain(new OptIterator<>(Arrays.asList(0, 1, 2).listIterator()));
    }
    
    public void testMain(ListIterator<Optional<Integer>> iter) {
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 0);
        assertEquals(iter.next(), Optional.of(0));
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 1);
        assertEquals(iter.next(), Optional.of(1));
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 2);
        assertEquals(iter.next(), Optional.of(2));
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 3);
        assertEquals(iter.next(), Optional.empty());
        assertTrue(iter.hasNext());
        assertEquals(iter.nextIndex(), 4);
        assertEquals(iter.next(), Optional.empty());
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 4);
        assertEquals(iter.previous(), Optional.empty());
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 3);
        assertEquals(iter.previous(), Optional.empty());
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 2);
        assertEquals(iter.previous(), Optional.of(2));
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 1);
        assertEquals(iter.previous(), Optional.of(1));
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previousIndex(), 0);
        assertEquals(iter.previous(), Optional.of(0));
    }
    
}
