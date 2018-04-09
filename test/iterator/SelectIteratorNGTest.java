/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class SelectIteratorNGTest {
    @DataProvider(name="select")
    public Object[][] createSelect() {
        return new Object[][] {
            {Arrays.asList("t0", "--", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "t0", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "t1", "t2", "t3", "--"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "--", "--", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "--", "t0", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "--", "--", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "t1", "t2", "t3", "--", "--"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "--", "t1", "--", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "--", "--", "t1", "--", "--", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "--", "t0", "t1", "t2", "t3", "--", "--"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "--", "t0", "--", "--", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "t0", "--", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("t0", "t1", "t2", "t3"), Arrays.asList("t0", "t1", "t2", "t3")},
            {Arrays.asList("--", "t0"), Arrays.asList("t0")},
            {Arrays.asList("t0", "--"), Arrays.asList("t0")},
            {Arrays.asList("--", "--", "t0"), Arrays.asList("t0")},
            {Arrays.asList("t0", "--", "--"), Arrays.asList("t0")},
            {Arrays.asList("--", "t0", "--"), Arrays.asList("t0")},
            {Arrays.asList("--", "t0", "--", "--"), Arrays.asList("t0")},
            {Arrays.asList("--", "--", "t0", "--"), Arrays.asList("t0")},
            {Arrays.asList("--", "t0", "--"), Arrays.asList("t0")},
            {Arrays.asList("t0"), Arrays.asList("t0")},
            {Arrays.asList("--", "--", "--"), Arrays.asList()},
            {Arrays.asList("--", "--"), Arrays.asList()},
            {Arrays.asList("--"), Arrays.asList()},
            {Arrays.asList(), Arrays.asList()},
        };
    }
    
    public SelectIteratorNGTest() {
    }

    @Test(dataProvider="select")
    public void test_removed(List<String> tested, List<String> expected) {
        System.out.println("test_removed " + tested.toString() + ":" + expected.toString());
        assertTrue(Iterators.test(new SelectIterator<String>(tested.iterator(), n->n!="--"), expected.iterator()));
    }
    
    @Test(dataProvider="select")
    public void test_remained(List<String> tested, List<String> expected) {
        System.out.println("test_remained " + tested.toString() + ":" + expected.toString());
        assertTrue(Iterators.test(new SelectIterator<String>(tested.iterator(), n->n.contains("t")), expected.iterator()));
    }
    
}
