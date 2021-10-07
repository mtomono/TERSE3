/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.Optional;
import java.util.function.Predicate;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class OneNGTest {
    
    public interface One<T> {
        T get();
        public static <T> One<T> of(T body) {
            return ()->body;
        }
        static public <T> T incase(T t, Predicate<? super T> pred, T value) {
            return pred.test(t)?value:t;
        }
        default One<T> incase(Predicate<? super T> pred, T value) {
            return ()->incase(get(),pred,value);
        }
    }
    
    @Test
    public void testIncase_3args() {
        System.out.println(test.TestUtils.methodName(0));
        Integer tested=5;
        Integer result = One.incase(tested,x->x%3==0,0);
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIncase_3argsHit() {
        System.out.println(test.TestUtils.methodName(0));
        Integer tested=6;
        Integer result = One.incase(tested,x->x%3==0,0);
        Integer expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIncase_Predicate_GenericType() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = One.of(5).incase(x->x%3==0,0).get();
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    /**
     * here are 3 performance tests for 3 variations of incase().
     * 1)ordinary function which takes original value as first parameter.
     * 2)optional
     * 3)one
     * when executed 3) was 150 times slower than others.
     * 1) was just a bit faster than 2). but acceptable enough to have this 
     * versatile context of optional. as a conclusion, 2) is the best choice.
     * thus One is removed.
     */
    @Test(groups="performance")
    public void testIncase_3argsPerformance() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = One.incase(5,x->x%3==0,0);
        for (int i=0; i<1000000000; i++)
            result = One.incase(5,x->x%3==0,0);
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 0.019s

    @Test(groups="performance")
    public void testIncase_OptionalPerformance() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = Optional.of(5).map(x->x%3==0?0:x).get();
        for (int i=0; i<1000000000; i++)
            result = Optional.of(5).map(x->x%3==0?0:x).get();
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 0.02s
    
    @Test(groups="performance")
    public void testIncase_Predicate_GenericTypePerformance() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = One.of(5).incase(x->x%3==0,0).get();
        for (int i=0; i<1000000000; i++)
            result = One.of(5).incase(x->x%3==0,0).get();
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 3.126s

    /**
     * here are additional tests to eradicate the influence of caching.
     * now used i instead of 5 as target.
     * now everything got slower (even 1).
     * still 3 was slower than the others though the outcome performance got 
     * much closer.
     * the winner remains the same. 2.
     */
    @Test(groups="performance")
    public void testIncase_3argsPerformance2() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = One.incase(5,x->x%3==0,0);
        for (int i=0; i<1000000000; i++)
            result = One.incase(i,x->x%3==0,0);
        Integer expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 3.075s

    @Test(groups="performance")
    public void testIncase_OptionalPerformance2() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = Optional.of(5).map(x->x%3==0?0:x).get();
        for (int i=0; i<1000000000; i++)
            result = Optional.of(i).map(x->x%3==0?0:x).get();
        Integer expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 2.761s

    @Test(groups="performance")
    public void testIncase_Predicate_GenericTypePerformance2() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = One.of(5).incase(x->x%3==0,0).get();
        for (int i=0; i<1000000000; i++)
            result = One.of(i).incase(x->x%3==0,0).get();
        Integer expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    } //takes 4.955s
}
