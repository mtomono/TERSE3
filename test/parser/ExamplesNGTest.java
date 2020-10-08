/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static parser.Parsers.anyChar;
import static parser.Parsers.many;
import static parser.Parsers.str;
import static parser.Parsers.regex;

/**
 *
 * @author masao
 */
public class ExamplesNGTest {
    
    public ExamplesNGTest() {
    }
    
    @Test//(expectedExceptions = {parser.ParseException.class})
    public void testSkipFail() throws Exception{
        System.out.println(test.TestUtils.methodName(0));
        Parser<String,Character,Character> skip = many(anyChar.t().except(c->c.equals('%')));
        String tested = "the reason behind";
        skip.parse(new StrSource(tested));
        // This case must end with exception because pos is already at the end of the string.
        // Instead of this, use something like testSkipPass
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSkipPass() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        Parser<String,Character,String> skip = many(anyChar.l().except(c->c.equals("%")));
        String tested = "the reason behind";
        String result = skip.l().parse(new StrSource(tested));
        // this is okay contrary to the case depicted in testSkipFail.
        // in t()'s case, if it's at the end of the list or string, it has nothing to point at.
        // but l() can still say the length of answer is 0. that's the difference.
        String expected = "the reason behind";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRegex() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String texted = "xxxx123xxxx";
        Parser<String,Character,String> xxxx = str("xxxx");
        Parser<String,Character,String> n = regex("[0-9]*");
        Parser<String,Character,String> p = xxxx.next(n).prev(xxxx);
        String result = p.parse(new StrSource(texted));
        String expected = "123";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
