/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import parser.BasicRegex;

/**
 *
 * @author masao
 */
public class TrimNGTest {
    
    public TrimNGTest() {
    }
    @Test
    public void testTEST_NAME() {
        System.out.println(test.TestUtils.methodName(0));
        int result = 0;
    }
    @Test
    public void testTrim() {
        System.out.println(test.TestUtils.methodName(0));
        String str="  123.45 ";
        String result="";
        for (int i=0; i<100000000;i++) result=str.trim();
        String expected = "123.45";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testReplace() {
        System.out.println(test.TestUtils.methodName(0));
        String str="  {\"ignore1\": 250.5, \"ignore2\": \"NG\" , \"interest\": \"OK\",\"ignore3\": \"NG\" }";
        String result="";
        for (int i=0; i<1000000;i++) result=str.replaceAll(BasicRegex.spaces,"");
        String expected = "{\"ignore1\":250.5,\"ignore2\":\"NG\",\"interest\":\"OK\",\"ignore3\":\"NG\"}";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
