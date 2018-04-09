/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static test.OptionalTest.*;

/**
 *
 * @author masao
 */
public class OptionalListOperationsNGTest {
    
    public OptionalListOperationsNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testHasAny() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = OptionalListOperations.hasAny(a2l(e(), e(), o(0)));
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHasAny2() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = OptionalListOperations.hasAny(a2l(e(), e(), e()));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNoEmpty() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = OptionalListOperations.noEmpty(a2l(e(), e(), o(0)));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNoEmpty2() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = OptionalListOperations.noEmpty(a2l(o(1), o(0)));
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOptionize() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = OptionalListOperations.optionize(a2l(0, 1, 2), ()->new ArrayList<>());
        List<Optional<Integer>> expected = a2l(o(0), o(1), o(2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDeoptionize() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = OptionalListOperations.deoptionize(a2l(o(0), o(1), o(2), e()), ()->100, ()->new ArrayList<>());
        List<Integer> expected = a2l(0, 1, 2, 100);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
