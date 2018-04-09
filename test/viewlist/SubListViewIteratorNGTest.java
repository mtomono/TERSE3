/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewlist;

import viewlist.ReverseView;
import viewlist.SubListViewIterator;
import static iterator.Iterators.toStream;
import static viewlist.SubListViewIterator.decr;
import static viewlist.SubListViewIterator.incr;
import static java.util.Arrays.asList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class SubListViewIteratorNGTest {
    
    public SubListViewIteratorNGTest() {
    }

    @Test
    public void testIncr() {
        List<Integer> tested = asList(0, 1, 2, 3, 4);
        List<List<Integer>> result = toStream(new SubListViewIterator<>(tested, ()->0, incr(0))).collect(toList());
        List<List<Integer>> expected = asList(
                asList(),
                asList(0),
                asList(0, 1),
                asList(0, 1, 2),
                asList(0, 1, 2, 3), 
                asList(0, 1, 2, 3, 4)
        );
        System.out.println("testIncr");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDecr() {
        List<Integer> tested = asList(0, 1, 2, 3, 4);
        List<List<Integer>> result = toStream(new SubListViewIterator<>(tested, ()->0, decr(5))).collect(toList());
        List<List<Integer>> expected = new ReverseView<>(asList(
                asList(),
                asList(0),
                asList(0, 1),
                asList(0, 1, 2),
                asList(0, 1, 2, 3), 
                asList(0, 1, 2, 3, 4)
        ));
        System.out.println("testIncr");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
