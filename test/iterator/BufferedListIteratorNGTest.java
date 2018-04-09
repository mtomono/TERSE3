/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class BufferedListIteratorNGTest {
    
    class Holder<T> {
        Function<List<T>, ListIterator<T>> function;
        String name;
        Holder(Function<List<T>, ListIterator<T>> function, String name) {
            this.function = function;
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
    @DataProvider(name="fixture")
    public Object[][] compatibility() {
        return new Object[] []{
            { new Holder<String>(t->new RingBufferedListIterator<>(t.iterator(), 2), "unread") }, 
            { new Holder<String>(t->new SingleBufferedListIterator<>(t.iterator()), "sunread") },
            { new Holder<String>(t->t.listIterator(), "list") }
        };
    }
    
    public BufferedListIteratorNGTest() {
    }
    
    public <T> List<T> a(T... t) {
        return Arrays.asList(t);
    }
    @Test(dataProvider="fixture")
    public void test1(Holder<String> holder) {
        System.out.println("test1 " + holder.name);
        List<String> tested = a("t0", "t1", "t2", "t3");
        ListIterator<String> iter = holder.function.apply(tested);
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t0");
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previous(), "t0");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t0");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t1");
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previous(), "t1");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t1");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t2");
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previous(), "t2");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t2");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t3");
        assertTrue(iter.hasPrevious());
        assertEquals(iter.previous(), "t3");
        assertTrue(iter.hasNext());
        assertEquals(iter.next(), "t3");
        assertFalse(iter.hasNext());
    }

}
