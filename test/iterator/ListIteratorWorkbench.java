/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Iterator;
import java.util.ListIterator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author mtomono
 */
abstract public class ListIteratorWorkbench extends IteratorWorkbench{
    abstract public ListIterator<Integer> listIterator1();
    abstract public ListIterator<Integer> listIterator2();
    
    public ListIteratorWorkbench() {
    }

    @Override
    public Iterator<Integer> iterator1() {
        return listIterator1();
    }

    @Override
    public Iterator<Integer> iterator2() {
        return listIterator2();
    }
    
    @Test
    public void traverseBackTest() {
        ListIterator<Integer> iter = listIterator1();
        assertFalse(iter.hasPrevious());
        assertTrue(iter.hasNext());
        assertEquals((Integer)1, iter.next());
        assertEquals((Integer)4, iter.next());
        assertEquals((Integer)7, iter.next());
        assertEquals((Integer)10, iter.next());
        assertEquals((Integer)13, iter.next());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertEquals((Integer)13, iter.previous());
        assertEquals((Integer)10, iter.previous());
        assertEquals((Integer)7, iter.previous());
        assertEquals((Integer)4, iter.previous());
        assertEquals((Integer)1, iter.previous());
        assertFalse(iter.hasPrevious());
    }

    
    @Test
    public void backAndForthTest() {
        ListIterator<Integer> iter = listIterator1();
        assertTrue(iter.hasNext());
        assertEquals((Integer)1, iter.next());
        assertEquals((Integer)4, iter.next());
        assertEquals((Integer)7, iter.next());
        assertTrue(iter.hasPrevious());
        assertEquals((Integer)7, iter.previous());
        assertEquals((Integer)4, iter.previous());
        assertTrue(iter.hasNext());
        assertEquals((Integer)4, iter.next());
        assertEquals((Integer)7, iter.next());
        assertEquals((Integer)10, iter.next());
        assertEquals((Integer)13, iter.next());
        assertFalse(iter.hasNext());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertEquals((Integer)13, iter.previous());
        assertEquals((Integer)10, iter.previous());
        assertTrue(iter.hasNext());
        assertEquals((Integer)10, iter.next());
        assertTrue(iter.hasPrevious());
        assertEquals((Integer)10, iter.previous());
        assertEquals((Integer)7, iter.previous());
        assertEquals((Integer)4, iter.previous());
        assertEquals((Integer)1, iter.previous());
        assertFalse(iter.hasPrevious());
    }

    
    @Test
    public void indexTest() {
        ListIterator<Integer> iter = listIterator1();
        assertEquals(0, iter.nextIndex()); assertEquals(-1, iter.previousIndex());
        assertFalse(iter.hasPrevious());
        assertTrue(iter.hasNext());
        assertEquals(0, iter.nextIndex()); assertEquals(-1, iter.previousIndex());
        assertEquals((Integer)1, iter.next());
        assertEquals(1, iter.nextIndex()); assertEquals(0, iter.previousIndex());
        assertEquals((Integer)4, iter.next());
        assertEquals(2, iter.nextIndex()); assertEquals(1, iter.previousIndex());
        assertEquals((Integer)7, iter.next());
        assertEquals(3, iter.nextIndex()); assertEquals(2, iter.previousIndex());
        assertTrue(iter.hasPrevious());
        assertEquals(3, iter.nextIndex()); assertEquals(2, iter.previousIndex());
        assertEquals((Integer)7, iter.previous());
        assertEquals(2, iter.nextIndex()); assertEquals(1, iter.previousIndex());
        assertEquals((Integer)4, iter.previous());
        assertEquals(1, iter.nextIndex()); assertEquals(0, iter.previousIndex());
        assertTrue(iter.hasNext());
        assertEquals((Integer)4, iter.next());
        assertEquals(2, iter.nextIndex()); assertEquals(1, iter.previousIndex());
        assertEquals((Integer)7, iter.next());
        assertEquals(3, iter.nextIndex()); assertEquals(2, iter.previousIndex());
        assertEquals((Integer)10, iter.next());
        assertEquals(4, iter.nextIndex()); assertEquals(3, iter.previousIndex());
        assertEquals((Integer)13, iter.next());
        assertEquals(5, iter.nextIndex()); assertEquals(4, iter.previousIndex());
        assertFalse(iter.hasNext());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertEquals(5, iter.nextIndex()); assertEquals(4, iter.previousIndex());
        assertEquals((Integer)13, iter.previous());
        assertEquals(4, iter.nextIndex()); assertEquals(3, iter.previousIndex());
        assertEquals((Integer)10, iter.previous());
        assertEquals(3, iter.nextIndex()); assertEquals(2, iter.previousIndex());
        assertTrue(iter.hasNext());
        assertEquals((Integer)10, iter.next());
        assertEquals(4, iter.nextIndex()); assertEquals(3, iter.previousIndex());
        assertTrue(iter.hasPrevious());
        assertEquals((Integer)10, iter.previous());
        assertEquals(3, iter.nextIndex()); assertEquals(2, iter.previousIndex());
        assertEquals((Integer)7, iter.previous());
        assertEquals(2, iter.nextIndex()); assertEquals(1, iter.previousIndex());
        assertEquals((Integer)4, iter.previous());
        assertEquals(1, iter.nextIndex()); assertEquals(0, iter.previousIndex());
        assertEquals((Integer)1, iter.previous());
        assertEquals(0, iter.nextIndex()); assertEquals(-1, iter.previousIndex());
        assertFalse(iter.hasPrevious());
        assertEquals(0, iter.nextIndex()); assertEquals(-1, iter.previousIndex());
    }

}
