/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2al;
import static collection.c.a2l;
import static collection.c.i2l;
import java.util.List;
import java.util.ListIterator;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.LITester;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class WeaveListIteratorNGTest {
    
    public WeaveListIteratorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @Test
    public void testNav() {
        System.out.println(methodName(0));
        ListIterator<Integer> tested = WeaveListIterator.create(a2al(a2al(0, 3, 6, 9), a2al(1, 4, 7, 10), a2al(2, 5, 8, 11)), 2);
        ListIterator<Integer> exemplar = a2al(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).listIterator(2);
        LITester<Integer> iter = new LITester<>(tested, exemplar);
        LITester.test2(iter);
    }
    
    @Test
    public void testNav2() {
        System.out.println(methodName(0));
        List<Integer> result = i2l(WeaveListIterator.create(a2l(a2l(0, 3, 6, 9), a2l(1, 4, 7), a2l(2, 5, 8)), 0));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
