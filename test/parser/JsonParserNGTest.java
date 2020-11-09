/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.function.Function;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static parser.Parsers.*;
import static test.TestUtils.methodName;

public class JsonParserNGTest {

    public static class JsonParserFactory {
        static final Parser<String, Character, String> boolValue = str("true").or(str("false"));
        static final Parser<String, Character, String> nullValue = str("null");
        static final Parser<String, Character, String> value = doubleQuote.or(numberStr).or(boolValue).or(nullValue);

        static final Parser<String, Character, Character> garbage = anyChar.t().except(c->c=='"').many();
        static final Parser<String, Character, Character> skipping = anyChar.t().except(c->c==',').many();
        static final Parser<String, Character, Character> nameSeparator = s(chr(':'));

        static <U> Parser<String, Character, U> create(String targetKey, Function<String, U> f) {
            final Parser<String, Character, U> targetMember =
                    doubleQuote.accept(s->s.equals(targetKey))
                            .next(nameSeparator)
                            .next(s(value.apply(f)));

            final Parser<String, Character, String> skipMember =
                    doubleQuote.except(s->s.equals(targetKey))
                            .prev(nameSeparator
                                    .next(skipping)
                                    .next(garbage));

            return garbage.next(skipMember.many()).next(targetMember);
        }

        static Parser<String, Character, String> create(String targetKey) {
            return create(targetKey, s->s);
        }
    }

    public JsonParserNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsString() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");

        final String result = extractor.parse(new StrSource("{\"interest\": \"OK\"}"));
        final String expected = "OK";

        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsNumber() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, Double> extractor = JsonParserFactory.create("interest", s->Double.parseDouble(s));

        double result = extractor.parse(new StrSource("{\"interest\": 1.0}"));
        double expected = 1.0;

        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsBoolean() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, Boolean> extractor = JsonParserFactory.create("interest", s->Boolean.parseBoolean(s));

        boolean result = extractor.parse(new StrSource("{\"interest\": true}"));

        System.out.println("result  : " + result);
        System.out.println("expected: true");
        assertTrue(result);
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsNull() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest", s->s.equals("null")?null:s);

        final String result = extractor.parse(new StrSource("{\"interest\": null}"));

        System.out.println("result  : " + result);
        System.out.println("expected: null");
        assertNull(result);
    }

    @Test
    public void testExtractJsonValueWhenJsonContainsManyOtherMembers() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");

        final String result = extractor.parse(new StrSource("  {\"ignore1\": \"NG\", \"ignore2\": \"NG\" , \"interest\": \"OK\",\"ignore3\": \"NG\" } "));
        final String expected = "OK";

        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions = parser.ParseException.class)
    public void testExtractJsonValueWhenJsonDoesNotContainTarget() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");

        System.out.println("expectedExceptions: parserParseException");
        extractor.parse(new StrSource("{ \"ignore1\": \"NG\", \"ignore2\": \"NG\" , \"ignore3\": \"NG\" } "));
    }

    final public static String jsonTestStr="  {\"ignore1\": 250.5, \"ignore2\": \"NG\" , \"interest\": \"OK\"    ,\"ignore3\": \"NG\" } ";
    @Test(groups="performance")
    public void testLoad() throws ParseException {
        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");
        final StrSource src = new StrSource(jsonTestStr);
        String result = "";
        for (int i=0;i<1000000/60;i++)
            result=extractor.parse(src.reset(jsonTestStr));
        assertEquals(result,"OK");
    }
    @Test
    public void testJudgeJsonValue() throws ParseException {
        System.out.println(methodName(0));

        final Parser<String, Character, Boolean> judge = JsonParserFactory.create("interest", s->Double.parseDouble(s)>0.5);

        boolean result = judge.parse(new StrSource("{\"interest\": 1.0}"));

        System.out.println("result  : " + result);
        System.out.println("expected: true");
        assertTrue(result);
    }
}
