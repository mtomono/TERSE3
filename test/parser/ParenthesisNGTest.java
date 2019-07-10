/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ParenthesisNGTest {
    
    public ParenthesisNGTest() {
    }

    @Test
    public void testParenthesis() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> retval = TList.c();
        Parenthesis<String,Character> parenthesis = new Parenthesis('(',')',retval);
        StrSource s = new StrSource("andob(begilba(xx)don(x)()ssdf)kara");
        parenthesis.content.parse(s);
        TList<String> result = retval.map(l->s.src.substring(l.get(0),l.get(1)));
        TList<String> expected = TList.sof("(xx)", "(x)", "()", "(begilba(xx)don(x)()ssdf)");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParenthesis_01() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> retval = TList.c();
        Parenthesis<String,Character> parenthesis = new Parenthesis('(',')',retval);
        StrSource s = new StrSource("andob(be)gi(lba(xx)don(x)()ssdf)kara");
        parenthesis.content.parse(s);
        TList<String> result = retval.map(l->s.src.substring(l.get(0),l.get(1)));
        TList<String> expected = TList.sof("(be)","(xx)", "(x)", "()", "(lba(xx)don(x)()ssdf)");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testParenthesis_02() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> retval = TList.c();
        Parenthesis<String,Character> parenthesis = new Parenthesis('(',')',retval);
        StrSource s = new StrSource("()(())");
        parenthesis.content.parse(s);
        TList<String> result = retval.map(l->s.src.substring(l.get(0),l.get(1)));
        TList<String> expected = TList.sof("()","()", "(())");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testContent() {
    }
    
}
