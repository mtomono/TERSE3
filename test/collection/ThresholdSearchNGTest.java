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
public class ThresholdSearchNGTest {
    
    public ThresholdSearchNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testApplyToNone() {
    }

    @Test
    public void testApplyToAll() {
    }

    @Test
    public void testFindLast() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<77,0,10);
        int result = tested.findLast();
        int expected = 76;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFindLast2() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<70,0,10);
        int result = tested.findLast();
        int expected = 69;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFindLast3() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<99,0,10);
        int result = tested.findLast();
        int expected = 98;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFindLast4() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<59,0,10);
        int result = tested.findLast();
        int expected = 58;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFindLast5() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<1,0,10);
        int result = tested.findLast();
        int expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFindLast6() {
        System.out.println(test.TestUtils.methodName(0));
        ThresholdSearch<Integer> tested = new ThresholdSearch<>(TList.range(0,100),i->i<100,0,10);
        int result = tested.findLast();
        int expected = 99;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
