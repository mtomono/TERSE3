/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.Iterator;
import org.testng.annotations.Test;
import parser.Token;

/**
 *
 * @author masao
 */
public class JasonLexNGTest {
    
    public JasonLexNGTest() {
    }

    @Test
    public void testClone() {
    }
    
    public String lex() throws Exception {
        String src="  {\"parameter0\":\"value0\" ,"
                + "\"parameter1\":\"value1\" ,"
                + "\"parameter2\":\"value2\","
                + "\"parameter3\": true, "
                + "\"parameter4\" :false,  "
                + "\"parameter5\":null, "
                + "\"parameter6\":-1043.25}";
        Iterator<TokenTypes> lexer=new JsonLex(src).ignored;
        StringBuilder retval=new StringBuilder();
        while (lexer.hasNext()) retval.append(lexer.next()).append("\n");
        return retval.toString();
    }
    @Test
    public void testOneLexNoPrint() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        lex();
    }
    @Test
    public void testOneLex() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(lex());
    }
    @Test(groups={"performance"})
    public void testMLex() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        for(int i=0;i<1000000;i++) lex();
    }
    @Test(groups={"performance"})
    public void testLex() throws Exception {
        testOneLex();
        testMLex();
        testOneLex();
    }
}
