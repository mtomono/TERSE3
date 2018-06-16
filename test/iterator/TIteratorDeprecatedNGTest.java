/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import static collection.TList.toTList;
import static collection.c.a2l;
import static collection.c.i2l;
import static iterator.TIterator.generate;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class TIteratorDeprecatedNGTest {
    
    public TIteratorDeprecatedNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testHeap() {
        System.out.println(methodName(0));
        Iterator<Integer> iter = generate(()->1).limit(5).heap(1, (a, b)->a+b);
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 2, 3, 4, 5, 6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHeap_BinaryOperator() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(2, 5).iterator().heap((a,b)->a+b).stream().collect(toTList());
        TList<Integer> expected = TList.of(2, 5, 9);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHeap_BinaryOperator2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.<Integer>empty().iterator().heap((a,b)->a+b).stream().collect(toTList());
        TList<Integer> expected = TList.empty();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
