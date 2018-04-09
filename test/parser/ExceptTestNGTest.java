/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static parser.Parsers.anyChar;
import static parser.Parsers.many;
import static parser.Parsers.rep;
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
        Parser<String, Character, String> abc = many(rep(3, anyChar).l().except(s->s.equals("abc")).tr()).next(str("abc"));
        StrSource src=new StrSource("cccfffdddabc");
        abc.parse(src);
    }
    
}
