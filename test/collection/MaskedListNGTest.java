/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.BitSet;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class MaskedListNGTest {
    
    public MaskedListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testListIterator() {
        BitSet bitset = new BitSet();
        bitset.set(1);
        bitset.set(3);
        bitset.set(4);
        System.out.println(test.TestUtils.methodName(0));
        MaskedList<Integer> result = new MaskedList<>(a2l(0, 1, 2, 3, 4, 5), bitset);
        List<Integer> expected = a2l(1, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    
}
