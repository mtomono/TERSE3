/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import static collection.c.a2i;
import static collection.c.a2l;
import static collection.c.i2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class SkipIteratorNGTest {
    
    public SkipIteratorNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSomeMethod() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = i2l(new SkipIterator<>(a2i(0,5,10,11,12,15), (a,b)->b-a>=5));
        List<Integer> expected = a2l(0,5,10,15);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
