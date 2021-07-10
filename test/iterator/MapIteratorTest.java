/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import function.Holder;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtpopeekmpopeeknpopeek
 */
public class MapIteratorTest {
    
    public MapIteratorTest() {
    }

    @Test
    public void replaceInsideOut() {
        List<Integer> tested = Arrays.<Integer>asList(1, 5, 4, 6, 3);
        List<Integer> expected = Arrays.<Integer>asList(1, 6, 10, 16, 19);
        Holder<Integer> h = new Holder<>(0);
        assertTrue(Iterators.test(new MapIterator<>(tested.iterator(), v->h.setget(h.get() + v)), expected.iterator()));
    }
    
}
