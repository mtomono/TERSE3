/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import static collection.c.a2al;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class OneOrNoneNGTest {
    
    public OneOrNoneNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testToOptional() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OneOrNone<>(0).toOptional();
        Optional<Integer> expected = Optional.of(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToOptional2() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OneOrNone<Integer>().toOptional();
        Optional<Integer> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOptional3() {
        System.out.println(test.TestUtils.methodName(0));
        OneOrNone<Integer> tested = new OneOrNone<>(0);
        tested.remove(0);
        Optional<Integer> result = tested.toOptional();
        Optional<Integer> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>(0);
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>();
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>();
        assertEquals(result.add(0), a2al().add(0));
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>(0);
        assertFalse(result.add(1));
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>(0);
        assertEquals(result.remove(0), (Integer)0);
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>();
        try {
            assertEquals(result.remove(0), (Integer)0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            List<Integer> expected = a2l();
            System.out.println("result  : " + result);
            System.out.println("expected: " + expected);
            assertEquals(result, expected);
        }
    }
    
    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>(0);
        assertEquals(result.set(0, 1), (Integer)0);
        List<Integer> expected = a2l(1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSet2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new OneOrNone<>();
        try {
            assertEquals(result.set(0, 1), (Integer)0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            List<Integer> expected = a2l();
            System.out.println("result  : " + result);
            System.out.println("expected: " + expected);
            assertEquals(result, expected);
        }
    }
}
