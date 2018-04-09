/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class IteratorsNGTest {
    
    public IteratorsNGTest() {
    }

    @DataProvider(name="test")
    public Object[][] createTest() {
        return new Object[][] {
            { Arrays.asList("test0", "test1", "test2"), Arrays.asList("test0", "test1")},
            { Arrays.asList("test0", "test1"), Arrays.asList("test0", "test1", "test2")},
            { Arrays.asList("test0", "test1"), Arrays.asList("test0", "test2") }, 
            { Arrays.asList("test1", "test1"), Arrays.asList("test0", "test1") }, 
            { Arrays.asList(), Arrays.asList("test0")},
            { Arrays.asList("test1"), Arrays.asList() }
        };
    }

    @Test(dataProvider="test")
    public void testTest_success(List tested1, List tested2) {
        System.out.println("test_success");
        assertTrue(Iterators.test(tested1.iterator(), tested1.iterator()));
    }

    @Test(dataProvider="test")
    public void testTest_fail(List tested1, List tested2) {
        System.out.println("test_fail " + tested1.toString() + ":" + tested2.toString());
        assertFalse(Iterators.test(tested1.iterator(), tested2.iterator()));
    }
}
