/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shapeCollection.GridCoord.gcoord;

/**
 *
 * @author masao
 */
public class GridConcatListNGTest {
    
    public GridConcatListNGTest() {
    }

    @Test
    public void testGet_List_fallsOnStart0() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,1,1);
        Integer expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_List_fallsOnMiddle0() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,3,1);
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_List_fallsOnEnd0() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,4,1);
        Integer expected = 6;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_List_fallsOnStart1() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,5,1);
        Integer expected = 7;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_List_fallsOnMiddle1() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,6,1);
        Integer expected = 8;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_List_fallsOnEnd1() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(1,8,1);
        Integer expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_int_0() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(0);
        Integer expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_int_3() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(3);
        Integer expected = 6;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_int_4() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(4);
        Integer expected = 7;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_int_7() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.get(7);
        Integer expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet_Generic_List() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c)).fix();
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c)).fix();
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        tested.set(1, 1,3,1);
        Integer result = tested.get(1,3,1);
        Integer expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis0 = gcoord(1,1,1, 1,4,1);
        GridCoord axis1 = gcoord(1,5,1, 1,8,1);
        GridX<Integer> base0 = new GridX<>(axis0, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis1, p->p.sumI(c->c));
        GridConcatList<Integer> tested = new GridConcatList<>(1,base0,base1);
        Integer result = tested.size();
        Integer expected = 8;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
