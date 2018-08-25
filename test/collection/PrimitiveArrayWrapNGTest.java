/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PrimitiveArrayWrapNGTest {
    
    public PrimitiveArrayWrapNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testWrap_doubleArr() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Double> result = TList.set(PrimitiveArrayWrap.wrap(new double[]{0.0, 1.0, 2.0, 3.0}));
        TList<Double> expected = TList.sof(0.0, 1.0, 2.0, 3.0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWrap_intArr() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.set(PrimitiveArrayWrap.wrap(new int[]{0, 1, 2, 3}));
        TList<Integer> expected = TList.sof(0, 1, 2, 3);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWrap_longArr() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Long> result = TList.set(PrimitiveArrayWrap.wrap(new long[]{0, 1, 2, 3}));
        TList<Long> expected = TList.sof(0L, 1L, 2L, 3L);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
