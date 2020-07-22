/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import solver.recur.KnapsackLine;
import collection.TList;
import debug.Te;
import java.util.TreeMap;
import orderedSet.Comparators;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;
import test.TestUtils;

/**
 *
 * @author masao
 */
public class KnapsackNGTest {
    
    public KnapsackNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testBasic() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.of(
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8)
        );
        
        int result = new KnapsackBasic().solve(15,tested);
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test(enabled=false)
    public void testBasicLarge() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.of(
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8)
        );
        
        int result = new KnapsackBasic().solve(60,tested);
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBasicMemo() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.of(
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8)
        );
        
        int result = new KnapsackBasicMemo().solve(15,tested);
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testBasicMemoLarge() {
        System.out.println(test.TestUtils.methodName(0));
    TList<TPoint2i> tested = TList.of(
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8),
                new TPoint2i(11,15),
                new TPoint2i(4,4),
                new TPoint2i(2,3),
                new TPoint2i(1,2),
                new TPoint2i(3,1),
                new TPoint2i(5,8)
        );
        
        int result = new KnapsackBasicMemo().solve(60,tested);
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }


    @Test
    public void testKnapsack() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).solve(15).value;
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test(enabled=false)
    public void testKnapsackLarge() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).solve(60).value;
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackMemo() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).memo(new Grid<>(tested.size(),15)).solve(15).value;
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackMemoLarge() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).memo(new Grid<>(tested.size(),60)).solve(60).value;
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackInside() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        String result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).memo().solve(15).render(tested).toWrappedString();
        String expected = "" 
                + "weight:1 value:2\n"
                + "weight:2 value:3\n"
                + "weight:11 value:15";
        System.out.println(TestUtils.toStringCode(result));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackMemoSparse() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new Knapsack().target(tested.map(l->p2i(l.weight(),l.value()))).memo(new TreeMap<>(Comparators.<TPoint2i>sof(p->p.x, p->p.y).compile())).solve(15).value;
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackFlat() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        Integer result = Te.e(KnapsackFlat.solve(tested.map(l->p2i(l.weight(),l.value())),15)).get(0,15);
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackFlatLarge() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = Te.e(KnapsackFlat.solve(tested.map(l->p2i(l.weight(),l.value())),60)).get(0,60);
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testKnapsackLine() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        Integer result = Te.e(KnapsackLine.solve(tested.map(l->p2i(l.weight(),l.value())),15)).get(15);
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackLineLarge() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = Te.e(KnapsackLine.solve(tested.map(l->p2i(l.weight(),l.value())),60)).get(60);
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackLine0() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        Integer result = Te.e(KnapsackLine.solve0(tested.map(l->p2i(l.weight(),l.value())),15)).get(15);
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsackLine0Large() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8),
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = Te.e(KnapsackLine.solve0(tested.map(l->p2i(l.weight(),l.value())),60)).get(60);
        int expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
