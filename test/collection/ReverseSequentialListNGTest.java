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
public class ReverseSequentialListNGTest {
    
    public ReverseSequentialListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new ReverseSequentialList<>(a2l(0, 1, 2, 3));
        List<Integer> expected = a2l(3, 2, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
