/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class DoubleHolderNGTest {
    
    public DoubleHolderNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testPush() {
        System.out.println(test.TestUtils.methodName(0));
        DoubleHolder h = new DoubleHolder(1);
        double result = h.push(h.get()+1);
        double expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        DoubleHolder h = new DoubleHolder(1);
        h.push(h.get()+1);
        double result = h.get();
        double expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        DoubleHolder h = new DoubleHolder(1);
        double result = h.set(h.get()+1);
        double expected = 2D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
