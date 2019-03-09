/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidents;

import collection.ListSequentialList;
import collection.ReverseSequentialList;
import static collection.c.a2l;
import static java.util.Collections.emptyList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * incident happened whe
 * @author masao
 */
public class BUG201903060001 {
    
    public BUG201903060001() {
    }

    @Test
    public void testReenact() {
        System.out.println(test.TestUtils.methodName(0));
        ReverseSequentialList<Integer> tested = new ReverseSequentialList<>(
                                                     new ListSequentialList<>(
                                                        a2l(a2l(1,2,3))
                                                    )
                                                );
        System.out.println(tested.monitor());
        ListIterator<Integer> iter = tested.listIterator(1);
        System.out.println(iter.next());
        System.out.println(iter.next());
        assertFalse(iter.hasNext());
        List<Integer> result = tested.subList(1, 3);
        List<Integer> expected = a2l(2,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTypeOfEmptyList() {
        System.out.println(emptyList() instanceof RandomAccess);
    }
}
