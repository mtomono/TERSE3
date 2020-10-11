/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static parser.Parsers.anyChar;
import static parser.Parsers.str;

/**
 *
 * @author masao
 */
public class ExceptTestNGTest {
    
    public ExceptTestNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void test() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        Parser<String, Character, String> abc = anyChar.rep(3).l().except(s->s.equals("abc")).tr().many().next(str("abc"));
        StrSource src=new StrSource("cccfffdddabc");
        String result=abc.l().parse(src);
        String expected="cccfffdddabc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
