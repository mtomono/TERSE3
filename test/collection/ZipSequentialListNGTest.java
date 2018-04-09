/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ZipSequentialListNGTest {
    
    public ZipSequentialListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testListIterator() {
        System.out.println(test.TestUtils.methodName(0));
        ZipSequentialList<Integer, Integer> result = new ZipSequentialList<>(a2l(0, 1, 2), a2l(3, 4));
        List<P<Integer, Integer>> expected = a2l(P.p(0, 3), P.p(1, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
