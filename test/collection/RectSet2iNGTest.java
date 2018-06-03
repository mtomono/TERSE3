/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import shapeCollection.RectSet2i;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class RectSet2iNGTest {
    public RectSet2iNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSize() {
    }

    TPoint2i p(int x, int y) {
        return new TPoint2i(x, y);
    }

    @DataProvider(name = "contains")
    public Object[][] contains() {
        return new Object[][]{
            {p(0, 4), p(-3, 7), p(-2, 5), true},
            {p(0, 4), p(-3, 7), p(-2, 7), true},
            {p(0, 4), p(-3, 7), p(-2, 8), false},
        };
    }

    @Test(dataProvider = "contains")
    public void testContains(TPoint2i from, TPoint2i to, TPoint2i contained, boolean expected) {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new RectSet2i(from, to).contains(contained);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
