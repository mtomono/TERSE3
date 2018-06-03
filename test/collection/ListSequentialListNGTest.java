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
public class ListSequentialListNGTest {
    
    public ListSequentialListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testListIterator() {
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        ListSequentialList<Integer> result = new ListSequentialList<>(a2l(a2l(0,1,2), a2l(3,4,5), a2l(6,7,8)));
        List<Integer> expected = a2l(0,1,2,3,4,5,6,7,8);
        System.out.println(result.get(7));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testSet() {
    }

    @Test
    public void testAdd() {
    }
    
}
