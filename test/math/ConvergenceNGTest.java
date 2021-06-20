/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import iterator.TIterator;
import java.util.List;
import static math.Convergence.diff;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ConvergenceNGTest {
    
    public ConvergenceNGTest() {
    }

    @Test
    public void testDiff() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println("in this sequence, the difference between previous item should be the item itself. when the item goes below 0.0001, iterator stops.");
        List<C2<Double>> result=TIterator.iterate(C2.derr.b(1.0), v->v.div(C2.derr.b(2))).until(diff(C2.derr.b(0.0001))).asList();
        List<C2<Double>> expected=TList.sof(1.0, 0.5, 0.25, 0.125, 0.0625, 0.03125, 0.015625, 0.0078125, 0.00390625, 0.001953125, 9.765625E-4, 4.8828125E-4, 2.44140625E-4, 1.220703125E-4, 6.103515625E-5).map(v->C2.derr.b(v));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
