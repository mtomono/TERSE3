/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.c;
import java.util.Iterator;
import static orderedSet.Range.intRange;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author mtomono
 */
public class CoverRangeIteratorTest {
    
    @Test
    public void iteratorTest() {
        Iterator<Range<Integer>> iter = new CoverRangeIterator<>(
                    c.a2i(2, 3, 4, 5, 9, 10, 11, 12, 15, 16, 17, 18, 25), 0, 1);
        assertTrue(iter.hasNext());
        assertEquals(intRange.r(2, 6), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(intRange.r(9, 13), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(intRange.r(15, 19), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(intRange.r(25, 26), iter.next());
        assertFalse(iter.hasNext());
    }
}
