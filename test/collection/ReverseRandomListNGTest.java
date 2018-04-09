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
public class ReverseRandomListNGTest {
    
    public ReverseRandomListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        List<Integer> result = new ReverseRandomList<>(a2l(7, 6, 5, 4, 3, 2, 1, 0));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
