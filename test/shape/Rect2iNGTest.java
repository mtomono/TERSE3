/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.p2is;

/**
 *
 * @author masao
 */
public class Rect2iNGTest {
    
    public Rect2iNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testEdges() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<TPoint2i>> result = new Rect2i(p2i(6,5),p2i(2,1)).edges();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(6,5),p2i(6,4),p2i(6,3),p2i(6,2),p2i(6,1)),
                TList.of(p2i(6,1),p2i(5,1),p2i(4,1),p2i(3,1),p2i(2,1)),
                TList.of(p2i(2,1),p2i(2,2),p2i(2,3),p2i(2,4),p2i(2,5)),
                TList.of(p2i(2,5),p2i(3,5),p2i(4,5),p2i(5,5),p2i(6,5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    TList<Integer> u = TList.rangeSym(-2,2);
    TList<Integer> d = TList.rangeSym(2,-2);

    @DataProvider(name = "oneToTwo")
    public Object[][] oneToTwo() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), u.map(y->p2i(-2,y)).subList(0,4).append(u.<TPoint2i>map(x->p2i(x,2)))},
            {p2i(-2, 2), p2i(2, -2), d.map(y->p2i(-2,y)).subList(0,4).append(u.<TPoint2i>map(x->p2i(x,-2)))},
            {p2i(2, -2), p2i(-2, 2), u.map(y->p2i(2,y)).subList(0,4).append(d.<TPoint2i>map(x->p2i(x,2)))},
            {p2i(2, 2), p2i(-2, -2), d.map(y->p2i(2,y)).subList(0,4).append(d.<TPoint2i>map(x->p2i(x,-2)))},
        };
    }
    @Test(dataProvider = "oneToTwo")
    public void testOneToTwo(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).oneToTwo();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name = "twoToOne")
    public Object[][] twoToOne() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), d.map(y->p2i(2,y)).subList(0,4).append(d.<TPoint2i>map(x->p2i(x,-2)))},
            {p2i(-2, 2), p2i(2, -2), u.map(y->p2i(2,y)).subList(0,4).append(d.<TPoint2i>map(x->p2i(x,2)))},
            {p2i(2, -2), p2i(-2, 2), d.map(y->p2i(-2,y)).subList(0,4).append(u.<TPoint2i>map(x->p2i(x,-2)))},
            {p2i(2, 2), p2i(-2, -2), u.map(y->p2i(-2,y)).subList(0,4).append(u.<TPoint2i>map(x->p2i(x,2)))},
        };
    }
    @Test(dataProvider = "twoToOne")
    public void testTwoToOne(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).twoToOne();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTwoToOne() {
    }

    @DataProvider(name = "edge0")
    public Object[][] edge0() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), u.map(y->p2i(-2,y))},
            {p2i(-2, 2), p2i(2, -2), d.map(y->p2i(-2,y))},
            {p2i(2, -2), p2i(-2, 2), u.map(y->p2i(2,y))},
            {p2i(2, 2), p2i(-2, -2), d.map(y->p2i(2,y))},
        };
    }
    @Test(dataProvider = "edge0")
    public void testEdge0(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).edge0();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @DataProvider(name = "edge1")
    public Object[][] edge1() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), u.map(x->p2i(x,2))},
            {p2i(-2, 2), p2i(2, -2), u.map(x->p2i(x,-2))},
            {p2i(2, -2), p2i(-2, 2), d.map(x->p2i(x,2))},
            {p2i(2, 2), p2i(-2, -2), d.map(x->p2i(x,-2))},
        };
    }
    @Test(dataProvider = "edge1")
    public void testEdge1(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).edge1();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @DataProvider(name = "edge2")
    public Object[][] edge2() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), d.map(y->p2i(2,y))},
            {p2i(-2, 2), p2i(2, -2), u.map(y->p2i(2,y))},
            {p2i(2, -2), p2i(-2, 2), d.map(y->p2i(-2,y))},
            {p2i(2, 2), p2i(-2, -2), u.map(y->p2i(-2,y))},
        };
    }
    @Test(dataProvider = "edge2")
    public void testEdge2(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).edge2();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @DataProvider(name = "edge3")
    public Object[][] edge3() {
        return new Object[][]{
            {p2i(-2, -2), p2i(2, 2), d.map(x->p2i(x,-2))},
            {p2i(-2, 2), p2i(2, -2), d.map(x->p2i(x,2))},
            {p2i(2, -2), p2i(-2, 2), u.map(x->p2i(x,-2))},
            {p2i(2, 2), p2i(-2, -2), u.map(x->p2i(x,2))},
        };
    }
    @Test(dataProvider = "edge3")
    public void testEdge3(TPoint2i from, TPoint2i to, TList<TPoint2i> expected) {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = new Rect2i(from, to).edge3();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
