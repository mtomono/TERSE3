/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.ChainedFunction.f;
import java.util.function.Function;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ChainedFunctionNGTest {
    
    public ChainedFunctionNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    static class X {
        static Integer parse(String i) {
            return Integer.parseInt(i);
        }
    
        static Integer addOne(Integer i) {
            return i+1;
        }
        
        static String merge(String x, String y) {
            return x+y;
        }
    }

    @Test
    public void testO() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,Integer> tested = f(X::addOne).o(X::parse);
        Integer result = tested.apply("1");
        Integer expected = 2;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO1() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(Object::toString).o(X::addOne).o(X::parse);
        String result = tested.apply("1");
        String expected = "2";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testA() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(X::addOne).o(X::parse).a(Object::toString);
        String result = tested.apply("1");
        String expected = "2";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testM0() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(X::addOne).o(X::parse).a(Object::toString).m0(X::merge);
        String result = tested.apply("1");
        String expected = "21";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testM1() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(X::addOne).o(X::parse).a(Object::toString).m1(X::merge);
        String result = tested.apply("1");
        String expected = "12";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
