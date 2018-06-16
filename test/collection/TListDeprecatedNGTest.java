/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TListDeprecatedNGTest {
    
    public TListDeprecatedNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testHeap() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(1, 2, 4, 8).heap(0, (a,b)->a+b);
        List<Integer> expected = a2l(0, 1, 3, 7, 15);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPreheap_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> result = TList.of(0, 1, 2, 3).map(p->p.toString()).heapFromStart((a,b)->b+a);
        TList<String> expected = TList.of("0", "01", "012", "0123");
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPreheap_Function_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> result = TList.of(0, 1, 2, 3).heapFromStart(f->Integer.toString(f), (a,b)->b+Integer.toString(a));
        TList<String> expected = TList.of("0", "01", "012", "0123");
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
