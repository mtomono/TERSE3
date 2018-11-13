/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.ChainedBiFunction.ff;
import java.util.function.Function;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ChainedBiFunctionNGTest {
    
    public ChainedBiFunctionNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    static class X {
        static Integer parse(String i) {
            return Integer.parseInt(i);
        }
    
        static Integer add(Integer i, Integer j) {
            return i+j;
        }
    }

    @Test
    public void testSomeMethod() {
        System.out.println(test.TestUtils.methodName(0));
        Function<String,Integer> tested = ff(X::add).c0(1).o(X::parse);
        int result = tested.apply("1");
        int expected = 2;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
