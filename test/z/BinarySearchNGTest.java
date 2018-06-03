/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import collection.TList;
import java.util.Collections;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class BinarySearchNGTest {
    
    public BinarySearchNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testSearch() {
        System.out.println(test.TestUtils.methodName(0));
        int result = Collections.binarySearch(TList.of(0,1,2,3,5,6), 4, (a,b)->a-b);
        int expected = -5;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSearch2() {
        System.out.println(test.TestUtils.methodName(0));
        int result = Collections.binarySearch(TList.of(0,1,2,3,5,6), 3, (a,b)->a-b);
        int expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
