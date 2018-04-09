/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2al;
import java.util.ListIterator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.LITester;
import static test.TestUtils.pr;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class MapListIteratorNGTest {
    
    public MapListIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @Test
    public void testFore(){
        System.out.println(methodName(0));
        ListIterator<Integer> tested = new MapListIterator<>(a2al(0, 2, 4, 6, 8, 10, 12, 14).listIterator(), i->i/2, i->i*2);
        ListIterator<Integer> exemplar = a2al(0, 1, 2, 3, 4, 5, 6, 7).listIterator();
        LITester<Integer> iter = new LITester<>(tested, exemplar);
        try {
            iter.next();
            iter.next();
            iter.previous();
            iter.next();
            iter.remove();
            iter.add(8);
            iter.add(9);
            iter.remove();
            iter.remove();
            iter.set(10);
            iter.add(10);
            iter.set(11);
            iter.next();
            iter.set(12);
            iter.remove();
            iter.set(13);
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.previous();
            iter.previous();
            iter.add(20);
            iter.remove();
            iter.rewind();
            pr(iter.report());
        } catch (Throwable t) {
            pr(iter);
            pr(iter.reportNow());
            iter.rewind();
            pr(iter.report());
            throw t;
        }
    }
    
}
