/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import static test.TestUtils.className;
import static test.TestUtils.classNamePrint;
import static test.TestUtils.methodName;
import static test.TestUtils.methodNamePrint;

/**
 *
 * @author masao
 */
public class TestUtilsNGTest {
    
    public TestUtilsNGTest() {
    }

    @Test
    public void testMethodName_0args() {
        methodNamePrint();
        assertEquals(methodName(), "testMethodName_0args");
    }
    
    @Test
    public void testMethodName_int() {
        methodNamePrint();
        assertEquals(methodName(0), "testMethodName_int");
    }
    
    @Test
    public void testMethodNamePrint_0args() {
        methodNamePrint();
        SystemOutCatcher c = new SystemOutCatcher();
        methodNamePrint();
        assertEquals(c.end().trim(), "testMethodNamePrint_0args");
    }

    @Test
    public void testMethodNamePrint_int() {
        methodNamePrint();
        SystemOutCatcher c = new SystemOutCatcher();
        methodNamePrint(0);
        assertEquals(c.end().trim(), "testMethodNamePrint_int");
    }

    @Test
    public void testClassName() {
        methodNamePrint();
        assertEquals(className(), "test.TestUtilsNGTest");
        assertEquals(className(0), "test.TestUtilsNGTest");
    }
    
    @Test
    public void testClassNamePrint_0args() {
        methodNamePrint();
        SystemOutCatcher c = new SystemOutCatcher();
        classNamePrint();
        assertEquals(c.end().trim(), "test.TestUtilsNGTest");
    }
    
    @Test
    public void testClassNamePrint_int() {
        methodNamePrint();
        SystemOutCatcher c = new SystemOutCatcher();
        classNamePrint();
        assertEquals(c.end().trim(), "test.TestUtilsNGTest");
    }

    @Test
    public void testClassName_0args() {
        //already tested in testClassName
    }

    @Test
    public void testClassName_int() {
        //already tested in testClassName
    }

    @Test
    public void testLineNumber() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TestUtils.lineNumber(0);
        int expected = 88;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPr_ObjectArr() {
        System.out.println(test.TestUtils.methodName(0));
        SystemOutCatcher c = new SystemOutCatcher();
        TestUtils.pr("what's", "up?");
        String result = c.end().trim();
        String expected = "99:[what's, up?]";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPr_int_ObjectArr() {
        System.out.println(test.TestUtils.methodName(0));
        SystemOutCatcher c = new SystemOutCatcher();
        TestUtils.pr(0, new Object[]{"what's up?"});
        String result = c.end().trim();
        String expected = "111:what's up?";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPrb_int_ObjectArr() {
        System.out.println(test.TestUtils.methodName(0));
        SystemOutCatcher c = new SystemOutCatcher();
        assert TestUtils.prb(0, new Object[]{"what's up?"});
        String result = c.end().trim();
        String expected = "123:what's up?";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPrb_ObjectArr() {
        System.out.println(test.TestUtils.methodName(0));
        SystemOutCatcher c = new SystemOutCatcher();
        assert TestUtils.prb(new Object[]{"what's up?"});
        String result = c.end().trim();
        String expected = "135:what's up?";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    private void method() {
        TestUtils.ppr("what's up?");
    }
    
    @Test
    public void testPpr() {
        System.out.println(test.TestUtils.methodName(0));
        SystemOutCatcher c = new SystemOutCatcher();
        method();
        String result = c.end().trim();
        String expected = "151:what's up?";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
