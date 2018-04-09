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
public class DoubleCNGTest {
    
    public DoubleCNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testV() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<Double> h = new Holder<>(1D);
        DoubleC tested = new DoubleC(()->h.push(h.get()+1));
        tested.v();
        tested.v();
        double result = tested.v();
        double expected = 1D;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
