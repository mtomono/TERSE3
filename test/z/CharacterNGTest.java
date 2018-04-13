/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CharacterNGTest {
    
    public CharacterNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testOctalEscapeSequence() {
        System.out.println(test.TestUtils.methodName(0));
        char result = '\100';
        char expected = '@';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testHexEscapeSequence() {
        System.out.println(test.TestUtils.methodName(0));
        char result = '\u0041';
        char expected = 'A';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testTomo() {
        representation('友');
    }

    @Test
    public void testNo() {
        representation('野');
    }
    
    public void representation(char target) {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(target);
        System.out.println("number of chars:"+Character.charCount(target));
        System.out.println("HEX:"+Integer.toString(target, 16));
        System.out.println("OCT:"+Integer.toString(target, 8));
    }
    
    @Test
    public void testIllegalEscape() {
        System.out.println(test.TestUtils.methodName(0));
        char result = "\100".charAt(0);
        char expected = '@';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
