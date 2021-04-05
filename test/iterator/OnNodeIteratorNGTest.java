/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class OnNodeIteratorNGTest {
    
    public OnNodeIteratorNGTest() {
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).next().next().get().get();
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPattern01() {
        System.out.println(test.TestUtils.methodName(0));
        int result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).next().next().prev().get().get();
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPattern02() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).prev().get();
        Optional<Integer> expected = Optional.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPattern03() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).prev().next().get();
        Optional<Integer> expected = Optional.of(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPattern04() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).prev().next().next().prev().get();
        Optional<Integer> expected = Optional.of(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPattern05() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = new OnNodeIterator<>(TList.sof(0,1,2,3,4).listIterator()).prev().next().next().next().next().next().next().prev().get();
        Optional<Integer> expected = Optional.of(4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
}
