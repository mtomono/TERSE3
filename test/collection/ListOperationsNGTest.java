/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.ListOperations.find;
import static iterator.Iterators.toStream;
import iterator.ReverseIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class ListOperationsNGTest {
    
    public ListOperationsNGTest() {
    }

    /**
     * lastメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testLast() {
        List<Integer> tested = ListOperations.a2al(0, 0, 0, 4);
        Integer result = ListOperations.last(tested);
        Integer expected = 4;
        System.out.println("testLast");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * endIteratorメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testEndIterator() {
        List<Integer> tested = ListOperations.a2al(0, 1, 2, 3);
        List<Integer> result = toStream(new ReverseIterator<>(ListOperations.endIterator(tested))).collect(toList());
        List<Integer> expected = ListOperations.a2al(3, 2, 1, 0);
        System.out.println("testEndIterator");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * findAboutメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testFindAbout() {
        List<Integer>tested = ListOperations.a2al(0, 0, 0, 3, 4);
        ListIterator<Integer> iter = ListOperations.find(tested.listIterator(), i->i>0);
        Integer result = iter.next();
        Integer expected = 3;
        System.out.println("testFindAbout");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * trimメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testTrim() {
        List<Integer> result = ListOperations.a2al(0, 0, 0, 3, 4);
        ListOperations.trim(result.listIterator(), n->n==0);
        List<Integer> expected = Arrays.asList(3, 4);
        System.out.println("testTrim");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * trimAroundメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testTrimAround() {
        List<Integer> result = ListOperations.a2al(3, 4, 0, 0, 0, 3, 4, 0, 0, 5);
        ListOperations.trimAround(find(result.listIterator(), n->n==0), n->n==0);
        List<Integer> expected = Arrays.asList(3, 4, 3, 4, 0, 0, 5);
        System.out.println("testTrimAround");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * trimEdgeメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testTrimEdge() {
        List<Integer> result = ListOperations.a2al(0, 0, 3, 4, 0, 0, 0, 3, 4, 0);
        ListOperations.trimEdge(result, n->n==0);
        List<Integer> expected = Arrays.asList(3, 4, 0, 0, 0, 3, 4);
        System.out.println("testTrimEdge");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * extractメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testExtract() {
        List<Integer> result = ListOperations.a2al(0, 0, 3, 4, 0, 0, 0, 3, 4, 0);
        List<Integer> removed = new ArrayList<>();
        ListOperations.extract(result.listIterator(), n->n==0, removed);
        List<Integer> expected = Arrays.asList(3, 4, 3, 4);
        System.out.println("testExtract");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    /**
     * replaceメソッドのテスト、クラスListOperationsのテスト。
     */
    @Test
    public void testReplace() {
        List<Integer> result = ListOperations.a2al(0, 0, 3, 4, 0, 0, 0, 3, 4, 0);
        List<Integer> replaced = new ArrayList<>();
        ListOperations.replace(result.listIterator(), n->n==0, n->55, replaced);
        List<Integer> expected = Arrays.asList(55, 55, 3, 4, 55, 55, 55, 3, 4, 55);
        System.out.println("testExtract");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
