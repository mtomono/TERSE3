/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.TList;
import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import solver.memo.Luggage;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import test.Clock;

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
//        System.out.println(KnapsackDP.knapsack(ttested,15).psolvew().toWrappedString());
        System.out.println(KnapsackDP.knapsack(ttested,15).psolvew().transform(tt->ReverseDP.knapsack(ttested,tt).right().whole().map(p->p.items())).toWrappedString());
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
        System.out.println(KnapsackDP.knapsack(ttested, 60).psolvew().transform(tt->ReverseDP.knapsack(ttested,tt).left().first().next().items()).toString());
        System.out.println(KnapsackDP.knapsack(ttested, 60).psolvew().transform(tt->ReverseDP.knapsack(ttested,tt).left().whole().map(p->p.items())).toWrappedString());
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

    @Test
    public void testKnapsackScaleRandom() {
        Clock p = new Clock();
        p.record();
        System.out.println(test.TestUtils.methodName(0));
        int scale=100000;
        ArrayInt weight=arrayInt(new Random().ints(scale,2,20).toArray());
        ArrayInt value =arrayInt(new Random().ints(scale,0,100).toArray());
        TList<Integer> result = KnapsackDP.knapsack(weight.asList().pair(value.asList(), (a,b)->p2i(a,b)).sfix(),6000).psolve();
        System.out.println(result.last());
    }
}
