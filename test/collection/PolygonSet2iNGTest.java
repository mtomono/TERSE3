/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import shapeCollection.PolygonSet2i;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class PolygonSet2iNGTest {
    
    public PolygonSet2iNGTest() {
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
    TList<TPoint2i> ps(Integer... v) {
        return TList.of(v).fold(2).filter(l->l.size()==2).map(l->new TPoint2i(l.get(0),l.get(1)));
    }

    @DataProvider(name="contains")
    public Object[][] contains() {
        PolygonSet2i p0 = new PolygonSet2i(ps(0,7, 2,8, 5,7, 6,3, 4,6, 3,3, 2,6, 2,0, 0,7));
        return new Object[][]{
            {p0, p(1,5), true},
            {p0, p(3,5), true},
            {p0, p(1,2), false},
        };
    }
    @Test(dataProvider="contains")
    public void testContains(PolygonSet2i target, TPoint2i tested, boolean expected) {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = target.contains(tested);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name="border")
    public Object[][] border() {
        PolygonSet2i p0 = new PolygonSet2i(ps(0,7, 2,8, 5,7, 6,3, 4,6, 3,3, 2,6, 2,0, 0,7));
        return new Object[][]{
            {p0, p(1,3), true},
            {p0, p(4,5), true},
            {p0, p(1,1), false},
        };
    }
    @Test(dataProvider="border")
    public void testBorder(PolygonSet2i target, TPoint2i tested, boolean expected) {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = target.border().contains(tested);
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
