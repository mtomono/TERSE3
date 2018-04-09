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
public class PairSequentialListNGTest {
    
    public PairSequentialListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNext() {
        System.out.println(methodName(0));
        List<Integer> result = new PairSequentialList<>(a2l(0, 1, 2, 3), a2l(0, 1, 2), (a, b)->a+b);
        List<Integer> expected = a2l(0, 2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
