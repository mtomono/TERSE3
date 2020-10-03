/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

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

    @Test
    public void testLex() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        String src="  {\"parameter0\":\"value0\" ,\"parameter1\":\"value1\" ,\"parameter2\":\"value2\" } ";
        for(int i=0;i<1000000;i++) {
            JsonLex lexer=new JsonLex(src);
            while (lexer.hasNext()) lexer.toString(lexer.next());
        }
        JsonLex lexer=new JsonLex(src);
        while (lexer.hasNext()) System.out.println(lexer.toString(lexer.next()));
        /*
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        */
    }
    
}
