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
public class CastPredicateNGTest {
    
    public CastPredicateNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testTest() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new CastPredicate<>(String.class, p->p.length()>3).test(new Object());
        boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testTest2() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new CastPredicate<>(String.class, p->p.length()>3).test("bobson");
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
