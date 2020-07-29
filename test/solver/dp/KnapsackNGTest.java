/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import solver.Luggage;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import static solver.dp.ReverseDP.knapsack;
import static solver.dp.ReverseDP.knapsack;

/**
 *
 * @author masao
 */
public class KnapsackNGTest {
    
    public KnapsackNGTest() {
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
        
        TList<TPoint2i> ttested = tested.map(l->p2i(l.weight(),l.value())).sfix();
        TList<Integer> result = KnapsackDP.knapsack(ttested,15).psolve();
        System.out.println(KnapsackDP.knapsack(ttested,15).psolvew().toWrappedString());
        System.out.println(KnapsackDP.knapsack(ttested,15).psolvew().transform(tt->knapsack(ttested,tt).items()).toWrappedString());
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
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
        
        TList<TPoint2i> ttested=tested.map(l->p2i(l.weight(),l.value())).sfix();
        TList<Integer> result = KnapsackDP.knapsack(ttested,60).psolve();
        System.out.println(KnapsackDP.knapsack(ttested, 60).psolvew().transform(tt->OneExample.knapsack(ttested,tt).right().next().items()).toString());
        //System.out.println(KnapsackDP.knapsack(ttested,60).psolvew().transform(tt->knapsack(ttested,tt).items()).toWrappedString());
        Integer expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
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
        
        TList<Integer> result = KnapsackDP.knapsack(tested.map(l->p2i(l.weight(),l.value())).sfix(),15).psolve();
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
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
        
        TList<Integer> result = KnapsackDP.knapsack(tested.map(l->p2i(l.weight(),l.value())).sfix(),60).psolve();
        Integer expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    
}
