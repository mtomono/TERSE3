/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2l;
import java.util.ListIterator;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class RotateListIteratorNGTest {
    
    public RotateListIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testIterate() {
        ListIterator<Integer> iter = new RotateListIterator<>(a2l(0, 1, 2, 3), 0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
    }
    
    @Test
    public void testIterate2() {
        ListIterator<Integer> iter = new RotateListIterator<>(a2l(0, 1, 2, 3), 2);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
    }
    
    @Test
    public void testIterate3() {
        ListIterator<Integer> iter = new RotateListIterator<>(a2l(0, 1, 2, 3), 6);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
    }

    @Test
    public void testIterate4() {
        ListIterator<Integer> iter = new RotateListIterator<>(a2l(0, 1, 2, 3), -2);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.next(), (Integer)2);
        assertEquals(iter.next(), (Integer)3);
        assertEquals(iter.next(), (Integer)0);
        assertEquals(iter.next(), (Integer)1);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
        assertEquals(iter.previous(), (Integer)1);
        assertEquals(iter.previous(), (Integer)0);
        assertEquals(iter.previous(), (Integer)3);
        assertEquals(iter.previous(), (Integer)2);
    }
}
