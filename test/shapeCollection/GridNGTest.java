/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import shape.TPoint2i;
import static shape.ShapeUtil.p2i;
import string.Strings;
import static string.Strings.asCharList;

/**
 *
 * @author masao
 */
public class GridNGTest {
    Grid<TPoint2i> tested;

    public GridNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
    }

    @DataProvider(name = "get_int_int")
    public Object[][] get_int_int() {
        return new Object[][]{
            {3, 2},
            {4, 2},
            {5, 2},
            {3, 3},
            {4, 3},
            {5, 3},
            {3, 4},
            {4, 4},
            {5, 4},
            {3, 5},
            {4, 5},
            {5, 5},};
    }

    @Test(dataProvider = "get_int_int")
    public void testGet_int_int(int x, int y) {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = tested.get(x, y);
        TPoint2i expected = p2i(x, y);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name = "get_int_int_fail")
    public Object[][] get_int_int_fail() {
        return new Object[][]{
            {2, 4},
            {5, 6},
            {4, 1}
        };
    }

    @Test(dataProvider = "get_int_int_fail", expectedExceptions = AssertionError.class)
    public void testGet_int_int_fail(int x, int y) {
        System.out.println(test.TestUtils.methodName(0));
        TPoint2i result = tested.get(x, y);
        TPoint2i expected = p2i(x, y);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
    }

    @Test
    public void testGetY_int() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = tested.getY(4);
        TList<TPoint2i> expected = TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetX_int() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> result = tested.getX(4);
        TList<TPoint2i> expected = TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetY() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(4, 2), p2i(3, 2)),
                TList.of(p2i(5, 3), p2i(4, 3), p2i(3, 3)),
                TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4)),
                TList.of(p2i(5, 5), p2i(4, 5), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetX() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        tested.cset(p2i(5, 3), TPoint2i.zero);
        TList<TList<TPoint2i>> result = tested.getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(0, 0), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSetY() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        tested.setY(3, TList.nCopies(3, TPoint2i.zero));
        TList<TList<TPoint2i>> result = tested.getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(0, 0), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(0, 0), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(3, 2), p2i(0, 0), p2i(3, 4), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSetX() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        tested.setX(3, TList.nCopies(4, TPoint2i.zero));
        TList<TList<TPoint2i>> result = tested.getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(0, 0), p2i(0, 0), p2i(0, 0), p2i(0, 0))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.clone().getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateX() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.rotateX(1).getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5)),
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateX2() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.rotateX(-1).getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5)),
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateY() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.rotateY(1).getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 3), p2i(4, 3), p2i(3, 3)),
                TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4)),
                TList.of(p2i(5, 5), p2i(4, 5), p2i(3, 5)),
                TList.of(p2i(5, 2), p2i(4, 2), p2i(3, 2))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateY2() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.rotateY(-1).getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 5), p2i(4, 5), p2i(3, 5)),
                TList.of(p2i(5, 2), p2i(4, 2), p2i(3, 2)),
                TList.of(p2i(5, 3), p2i(4, 3), p2i(3, 3)),
                TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShift() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        tested = tested.shift(new TPoint2i(-1, -1));
        System.out.println(tested.get(4, 4));
        TList<TList<TPoint2i>> result = tested.getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 2), p2i(4, 2), p2i(3, 2)),
                TList.of(p2i(5, 3), p2i(4, 3), p2i(3, 3)),
                TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4)),
                TList.of(p2i(5, 5), p2i(4, 5), p2i(3, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverseY() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.reverseY().getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(5, 5), p2i(4, 5), p2i(3, 5)),
                TList.of(p2i(5, 4), p2i(4, 4), p2i(3, 4)),
                TList.of(p2i(5, 3), p2i(4, 3), p2i(3, 3)),
                TList.of(p2i(5, 2), p2i(4, 2), p2i(3, 2))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverseX() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.reverseX().getX();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(3, 2), p2i(3, 3), p2i(3, 4), p2i(3, 5)),
                TList.of(p2i(4, 2), p2i(4, 3), p2i(4, 4), p2i(4, 5)),
                TList.of(p2i(5, 2), p2i(5, 3), p2i(5, 4), p2i(5, 5))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToRightHandSystem() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        TList<TList<TPoint2i>> result = tested.toRightHandedSystem().getY();
        TList<TList<TPoint2i>> expected = TList.of(
                TList.of(p2i(3, 5), p2i(4, 5), p2i(5, 5)),
                TList.of(p2i(3, 4), p2i(4, 4), p2i(5, 4)),
                TList.of(p2i(3, 3), p2i(4, 3), p2i(5, 3)),
                TList.of(p2i(3, 2), p2i(4, 2), p2i(5, 2))
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<TPoint2i> tested = new Grid<>(new TPoint2i(5, 2), new TPoint2i(3, 5), (a, b)->new TPoint2i(a, b));
        System.out.println(tested);
    }

    @Test
    public void testTrim() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = new Grid<>(p2i(0, 0), p2i(4, 4), (a, b)->a*4+b).trim(p2i(2, 2), p2i(5, 5), (a, b)->0).getX();
        TList<TList<Integer>> expected = TList.of(
                TList.of(10, 11, 12, 0),
                TList.of(14, 15, 16, 0),
                TList.of(18, 19, 20, 0),
                TList.of(0, 0, 0, 0)
        );
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConstructor() {
        System.out.println(test.TestUtils.methodName(0));
        String result = new Grid<>(p2i(4, 2), p2i(7, 5), asCharList("0123456789ABCDEF")).toFlatTestString();
        String expected = "0123\n4567\n89AB\nCDEF";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMark() {
        System.out.println(test.TestUtils.methodName(0));
        String result = new Grid<>(p2i(4, 2), p2i(7, 5), (a, b)->" ").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"X").toFlatTestString();
        String expected = Strings.wrap(
                 "    "
                +" XX "
                +" X  "
                +"    ", 4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<Integer> result = new Grid<>(p2i(4, 2), p2i(7, 5), (a, b)->"0").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"1").map(s->Integer.parseInt(s));
        Grid<Integer> expected = new Grid<>(p2i(4, 2), p2i(7,5), Strings.asCharList(
                 "0000"
                +"0110"
                +"0100"
                +"0000"
        ).map(s->s-'0'));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMapByPosition() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<Integer> result = new Grid<>(p2i(4, 2), p2i(7, 5), (a, b)->"0").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"1").mapByPosition((p,s)->Integer.parseInt(s)+p.y);
        Grid<Integer> expected = new Grid<>(p2i(4, 2), p2i(7,5), Strings.asCharList(
                 "2222"
                +"3443"
                +"4544"
                +"5555"
        ).map(s->s-'0'));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<Integer> tested = new Grid<>(p2i(4, 2), p2i(7, 5), (a, b)->"0").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"1").map(s->Integer.parseInt(s));
        Grid<Integer> result = tested.mapByPosition((p,s)->p.y).pair(tested, (a,b)->a+b);
        Grid<Integer> expected = new Grid<>(p2i(4, 2), p2i(7,5), Strings.asCharList(
                 "2222"
                +"3443"
                +"4544"
                +"5555"
        ).map(s->s-'0'));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToRightHandedSystem() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<Integer> result = new Grid<>(p2i(4, 2), p2i(7, 5), (a, b)->"0").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"1").mapByPosition((p,s)->Integer.parseInt(s)+p.y).toRightHandedSystem();
        Grid<Integer> expected = new Grid<>(p2i(4, 2), p2i(7,5), Strings.asCharList(""
                +"5555"
                +"4544"
                +"3443"
                +"2222"
        ).map(s->s-'0'));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFlip() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<Integer> result = new Grid<>(p2i(4, 2), p2i(7, 6), (a, b)->"0").mark(TList.of(p2i(5, 3), p2i(5, 4), p2i(6, 3)), p->"1").mapByPosition((p,s)->Integer.parseInt(s)+p.y).flip();
        Grid<Integer> expected = new Grid<>(p2i(2, 4), p2i(6, 7), Strings.asCharList(
                 "23456"
                +"24556"
                +"24456"
                +"23456"
        ).map(s->s-'0'));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
