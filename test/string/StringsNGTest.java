/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class StringsNGTest {
    
    public StringsNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testN() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Strings.n('a', 10);
        String expected = "aaaaaaaaaa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCharacters_String() {
        System.out.println(test.TestUtils.methodName(0));
        List<Character> result = Strings.toCharacters("abcdefg");
        List<Character> expected = a2l('a', 'b', 'c', 'd', 'e', 'f', 'g');
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCharacters_charArr() {
        System.out.println(test.TestUtils.methodName(0));
        List<Character> result = Strings.toCharacters('a', 'b', 'c', 'd', 'e', 'f', 'g');
        List<Character> expected = a2l('a', 'b', 'c', 'd', 'e', 'f', 'g');
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNullableToString() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Strings.nullableToString(null);
        String expected = "null";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNullableToString2() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Strings.nullableToString(a2l(0, 1, 2));
        String expected = "[0, 1, 2]";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
