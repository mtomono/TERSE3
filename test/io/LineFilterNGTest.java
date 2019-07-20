/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class LineFilterNGTest {
    
    public LineFilterNGTest() {
    }

    @Test
    public void testProcess() {
    }

    @Test
    public void testMain() {
        System.out.println(test.TestUtils.methodName(0));
        LineFilter.main(new String[]{"s->s+\"xxx\""});
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
