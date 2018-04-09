/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import static iterator.Iterators.toStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class BeforeIteratorTest {
    
    public BeforeIteratorTest() {
    }

    @Test
    public void test() {
        List<Integer> base = Arrays.asList(1, 2, 3, 4, 9, 1, 2, 3);
        Iterator<Integer> tested = new BeforeIterator<>(base.listIterator(), n->n > 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertEquals(toStream(tested).collect(toList()), expected);
    }
}
