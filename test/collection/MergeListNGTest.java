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
public class MergeListNGTest {
    
    public MergeListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testList() {
        System.out.println(methodName(0));
        List<Integer> result = new MergeList<>(a2l(0, 2, 5, 9), a2l(1, 2, 8, 10), (a, b)->a-b);
        List<Integer> expected = a2l(0, 1, 2, 2, 5, 8, 9, 10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
