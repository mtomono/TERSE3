/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import function.Holder;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CacheNGTest {
    
    public CacheNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testV() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Integer> h = new Holder<>(1);
        Cache<Integer> tested = new Cache<>(()->h.push(h.get()+1));
        tested.v();
        tested.v();
        Integer result = tested.v();
        Integer expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
