/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import collection.TList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static parser.BasicRegex.number;

/**
 *
 * @author masao
 */
public class BasicRegexNGTest {
    
    public BasicRegexNGTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    
    public String match(String source,Matcher matcher) {
        matcher.find();
        return source.substring(matcher.start(),matcher.end());
    }
    public boolean nomatch(Matcher matcher) {
        matcher.find();
        return matcher.hitEnd();
    }
    
    @Test
    public void testNumberExactMatch() {
        System.out.println(test.TestUtils.methodName(0));
        Pattern pattern=Pattern.compile(number);
        TList<String> tested=TList.sof("123","1.23","12.3",".123","123.","-1.23","1.23e-10");
        TList<String> result=tested.map(s->match(s,pattern.matcher(s))).sfix();
        TList<String> expected = TList.sof("123","1.23","12.3",".123","123.","-1.23","1.23e-10");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test(groups={"performance"})
    public void testNumber00xmany() {
        System.out.println(test.TestUtils.methodName(0));
        Pattern pattern=Pattern.compile(number);
        TList<String> tested=TList.sof("123","1.23","12.3",".123","123.",".123.","123.456.789");
        for (int i=0;i<1000000;i++)
            tested.map(s->match(s,pattern.matcher(s))).sfix();
    }

    @Test
    public void testNumberPartialMatch() {
        System.out.println(test.TestUtils.methodName(0));
        Pattern pattern=Pattern.compile(number);
        TList<String> tested=TList.sof("1.23e-10.000",".123.","123.456.789");
        TList<String> result=tested.map(s->match(s,pattern.matcher(s))).sfix();
        TList<String> expected = TList.sof("1.23e-10",".123","123.456");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNumberNoMatch() {
        System.out.println(test.TestUtils.methodName(0));
        Pattern pattern=Pattern.compile(number);
        TList<String> tested=TList.sof(".");
        TList<Boolean> result=tested.map(s->nomatch(pattern.matcher(s))).sfix();
        TList<Boolean> expected = TList.sof(true);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
