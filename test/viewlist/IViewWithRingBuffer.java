/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewlist;

import collection.RingBuffer;
import static collection.c.a2l;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import java.util.ListIterator;
import java.util.Optional;

/**
 *
 * @author mtomono
 */
public class IViewWithRingBuffer {
    
    public IViewWithRingBuffer() {
    }
    
    @Test
    public void test() {
        RingBuffer<String> body = new RingBuffer(a2l("t0", "t1", "t2", "t3"));
        View<String> view = View.pre(body, 4);
        ListIterator<Optional<String>> iter = view.listIterator();
        assertTrue(iter.hasNext());
        assertEquals(body.pop(null), "t3");
        assertEquals(iter.next().get(), "t2");
    }
}
