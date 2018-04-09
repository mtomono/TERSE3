/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collection;

import static collection.c.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class RingBufferNGTest {
    
    public RingBufferNGTest() {
    }

    @Test
    public void test1() {
        RingBuffer<String> tested = new RingBuffer<>(5);
        assertEquals(tested.push("t0"), null);
        assertEquals(tested, a2l(null, null, null, null, "t0"));
        assertEquals(tested.push("t1"), null);
        assertEquals(tested, a2l(null, null, null, "t0", "t1"));
        assertEquals(tested.push("t2"), null);
        assertEquals(tested, a2l(null, null, "t0", "t1", "t2"));
        assertEquals(tested.push("t3"), null);
        assertEquals(tested, a2l(null, "t0", "t1", "t2", "t3"));
        assertEquals(tested.push("t4"), null);
        assertEquals(tested, a2l("t0", "t1", "t2", "t3", "t4"));
        assertEquals(tested.push("t5"), "t0");
        assertEquals(tested, a2l("t1", "t2", "t3", "t4", "t5"));
        assertEquals(tested.push("t6"), "t1");
        assertEquals(tested, a2l("t2", "t3", "t4", "t5", "t6"));
        assertEquals(tested.push("t7"), "t2");
        assertEquals(tested, a2l("t3", "t4", "t5", "t6", "t7"));
    }
    
    @Test
    public void test2() {
        RingBuffer<String> tested = new RingBuffer<>(5);
        assertEquals(tested.push("t0"), null);
        assertEquals(tested, a2l(null, null, null, null, "t0"));
        assertEquals(tested.push("t1"), null);
        assertEquals(tested, a2l(null, null, null, "t0", "t1"));
        assertEquals(tested.push("t2"), null);
        assertEquals(tested, a2l(null, null, "t0", "t1", "t2"));
        assertEquals(tested.push("t3"), null);
        assertEquals(tested, a2l(null, "t0", "t1", "t2", "t3"));
        assertEquals(tested.push("t4"), null);
        assertEquals(tested, a2l("t0", "t1", "t2", "t3", "t4"));
        assertEquals(tested.push("t5"), "t0");
        assertEquals(tested, a2l("t1", "t2", "t3", "t4", "t5"));
        assertEquals(tested.push("t6"), "t1");
        assertEquals(tested, a2l("t2", "t3", "t4", "t5", "t6"));
        assertEquals(tested.push("t7"), "t2");
        assertEquals(tested, a2l("t3", "t4", "t5", "t6", "t7"));
        assertEquals(tested.pop(null), "t7");
        assertEquals(tested, a2l(null, "t3", "t4", "t5", "t6"));
        assertEquals(tested.pop(null), "t6");
        assertEquals(tested, a2l(null, null, "t3", "t4", "t5"));
        assertEquals(tested.pop(null), "t5");
        assertEquals(tested, a2l(null, null, null, "t3", "t4"));
        assertEquals(tested.pop(null), "t4");
        assertEquals(tested, a2l(null, null, null, null, "t3"));
        assertEquals(tested.pop(null), "t3");
        assertEquals(tested, a2l(null, null, null, null, null));
        assertEquals(tested.pop(null), null);
        assertEquals(tested, a2l(null, null, null, null, null));
    }
}
