/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.Iterator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static parser.JsonParserNGTest.standardJsonTest;
import parser.ParseException;

/**
 *
 * @author masao
 */
public class JsonParserNGTest {
    
    public JsonParserNGTest() {
    }

    @Test
    public void testParseJson() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String src="{\"m0\":true , \"m1\":\"a\", \"m2\":   \"bb\", \"m3\":\"ccc\"}";
        String srcwos="{\"m0\":true,\"m1\":\"a\",\"m2\":\"bb\",\"m3\":\"ccc\"}";
        JsonLex lexer=new JsonLex(src);
        String result = JsonParser.get("m2",JsonParser::asString).parse(lexer);
        String expected = "bb";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups={"performance"})
    public void testParseStandard() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String target=standardJsonTest;
        JsonLex lexer=new JsonLex(target);
        String result="";
        for(int i=0;i<1000000;i++) result=JsonParser.get("interest",JsonParser::asString).parse(lexer.reset(target));
        String expected = "OK";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test(groups={"performance"})
    public void testParseWithoutSpace() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        String target="{\"ignore1\":250.5,\"ignore2\":\"NG\",\"interest\":\"OK\",\"ignore3\":\"NG\"}";
        JsonLex lexer=new JsonLex(target);
        String result="";
        for(int i=0;i<1000000;i++) result=JsonParser.get("interest",JsonParser::asString).parse(lexer.reset(target));
        String expected = "OK";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    public String testTheBestBase() {
        String src=standardJsonTest;
        Iterator<TokenType> lexer=new JsonLex(src).ignored;
        StringBuilder retval=new StringBuilder();
        while (lexer.hasNext()) retval.append(lexer.next());
        return retval.toString();
    }
    @Test(groups={"performance"})
    public void testMLex() throws Exception {
        System.out.println(test.TestUtils.methodName(0));
        for(int i=0;i<1000000;i++) testTheBestBase();
    }
    /**
     * history for performance improvement.
     * The first generation.
     * all of the syntax were described by parse.
     * still this test is available through parser.JsonParserNGTest.testLoad().
     * 
     * The second generation.
     * performance was drastically improved by 40 times.
     * 2 main reasons behind.
     * 1)char-to-Character boxing-unboxing is removed from parsing compared to the first generation.
     * 2)back track is reduced by having lexical analyzing layer.
     * 
     * The third generation.
     * performance was slightly improved by 50 times compared to the first generation.
     * 2 main resons behind.
     * 1)lexical analyzer is improved by using only the first character to judging which token to analyze.
     *this is barely allowed because of the syntax of JSON.
     * 2)removing Token. even after boxing-unboxing, parsing was relying upon objects which were generated for every token 
     * which is always many. abstaining generation of token by turning to l(), which gets matched part of literal from source.
     * but between tokens, still there is a chance to have spaces. there were choices to handle with them.
     * 
     * The war story with spaces.
     * here are choices to handle those spaces. every choices described here worked. but there were difference 
     * in speed of execution. list them in the order of slower the first.
     * 1) parse them through parser by tr()
     * this scheme is very slow. basically it is like bringing parser back to the first generation in part.
     * still it was faster than the first generation, but was like only 20 times of the first generation.
     * 2) parse them through special parser i() for ignore.
     * this is another idea to ignore tokens in parser.
     * by adding a parser which only ignores ignored TokenType, and putting that parser before, the actual parser 
     * only have to parse the core of it (meaning no need to trimming. refer to schemes below).
     * it was a hard labor to come up with. but finally realized that this is quite close to tr(), because it 
     * requires repetitive parsing on the non-ignorable token. first, to discern where to stop. second, to know
     * what was it.
     * 3) remove spaces at lexical analyze
     * the problem of removing spaces in lexer is that parser cannot see the border between space and token.
     * by parsing from such an lexical analyzer would ends up like this.
     * from:" aaa bbb  ccc "
     * to  :" aaa"," bbb","  ccc"
     * removing prefixing spaces requires to apply trim() at the interpreting stage. it might look silly to 
     * trim it down after knowing that part is space. but this is still much faster than 2).
     * 4) remove spaces at parser
     * lexical analyzer can be translated as an Iterator. to ignore something from Iterator, there is a
     * commonly used class for that purpose, SelectIterator. that was used in the case of 3). 
     * to treat a lexical analyzer as an iterator, it requires one buffer to be read in before. when we 
     * simply filter the tokens from lexer, it would be more direct. thus the parsing gets faster a little.
     * *) future
     * thus, current implementation is relying upon trimming. and a trick is found for trimming. when there
     * is nothing to trim, it's extremely fast. so, if there is no space from the beginning, current parsing
     * which follows the scheme of 4), runs much faster (will save up to 20% of time).
     * it could be a quite good preprocess for JSON. it can be paralleled. but with care. spaces might 
     * exist even in strings. do not take it easy.
     * **)limitation
     * judging from the time it takes for simple lexical analyzing, even at the fastest, it won't go more 
     * than 130 times faster than the first generation.
     * this is a reference time we can always think about and this is taken from the execution of testMLex()
     * of this class.
     */
}
