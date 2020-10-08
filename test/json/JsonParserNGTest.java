/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
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
    public void testParseJsonxmany() throws ParseException {
        System.out.println(test.TestUtils.methodName(0));
        final String json="  {\"ignore1\": 250.5, \"ignore2\": \"NG\" , \"interest\": \"OK\"    ,\"ignore3\": \"NG\" } ";
        String jsonws="    {   \"ignore1\"   : 250.5  , \"ignore2\" : \"NG\" , \"interest\" :  \"OK\" , \"ignore3\":\"NG\"}";
        String jsonwos="{\"ignore1\":250.5,\"ignore2\":\"NG\",\"interest\":\"OK\",\"ignore3\":\"NG\"}";//implementation of trim has a certain effect to the performance.
        JsonLex lexer=new JsonLex(json);
        String result="";
        for(int i=0;i<1000000;i++) result=JsonParser.get("interest",JsonParser::asString).parse(lexer.reset(json));
        String expected = "OK";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
