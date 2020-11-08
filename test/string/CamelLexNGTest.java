/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import java.util.Iterator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CamelLexNGTest {
    
    public CamelLexNGTest() {
    }

    public String lex() throws Exception {
        String src="newYork12City";
        Iterator<string.TokenType> lexer=new CamelLex(src);
        StringBuilder retval=new StringBuilder();
        while (lexer.hasNext()) retval.append(lexer.next()).append("\n");
        return retval.toString();
    }
    @Test
    public void testBumps() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(lex());
    }

    @Test
    public void testNextToken() {
    }
    
}
