/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import shape.TPoint3i;
import static shapeCollection.GridCoord.gcoord;

/**
 *
 * @author masao
 */
public class GridXNGTest {
    
    public GridXNGTest() {
    }

    @Test
    public void testGet_IntegerArr() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c));
        int result = tested.get(0,1,2);
        int expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet_IntegerArr_cover() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c));
        for (int i=0; i<axis.size();i++) {
            int result = tested.get(axis.address(i));
            int expected = axis.address(i).sumI(ii->ii);
            System.out.println("result  : " + result);
            System.out.println("expected: " + expected);
            assertEquals(result, expected);
        }
    }
    
    @Test
    public void testSet_P3() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3i addr = new TPoint3i(0,1,2);
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c)).fix();
        tested.set(0, addr);
        int result = tested.get(addr);
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCSet_P3() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3i addr = new TPoint3i(0,1,2);
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c)).fix().cset(0, addr);
        int result = tested.get(addr);
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAsList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<String> tested = new GridX<>(axis, p->p.get(0).toString()+p.get(1)+p.get(2));
        TList<String> result = tested.asList();
        TList<String> expected = TList.sof(
                "000","100","200", 
                "010","110","210",
                "020","120","220",
                
                "001","101","201", 
                "011","111","211",
                "021","121","221",
                
                "002","102","202", 
                "012","112","212",
                "022","122","222");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testTranslate() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c));
        GridCoord translator = gcoord(2,0,0, 0,0,1);
        assertEquals(tested.axis.index(1,0,1),10);
        GridX<Integer> sliced = tested.translate(translator);
        Integer result = sliced.get(1,0,1);
        Integer expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        assertEquals(sliced.axis.index(1,0,1), 4);
    }
    
    @Test
    public void testIntersect_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> sliced = tested.intersect(gcoord(0,0,0, 0,2,0));
        int result = sliced.get(0,2,0);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> tested = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> sliced = tested.intersect(gcoord(0,0,0, 0,2,0));
        TList<Integer> result = sliced.asList();
        TList<Integer> expected = TList.sof(0,1,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReverseYA_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.reverseYA(1);
        int result = tested.get(0,0,0);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReverseYA_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 0,2,0);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.reverseYA(1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(2,1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.reverse(1);
        int result = tested.get(0,0,0);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReverse_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 0,2,0);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.reverse(1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(0,1,2);//result should be this, because the counting order itself is also reversed.
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse_asList_showingReversalEffect() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 0,2,0);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.reverse(1);
        TList<Integer> result = tested.translate(base.axis.straight()).asList();
        TList<Integer> expected = TList.sof(2,1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testShift_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 2,2,2);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.shift(1,-1,1);
        int result = tested.get(1,1,1);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testShift_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(0,0,0, 0,2,0);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.shift(1,-1,1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(0,1,2);//result should be this, because the counting order itself is also shifted.
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }    

    @Test
    public void testRotate_address() {
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1);
        Integer result = tested.get(1,1,1);
        Integer expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotate_asList() {
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(4,5,6,3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRotateAndIntersect_address() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,1,1, 1,4,1));
        Integer result = tested.get(1,1,1);
        Integer expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateAndIntersect_asList() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,1,1, 1,4,1));
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(4, 5, 6, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateAndIntersectShorter_address() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1));
        Integer result = tested.get(1,2,1);
        Integer expected = 5;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateAndIntersectShorter_asList() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1));
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(5, 6, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRotateIntersectAndReverseYA_address() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1)).reverseYA(1);
        Integer result = tested.get(1,2,1);
        Integer expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateIntersectAndReverseYA_asList() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1)).reverseYA(1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(3, 6, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRotateIntersectAndReverse_address() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1)).reverse(1);
        Integer result = tested.get(1,2,1);
        Integer expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotateIntersectAndReverse_asList() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1)).reverse(1);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(5, 6, 3);//result should be this, because the counting order itself is also reversed.
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRotateIntersectAndReverse_asList_showingReversalEffect() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1));
        GridX<Integer> reversed = tested.reverse(1);
        TList<Integer> result = reversed.translate(tested.axis.straight()).asList();
        TList<Integer> expected = TList.sof(3, 6, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFix() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c)).transform(l->l.rotate(1),1).intersect(gcoord(1,2,1, 1,4,1));
        GridX<Integer> tested = base.reverse(1).translate(base.axis.straight()).fix();
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(3, 6, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStraight() {
        GridCoord axis = gcoord(1,1,1, 4,4,4);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c)).transform(l->l.rotate(1),1).straight().intersect(gcoord(1,2,1, 1,4,1));
        GridX<Integer> tested = base.reverse(1).straight().translate(base.axis.straight()).straight().sfix();
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(3, 6, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base0 = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base0.pair(base1, (a,b)->a+b);
        int result = tested.get(1,2,1);
        int expected = 8;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base0 = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> base1 = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base0.pair(base1, (a,b)->a+b);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(6,8,10,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.map(i->i*2);
        int result = tested.get(1,2,1);
        int expected = 8;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.map(i->i*2);
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(6,8,10,12);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAppend_address() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.map(i->i*2);
        int result = tested.get(1,2,1);
        int expected = 8;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAppend_asList() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord axis = gcoord(1,1,1, 1,4,1);
        GridX<Integer> base = new GridX<>(axis, p->p.sumI(c->c));
        GridX<Integer> tested = base.append(base.shift(0,4,0));
        TList<Integer> result = tested.asList();
        TList<Integer> expected = TList.sof(3,4,5,6,3,4,5,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
