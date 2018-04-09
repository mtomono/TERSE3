package iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author mtomono
 */
abstract public class IteratorWorkbench {
    public List<Integer> subject1 = Arrays.asList(1, 4, 7, 10, 13);
    public List<Integer> subject2 = Arrays.asList(2, 5, 8, 11, 14);
    abstract public Iterator<Integer> iterator1();
    abstract public Iterator<Integer> iterator2();
    
    public IteratorWorkbench() {
    }

    @Test
    public void traverseTest() {
        Iterator<Integer> iter = iterator1();
        assertTrue(iter.hasNext());
        assertEquals((Integer)1, iter.next());
        assertEquals((Integer)4, iter.next());
        assertEquals((Integer)7, iter.next());
        assertEquals((Integer)10, iter.next());
        assertEquals((Integer)13, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void checkAndTraverseThroughTest() {
        Iterator<Integer> iter = iterator1();
        assertTrue(iter.hasNext());
        assertEquals((Integer)1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer)4, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer)7, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer)10, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer)13, iter.next());
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void dualTraverseTest() {
        Iterator<Integer> iter1 = iterator1();
        Iterator<Integer> iter2 = iterator1();
        
        assertTrue(iter1.hasNext());
        assertEquals((Integer)1, iter1.next());
        assertEquals((Integer)4, iter1.next());
        assertEquals((Integer)7, iter1.next());
        assertTrue(iter2.hasNext());
        assertEquals((Integer)1, iter2.next());
        assertEquals((Integer)4, iter2.next());
        assertEquals((Integer)7, iter2.next());
        assertEquals((Integer)10, iter1.next());
        assertEquals((Integer)13, iter1.next());
        assertEquals((Integer)10, iter2.next());
        assertFalse(iter1.hasNext());
        assertEquals((Integer)13, iter2.next());
        assertFalse(iter2.hasNext());
    }
    
    
    @Test
    public void dualCheckAndTraverseTest() {
        Iterator<Integer> iter1 = iterator1();
        Iterator<Integer> iter2 = iterator1();
        
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)1, iter1.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)4, iter1.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)7, iter1.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)1, iter2.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)4, iter2.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)7, iter2.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)10, iter1.next());
        assertTrue(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)13, iter1.next());
        assertFalse(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)10, iter2.next());
        assertFalse(iter1.hasNext()); assertTrue(iter2.hasNext());
        assertEquals((Integer)13, iter2.next());
        assertFalse(iter1.hasNext()); assertFalse(iter2.hasNext());
    }
}
