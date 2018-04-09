/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class WeaveSequentialListNGTest {
    
    public WeaveSequentialListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        List<Integer> result = new WeaveSequentialList<>(a2l(a2l(0, 3, 6, 9), a2l(1, 4, 7, 10), a2l(2, 5, 8)));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
