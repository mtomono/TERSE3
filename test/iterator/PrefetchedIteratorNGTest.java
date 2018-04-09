/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class PrefetchedIteratorNGTest {
    
    public PrefetchedIteratorNGTest() {
    }
    
    public <T> List<T> a(T... t) {
        return Arrays.asList(t);
    }
    
    @Test
    public void test1() {
        PreIterator<String> tested = new PreIterator(a("t0", "t1", "t2", "t3").iterator(), 2);
        List<String> pre = tested.pre();
        assertTrue(tested.hasNext());
        assertEquals(tested.next(), "t0");
        assertEquals(pre, a(null, "t0"));
        assertTrue(tested.hasNext());
        assertEquals(tested.next(), "t1");
        assertEquals(pre, a("t0", "t1"));
        assertTrue(tested.hasNext());
        assertEquals(tested.next(), "t2");
        assertEquals(pre, a("t1", "t2"));
        assertTrue(tested.hasNext());
        assertEquals(tested.next(), "t3");
        assertEquals(pre, a("t2", "t3"));
        assertFalse(tested.hasNext());
    }

    @Test
    public void test2() {
        PreIterator<String> tested = new PreIterator(a("t0").iterator(), 2);
        List<String> pre = tested.pre();
        assertTrue(tested.hasNext());
        assertEquals(tested.next(), "t0");
        assertEquals(pre, a(null, "t0"));
        assertFalse(tested.hasNext());
    }

    @Test
    public void test3() {
        PreIterator<String> tested = new PreIterator(a().iterator(), 2);
        List<String> pre = tested.pre();
        assertFalse(tested.hasNext());
    }

}
