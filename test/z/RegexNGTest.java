/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class RegexNGTest {
    
    public RegexNGTest() {
    }
    
    @Test
    public void testFind() {
        System.out.println(test.TestUtils.methodName(0));
        Pattern p = Pattern.compile("abc");
        Matcher m = p.matcher("abcxxxx");
        System.out.println(m.find());
        System.out.println(m.end());
        System.out.println(m.group());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
