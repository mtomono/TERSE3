/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.TIterable.generate;
import static collection.c.a2l;
import static collection.c.i2l;
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
public class TIterableDeprecatedNGTest {
    
    public TIterableDeprecatedNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    public void check3(Iterable<Integer> i) {
        check(i.iterator());
        check(i.iterator());
        check(i.iterator());
    }
    
    public void check(Iterator<Integer> iter) {
        List<Integer> result = i2l(iter);
        List<Integer> expected = a2l(1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHeap() {
        System.out.println(methodName(0));
        check3(generate(()->()->1).limit(4).heap(1, (a, b)->a+b));
    }

}
