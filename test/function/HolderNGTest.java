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
public class HolderNGTest {
    
    public HolderNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testPush() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Integer> h = new Holder<>(1);
        Integer result = h.push(h.get()+1);
        Integer expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Integer> h = new Holder<>(1);
        h.push(h.get()+1);
        Integer result = h.get();
        Integer expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Integer> h = new Holder<>(1);
        Integer result = h.setget(h.get()+1);
        Integer expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
