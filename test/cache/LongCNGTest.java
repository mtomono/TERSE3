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
public class LongCNGTest {
    
    public LongCNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testV() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Long> h = new Holder<>(1L);
        LongC tested = new LongC(()->h.push(h.get()+1));
        tested.v();
        tested.v();
        long result = tested.v();
        long expected = 1L;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
