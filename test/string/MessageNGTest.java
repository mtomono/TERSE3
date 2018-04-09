/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import static collection.c.a2l;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class MessageNGTest {
    
    public MessageNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testNl_String() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").toString();
        String expected = "test";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_double() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl(1.0).toString();
        String expected = "1.0";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_int() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl(1).toString();
        String expected = "1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_long() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl(1L).toString();
        String expected = "1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_char() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl('c').toString();
        String expected = "c";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_Object() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl(a2l(0, 1)).toString();
        String expected = "[0, 1]";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNl_0args() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl().toString();
        String expected = "";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_String() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c("of").toString();
        String expected = "testof";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_char() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c('?').toString();
        String expected = "test?";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_double() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c(1.0).toString();
        String expected = "test1.0";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_int() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c(1).toString();
        String expected = "test1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_long() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c(1L).toString();
        String expected = "test1";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC_Object() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").c(a2l(0, 1)).toString();
        String expected = "test[0, 1]";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testS_int() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").s(2).toString();
        String expected = "test  ";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testS_0args() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").s().toString();
        String expected = "test ";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSto() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").sto(10).c("test").toString();
        String expected = "test      test";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSto2() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("testtesttest").sto(10).c("test").toString();
        String expected = "testtesttest\n          test";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCr() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").cr().c("test").toString();
        String expected = "test\ntest";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRep() {
        System.out.println(test.TestUtils.methodName(0));
        String result = Message.nl("test").cr().lines(a2l(1, 2, 3), i->Strings.n('c', i)).toString();
        String expected = "test\nc\ncc\nccc";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
        //already tested (see other tests).
    }
}
