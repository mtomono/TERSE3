/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import collection.TList;
import static function.ChainedBiFunction.b;
import static function.Functions.f;
import java.util.function.Function;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class FunctionsNGTest {
    
    public FunctionsNGTest() {
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
        Function<String,Integer> tested = f(X::addOne).compose(X::parse);
        Integer result = tested.apply("1");
        Integer expected = 2;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testO1() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(Object::toString).compose(X::addOne).compose(X::parse);
        String result = tested.apply("1");
        String expected = "2";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testA() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = f(X::addOne).compose(X::parse).andThen(Object::toString);
        String result = tested.apply("1");
        String expected = "2";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testM0() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = b(X::merge).unify(f(X::addOne).compose(X::parse).andThen(Object::toString),x->x);
        String result = tested.apply("1");
        String expected = "21";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testYaM0() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = TList.sof(f(X::addOne).compose(X::parse).andThen(Object::toString),x->x)
                .stream().reduce((a,b)->b(X::merge).unify(a,b)).get();
        String result = tested.apply("1");
        String expected = "21";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testM1() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,String> tested = b(X::merge).unify(x->x,f(X::addOne).compose(X::parse).andThen(Object::toString));
        String result = tested.apply("1");
        String expected = "12";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
