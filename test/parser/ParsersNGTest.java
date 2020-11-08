/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import collection.P;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static parser.Parser.reduce;
import static parser.Parser.seq;
import static parser.Parser.tor;
import static parser.Parsers.*;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class ParsersNGTest {
    
    public ParsersNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSatisfy() {
    }

    @Test
    public void testAny() {
    }
    
    
    @Test
    public void testAnyChar() throws Exception {
        System.out.println(methodName(0));
        Parser<String, Character, String> p = s-> {
            anyChar.parse(s);
            anyChar.parse(s);
            return null;
        };
        StrSource s = new StrSource("abc");
        String result = p.l().parse(s);
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testComposite1() throws Exception {
        System.out.println(methodName(0));
        Parser<String, Character, String> ppre = s-> {
            anyChar.parse(s);
            anyChar.parse(s);
            return null;
        };
        Parser<String, Character, String> p = s-> {
            ppre.parse(s);
            anyChar.parse(s);
            return null;
        };
        StrSource s = new StrSource("abc");
        String result = p.l().parse(s);
        String expected = "abc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testComposite2() throws Exception {
        System.out.println(methodName(0));
        Parser<String, Character, String> ppre = s-> {
            anyChar.parse(s);
            anyChar.parse(s);
            return null;
        };
        Parser<String, Character, String> p = s-> {
            ppre.parse(s);
            anyChar.parse(s);
            return null;
        };
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = p.l().parse(s);
            fail();
        } catch(ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSatisfy1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = Parsers.<String, Character>satisfy(Character::isDigit).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSatisfy2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123");
        String result = null;
        try {
            result = Parsers.<String, Character>satisfy(Character::isDigit).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChar1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123");
        String result = null;
        try {
            result = chr('a').l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testChar2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc");
        String result = null;
        try {
            result = chr('a').l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDigit1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc");
        String result = null;
        try {
            result = digit.l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDigit2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123");
        String result = null;
        try {
            result = digit.l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLetter1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc");
        String result = null;
        try {
            result = letter.l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLetter2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123");
        String result = null;
        try {
            result = letter.l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSequence1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc");
        String result = null;
        try {
            result = seq(letter, digit.rep(2)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSequence2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123");
        String result = null;
        try {
            result = seq(letter, digit.rep(2)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSequence3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a23");
        String result = null;
        try {
            result = seq(letter, digit.rep(2)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a23";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSequence4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a234");
        String result = null;
        try {
            result = seq(letter, digit.rep(2)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a23";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMany1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc123");
        String result = null;
        try {
            result = alpha.many().l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMany2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123abc");
        String result = null;
        try {
            result = alpha.many().l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a");
        String result = null;
        try {
            result = letter.tor(digit).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("1");
        String result = null;
        try {
            result = letter.tor(digit).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("!");
        String result = null;
        try {
            result = letter.tor(digit).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOr4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc123");
        String result = null;
        try {
            result = letter.tor(digit).many().l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abc123";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr5() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("123abc");
        String result = null;
        try {
            result = letter.tor(digit).many().l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "123abc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr6() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("abc!123");
        String result = null;
        try {
            result = letter.tor(digit).many().l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    static Parser<String, Character, Character> a = chr('a');
    static Parser<String, Character, Character> b = chr('b');
    static Parser<String, Character, Character> c = chr('c');

    @Test
    public void testOr7() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = seq(a, b).tor(seq(c, b)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr8() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("cb");
        String result = null;
        try {
            result = seq(a, b).tor(seq(c, b)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "cb";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr9() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("acb");
        String result = null;
        try {
            result = seq(a, b).tor(seq(c, b)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr10() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = seq(a, b).tor(seq(a, c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr11() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ac");
        String result = null;
        try {
            result = seq(a, b).tor(seq(a, c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ac";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr12() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ac");
        String result = null;
        try {
            result = seq(a, b.tor(c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ac";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOr13() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = seq(a, b.tor(c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTor1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ac");
        String result = null;
        try {
            result = seq(a, b).tor(seq(a, c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ac";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTor2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = seq(a, b).tor(seq(a, c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testString() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("avoided");
        String result = null;
        try {
            result = string("avoid", c->chr(c)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "avoid";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }


    @Test
    public void testMor1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMor2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ac");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ac";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMor3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aa");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMor4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ba");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMtor1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ab");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMtor2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ac");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ac";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMtor3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aa");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMtor4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("ba");
        String result = null;
        try {
            result = tor(seq(a, b), seq(a, c), seq(a, a)).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testUpto1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a");
        String result = null;
        try {
            result = a.upto(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testUpto2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aa");
        String result = null;
        try {
            result = a.upto(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testUpto3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aaa");
        String result = null;
        try {
            result = a.upto(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyMin1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a");
        String result = null;
        try {
            result = a.than(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testUpto4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a");
        String result = null;
        try {
            result = a.upto(1).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "a";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyMin2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aa");
        String result = null;
        try {
            result = a.than(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyMin3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aaa");
        String result = null;
        try {
            result = a.than(2).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aaa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyUpto1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("a");
        String result = null;
        try {
            result = a.many(2,4).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyUpto2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aa");
        String result = null;
        try {
            result = a.many(2,4).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyUpto3() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aaa");
        String result = null;
        try {
            result = a.many(2,4).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aaa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyUpto4() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aaaa");
        String result = null;
        try {
            result = a.many(2,4).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aaaa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testManyUpto5() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("aaaaa");
        String result = null;
        try {
            result = a.many(2,4).l().parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "aaaa";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testInteger() {
        System.out.println(methodName(0));
        Integer result = null;
        try {
            result = integer.parse(new StrSource("134"));
        } catch (ParseException e) {
            
        }
        Integer expected = 134;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInteger1() {
        System.out.println(methodName(0));
        Integer result = null;
        try {
            result = integer.parse(new StrSource("-134"));
        } catch (ParseException e) {
            
        }
        Integer expected = -134;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNumber() {
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = number.parse(new StrSource("134.556"));
        } catch (ParseException e) {
            
        }
        Double expected = 134.556;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNumber1() {
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = number.parse(new StrSource("-134.556"));
        } catch (ParseException e) {
            
        }
        Double expected = -134.556;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReduce() {
        StrSource s = new StrSource("123456");
        System.out.println(methodName(0));
        Integer result = null;
        result = reduce(s, 0, digit.l().apply(l->Integer.parseInt(l)).apply((v, u)->v+u));
        Integer expected = 21;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReduceOnParser() {
        StrSource s = new StrSource("123456");
        System.out.println(methodName(0));
        Integer result = null;
        try {
            result = digit.l().apply(l->Integer.parseInt(l)).reduce(()->(Integer)0,(v, u)->v+u).parse(s);
        } catch (ParseException e) {
            
        }
        Integer expected = 21;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRelayToAnotherParser() {
        StrSource s = new StrSource("123abc");
        System.out.println(methodName(0));
        String result = null;
        try {
            result = digit.many().l().relay(letter.many().l(), (u, v)->v+u).parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abc123";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExpr1() {
        StrSource s = new StrSource("100+200-50");
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = expr.parse(s);
        } catch (ParseException e) {
            
        }
        Double expected = 250.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testTerm() {
        StrSource s = new StrSource("12*50/3");
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = term.parse(s);
        } catch (ParseException e) {
            
        }
        Double expected = 200.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExpr2() {
        StrSource s = new StrSource("100+12*50/3-50");
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = expr.parse(s);
        } catch (ParseException e) {
            
        }
        Double expected = 250.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExpr3() {
        StrSource s = new StrSource("100 +   12 * 50/3-50");
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = expr.parse(s);
        } catch (ParseException e) {
            
        }
        Double expected = 250.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testExpr4() {
        StrSource s = new StrSource("100+   ( 5 + 7) * 50/3-50");
        System.out.println(methodName(0));
        Double result = null;
        try {
            result = expr.parse(s);
        } catch (ParseException e) {
            
        }
        Double expected = 250.0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    class Tested {
        double v;
        int q;
        public Tested(double v, int q) {
            this.v = v;
            this.q = q;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tested))
                return false;
            Tested t = (Tested)o;
            return v == t.v && q == t.q;
        }
        
        public String toString() {
            return "v:" + v + " q:" + q;
        }
    }
    
    @Test
    public void testAnd1() {
        StrSource s = new StrSource("112.0L50");
        System.out.println(methodName(0));
        Tested result = null;
        try {
            result = number.and(tor(chr('S').next(integer), chr('L').next(integer))).apply(p->new Tested(p.l(), p.r())).parse(s);
        } catch (ParseException e) {
            
        }
        Tested expected = new Tested(112.0, 50);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeT() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\t"));
        char expected = '\t';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeN() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\n"));
        char expected = '\n';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeBackslash() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\\\"));
        char expected = '\\';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeSingleQuote() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\\'"));
        char expected = '\'';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeDoubleQuote() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\\""));
        char expected = '\"';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeOct() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\101"));
        char expected = 'A';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEscapeHex() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        char result = Parsers.esc.parse(new StrSource("\\u53CB"));
        char expected = '友';
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAccept() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"abcd\"");
        String result = null;
        try {
            result = doubleQuote.accept(c->c.equals("abcd")).parse(s);
        } catch (ParseException e) {

        }
        String expected = "abcd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAccept1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"abcd\"");
        String result = null;
        try {
            result = doubleQuote.accept(c->c.equals("abc")).parse(s);
        } catch (ParseException e) {

        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDoubleQuote() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"abcd\"");
        String result = null;
        try {
            result = doubleQuote.parse(s);
        } catch (ParseException e) {

        }
        String expected = "abcd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDoubleQuote1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"ab\\\"cd\"");
        String result = null;
        try {
            result = doubleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab\"cd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDoubleQuote2() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"ab\\tcd\"");
        String result = null;
        try {
            result = doubleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab\tcd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWholeDoubleQuote() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\"ab\\\"cd\"");
        String result = null;
        try {
            result = wholeDoubleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "\"ab\\\"cd\"";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSingleQuote() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'abcd\'");
        String result = null;
        try {
            result = singleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abcd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSingleQuote1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'ab\\\'cd\'");
        String result = null;
        try {
            result = singleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "ab\'cd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWholeSingleQuote1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'ab\\\'cd\'");
        String result = null;
        try {
            result = wholeSingleQuote.parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "\'ab\\\'cd\'";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSingleQuoteExcept() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'abcd\'");
        String result = null;
        try {
            result = singleQuote.except(c->c.equals("abc")).parse(s);
        } catch (ParseException e) {
            
        }
        String expected = "abcd";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSingleQuoteExcept1() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'abcd\'");
        String result = null;
        try {
            result = singleQuote.except(c->c.equals("abcd")).parse(s);
        } catch (ParseException e) {
            
        }
        String expected = null;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDual() {
        System.out.println(methodName(0));
        StrSource s = new StrSource("\'ab\\\'cd\'");
        P<String, String> result = null;
        try {
            result = singleQuote.dual(wholeSingleQuote).parse(s);
        } catch (ParseException e) {
            
        }
        P<String, String> expected = P.p("ab'cd", "\'ab\\\'cd\'");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
