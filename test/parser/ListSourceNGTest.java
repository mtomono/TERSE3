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
public class ListSourceNGTest {
    
    public ListSourceNGTest() {
    }

    @Test
    public void testParenthesis() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> tested = TList.sof("x",">>>","x","x",">>>","x",">>>","<<<","x","<<<","<<<");
        TList<List<Integer>> retval = TList.c();
        new Parenthesis<List<String>,String>(">>>","<<<",retval).content.parse(new ListSource<>(tested));
        TList<List<String>> result = retval.map(s->tested.subList(s.get(0),s.get(1)));
        TList<List<String>> expected = TList.sof(TList.sof(">>>", "<<<"), TList.sof(">>>", "x", ">>>", "<<<", "x", "<<<"), TList.sof(">>>", "x", "x", ">>>", "x", ">>>", "<<<", "x", "<<<", "<<<"));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
