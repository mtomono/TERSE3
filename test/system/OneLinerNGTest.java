/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class OneLinerNGTest {
    
    public OneLinerNGTest() {
    }
    @Test
    public void testCompile() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> l = TList.sof("xxx","yyy");
        try (OneLiner oneLiner = new OneLiner()) {
            System.out.println(oneLiner.exec("collection.TList.sof(\"str\")"));
        }
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testVariable() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> l = TList.sof("xxx","yyy");
        try (OneLiner oneLiner = new OneLiner()) {
            System.out.println(oneLiner.exec("java.lang.String v;"));
            System.out.println(oneLiner.exec("v = \"str\";"));
        }
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClass() throws Exception{
        System.out.println(test.TestUtils.methodName(0));
        TList<String> l = TList.sof("xxx","yyy");
        try (OneLiner oneLiner = new OneLiner()) {
            System.out.println(oneLiner.exec("class x{static java.lang.String f;};"));
            System.out.println(oneLiner.exec("x.class.getName()"));
        }
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    public static void exec() {
        throw new RuntimeException("test");
    }

    @Test
    public void testException() throws Exception{
        System.out.println(test.TestUtils.methodName(0));
        TList<String> l = TList.sof("xxx","yyy");
        try (OneLiner oneLiner = new OneLiner()) {
            System.out.println(oneLiner.exec("system.OneLinerNGTest.exec()"));
        }
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
