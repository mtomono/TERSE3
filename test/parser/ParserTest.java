/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.*;

import java.util.function.Function;

import static parser.Parsers.*;

@RunWith(JUnit4.class)
public class ParserTest {

    public static class JsonParserFactory {
        static final Parser<String, Character, String> boolValue = str("true").or(str("false"));
        static final Parser<String, Character, String> nullValue = str("null");
        static final Parser<String, Character, String> value = doubleQuote.or(numberStr).or(boolValue).or(nullValue); 

        static final Parser<String, Character, Character> garbage = many(anyChar.t().except(c->c=='"').tr());
        static final Parser<String, Character, Character> skipping = many(anyChar.t().except(c->c==','));
        static final Parser<String, Character, Character> nameSeparator = s(chr(':'));

        static <U> Parser<String, Character, U> create(String targetKey, Function<String, U> f) {
            final Parser<String, Character, U> targetMember =
                    doubleQuote.accept(s->s.equals(targetKey))
                            .next(nameSeparator)
                            .next(s(value.apply(f)));

            final Parser<String, Character, String> skipMember =
                    doubleQuote.except(s->s.equals(targetKey)).tr()
                            .prev(nameSeparator
                                    .next(skipping)
                                    .next(garbage));

            return garbage.next(many(skipMember)).next(targetMember);
        }

        static Parser<String, Character, String> create(String targetKey) {
            return create(targetKey, s->s);
        }

    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsString() throws ParseException {
        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");
        final String result = extractor.parse(new StrSource("{\"interest\": \"OK\"}"));

        assertThat(result, is("OK"));
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsNumber() throws ParseException {
        final Parser<String, Character, Double> extractor = JsonParserFactory.create("interest", s->Double.parseDouble(s));

        final Double result = extractor.parse(new StrSource("{\"interest\": 1.0}"));

        assertThat(result, is(1.0));
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsBoolean() throws ParseException {
        final Parser<String, Character, Boolean> extractor = JsonParserFactory.create("interest", s->Boolean.parseBoolean(s));

        final Boolean result = extractor.parse(new StrSource("{\"interest\": true}"));

        assertThat(result, is(true));
    }

    @Test
    public void testExtractJsonValueWhenTargetValueIsNull() throws ParseException {
        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest", s->s.equals("null")?null:s);

        final String result = extractor.parse(new StrSource("{\"interest\": null}"));

        assertNull(result);
    }

    @Test
    public void testExtractJsonValueWhenJsonContainsManyOtherMembers() throws ParseException {
        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");

        final String result = extractor.parse(new StrSource("  {\"ignore1\": \"NG\", \"ignore2\": \"NG\" , \"interest\": \"OK\",\"ignore3\": \"NG\" } "));

        assertThat(result, is("OK"));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testExtractJsonValueWhenJsonIsNotContainsTarget() throws ParseException {
        final Parser<String, Character, String> extractor = JsonParserFactory.create("interest");

        extractor.parse(new StrSource("{ \"ignore1\": \"NG\", \"ignore2\": \"NG\" , \"ignore3\": \"NG\" } "));
    }

    public void testJudgeJsonValue() throws ParseException {
        final Parser<String, Character, Boolean> judge = JsonParserFactory.create("interest", s->Double.parseDouble(s)>0.5);

        final Boolean result = judge.parse(new StrSource("{\"interest\": 1.0}"));

        assertTrue(result);
    }
}
