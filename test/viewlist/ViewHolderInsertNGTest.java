/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewlist;

import collection.TIterable;
import static iterator.Iterators.test;
import static iterator.Iterators.toStream;
import iterator.MapIterator;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static collection.c.*;

/**
 *
 * @author masao
 */
public class ViewHolderInsertNGTest {
    
    public ViewHolderInsertNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    /**
     * this test shows how to insert item in iterator depending on the difference 
     * between the previous item.
     * in this case, it is showing the way to complement items not to open more 
     * than 10.
     */
    @Test
    public void replaceInsert() {
        //this can be implemented with TList#pair() and TList#flatMap()
        List<Integer> tested = Arrays.asList(1, 13, 17, 25, 36, 49);
        List<Integer> expected = Arrays.asList(1, 11, 13, 17, 25, 35, 36, 46, 49);
        ViewHolder<Integer> h = ViewHolder.pre(1);
        ListIterator<Integer> iter = tested.listIterator();
        List<Integer> result = toStream(new MapIterator<>(iter, 
                n-> {
                    int retval;
                    if (h.t(0, x->n-x<10))
                        retval = n;
                    else {
                        retval = h.g(0) + 10;
                        iter.previous();
                    }
                    h.push(retval);
                    return retval;
                }
            )).collect(toList());
        assertEquals(result, expected);
    }
    
    @Test
    public void replaceScoped() {
        List<String> tested = a2l("t0", "t1", "t2", "t3");
        List<List<Optional<String>>> expected = a2l(
                a2l(Optional.of("t0"), Optional.empty()),
                a2l(Optional.of("t1"), Optional.of("t0")),
                a2l(Optional.of("t2"), Optional.of("t1")),
                a2l(Optional.of("t3"), Optional.of("t2"))
        );
        ViewHolder<String> h = ViewHolder.pre(2);
        Iterable<List<Optional<String>>> iter = new TIterable(()->
                new MapIterator<>(tested.iterator(), 
                    n-> {
                        h.push(n);
                        return h.peek();
                    }
                ));
        assertTrue(test(iter.iterator(), expected.iterator()));
     }

}
