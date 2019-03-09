/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import collection.c;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class IteratorCacheTest extends ListIteratorWorkbench {
    
    public IteratorCacheTest() {
    }
    @Test
    public void cacheTest() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(3), iter.next());
        assertEquals(Integer.valueOf(2), iter.next());
        assertTrue(iter.hasNext());
        iter = target.iterator();
        List<Integer> cache = target.dump();
        assertEquals(Integer.valueOf(3), cache.get(0));
        assertEquals(Integer.valueOf(2), cache.get(1));
        assertEquals(2, cache.size());
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(3), iter.next());
        assertEquals(Integer.valueOf(2), iter.next());
        assertEquals(Integer.valueOf(6), iter.next());
        assertEquals(Integer.valueOf(1), iter.next());
        assertFalse(iter.hasNext());
        iter = target.iterator();
        assertTrue(iter.hasNext());
        assertEquals(Integer.valueOf(3), iter.next());
        assertEquals(Integer.valueOf(2), iter.next());
        assertEquals(Integer.valueOf(6), iter.next());
        assertEquals(Integer.valueOf(1), iter.next());
        assertFalse(iter.hasNext());
        cache = target.dump();
        assertEquals(Integer.valueOf(3), cache.get(0));
        assertEquals(Integer.valueOf(2), cache.get(1));
        assertEquals(Integer.valueOf(6), cache.get(2));
        assertEquals(Integer.valueOf(1), cache.get(3));
        assertEquals(4, cache.size());
    }
    
    @Test
    public void listTest_getToNext() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer)2, target.get(1));
        assertTrue(iter.hasNext());
        assertEquals((Integer)3, iter.next());
        assertEquals((Integer)2, iter.next());
        assertEquals((Integer)6, iter.next());
        assertEquals((Integer)6, target.get(2));
        assertEquals((Integer)1, target.get(3));
        try {
            target.get(4);
            fail();
        } catch (IndexOutOfBoundsException e) {
            
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void listTest_nextToGet() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer)3, iter.next());
        assertEquals((Integer)2, iter.next());
        assertEquals((Integer)3, target.get(0));
        assertEquals((Integer)6, target.get(2));
        assertEquals((Integer)6, iter.next());
        assertEquals((Integer)1, iter.next());
        assertFalse(iter.hasNext());
        try {
            target.get(4);
            fail();
        } catch (IndexOutOfBoundsException e) {
            
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void listTest_nextThrough() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertTrue(iter.hasNext());
        assertEquals((Integer)3, iter.next());
        assertEquals((Integer)2, iter.next());
        assertEquals((Integer)6, iter.next());
        assertEquals((Integer)1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void listTest_getThrough() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertEquals((Integer)2, target.get(1));
        assertEquals((Integer)3, target.get(0));
        assertEquals((Integer)2, target.get(1));
        assertEquals((Integer)1, target.get(3));
        assertEquals((Integer)6, target.get(2));
        try {
            target.get(4);
            fail();
        } catch (IndexOutOfBoundsException e) {
            
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void iteratorTest() {
        IteratorCache<Integer> target = new IteratorCache<>(c.a2i(3, 2, 6, 1));
        Iterator<Integer> iter = target.iterator();
        assertEquals((Integer)3, iter.next());
        assertEquals((Integer)2, iter.next());
        Iterator<Integer> tested = target.iterator();
        assertTrue(tested.hasNext());
        assertEquals((Integer)3, tested.next());
        assertEquals((Integer)2, tested.next());
        assertEquals((Integer)6, tested.next());
        Iterator<Integer> tested2 = target.iterator();
        assertEquals((Integer)3, tested2.next());
        assertEquals((Integer)1, tested.next());
        assertEquals((Integer)2, tested2.next());
        assertEquals((Integer)6, tested2.next());
        assertEquals((Integer)1, tested2.next());
    }

    @Override
    public ListIterator<Integer> listIterator1() {
        return new IteratorCache<>(subject1.iterator()).listIterator();
    }

    @Override
    public ListIterator<Integer> listIterator2() {
        return new IteratorCache<>(subject2.iterator()).listIterator();
    }
    
    @Test
    public void testAsList() {
        System.out.println(test.TestUtils.methodName(0));
        IteratorCache<Integer> ic = new IteratorCache<>(TList.sof(0,1,2,3,4).iterator());
        List<Integer> result = new IteratorCache<>(ic.listIterator(1));
        List<Integer> expected = TList.sof(1,2,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
