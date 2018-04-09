/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.*;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class TransposeListNGTest {
    
    public TransposeListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testNav() {
        System.out.println(methodName(0));
        List<List<Integer>> result = new TransposeList<>(a2l(a2l(0, 0, 0, 0), a2l(1, 1, 1), a2l(2, 2, 2), a2l(3, 3)));
        List<List<Integer>> expected = a2l(a2l(0, 1, 2, 3), a2l(0, 1, 2, 3), a2l(0, 1, 2), a2l(0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNavNav() {
        System.out.println(methodName(0));
        List<List<Integer>> result = new TransposeList<>(new TransposeList<>(a2l(a2l(0, 0, 0, 0), a2l(1, 1, 1), a2l(2, 2, 2), a2l(3, 3))));
        List<List<Integer>> expected = a2l(a2l(0, 0, 0, 0), a2l(1, 1, 1), a2l(2, 2, 2), a2l(3, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }


}
