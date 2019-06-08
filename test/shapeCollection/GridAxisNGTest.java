/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class GridAxisNGTest {
    
    public GridAxisNGTest() {
    }

    @Test
    public void testContains() {
        
    }

    @Test
    public void testIntersect() {
        System.out.println(test.TestUtils.methodName(0));
        GridAxis tested = new GridAxis(2,0);
        GridAxis result = tested.intersect(new GridAxis(2,3)).get();
        GridAxis expected = new GridAxis(2,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        GridAxis tested = new GridAxis(2,0);
        GridAxis result = tested.reverse();
        GridAxis expected = new GridAxis(0,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
