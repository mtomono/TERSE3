/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2l;
import static iterator.ListIterators.where;
import java.util.ListIterator;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ListIteratorsNGTest {
    
    public ListIteratorsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testWhere() {
        ListIterator<Integer> iter = a2l(0, 1, 2, 3, 4, 5, 6, 7).listIterator();
        iter.next();
        iter.next();
        int tested = iter.next();
        assertEquals(tested, 2);
        int count = where(iter);
        assertEquals(count, 3);
        assertEquals(iter.next(), (Integer)3);
    }
    
}
