/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static java.lang.Integer.min;
import orderedSet.Range;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class SegmentTreeNGTest {
    
    public SegmentTreeNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testGet() {
        System.out.println(test.TestUtils.methodName(0));
        SegmentTree<Integer> st = new SegmentTree<>(TList.of(5, 2, 3, 1, 4, 7, 9), Integer.MAX_VALUE, (a,b)->min(a,b));
        int result = st.get(Range.create(2,4));
        int expected = 1;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSetGet() {
        System.out.println(test.TestUtils.methodName(0));
        SegmentTree<Integer> st = new SegmentTree<>(TList.of(5, 2, 3, 1, 4, 7, 9), Integer.MAX_VALUE, (a,b)->min(a,b));
        st.set(3, 5);
        int result = st.get(Range.create(2,4));
        int expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
