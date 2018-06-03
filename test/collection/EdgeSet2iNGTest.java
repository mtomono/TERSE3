/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import shapeCollection.EdgeSet2i;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class EdgeSet2iNGTest {
    
    public EdgeSet2iNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testSize() {
    }

    @Test
    public void testIsEmpty() {
    }
    
    TPoint2i p(int x, int y) {
        return new TPoint2i(x,y);
    }

    @DataProvider(name="contains")
    public Object[][] contains() {
        return new Object[][] {
            {new EdgeSet2i(p(0,1),p(8,0)), p(4,1), true},
            {new EdgeSet2i(p(0,1),p(8,0)), p(5,1), false},
            {new EdgeSet2i(p(8,0),p(0,1)), p(4,1), true},
            {new EdgeSet2i(p(8,0),p(0,1)), p(5,1), false},
            {new EdgeSet2i(p(0,7),p(3,0)), p(1,7), false},
            {new EdgeSet2i(p(0,7),p(3,0)), p(1,6), true},
            {new EdgeSet2i(p(0,7),p(3,0)), p(1,5), true},
            {new EdgeSet2i(p(0,7),p(3,0)), p(1,4), true},
            {new EdgeSet2i(p(0,7),p(3,0)), p(1,3), false},
        };
    }
    @Test(dataProvider="contains")
    public void testContains(EdgeSet2i target, TPoint2i tested, boolean expected) {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = target.contains(tested);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testToArray_0args() {
    }

    @Test
    public void testToArray_GenericType() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testContainsAll() {
    }

    @Test
    public void testAddAll() {
    }

    @Test
    public void testRetainAll() {
    }

    @Test
    public void testRemoveAll() {
    }

    @Test
    public void testClear() {
    }
    
}
