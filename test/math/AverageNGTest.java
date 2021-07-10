/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class AverageNGTest {
    
    public AverageNGTest() {
    }

    @Test
    public void testAverage() {
        System.out.println(test.TestUtils.methodName(0));
        C<Double> result = C.d.l("1.0,2.0,3.0,4.0,5.0").body().stream().collect(Average.collector(v->v,C.d.zero()));
        C<Double> expected = C.d.b(3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
