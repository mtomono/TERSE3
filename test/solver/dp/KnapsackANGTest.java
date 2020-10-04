/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
import collection.TList;
import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import solver.memo.Luggage;
import test.Clock;

/**
 *
 * @author masao
 */
public class KnapsackANGTest {
    
    public KnapsackANGTest() {
    }

    @Test
    public void testKnapsackArrayLarge() {
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
        
        ArrayInt weight=ArrayInt.set(tested,l->l.weight());
        ArrayInt value=ArrayInt.set(tested,l->l.value());
        ArrayInt2 dp = KnapsackDPa.knapsack(weight,value,60).solvePackages();
        int result=dp.last().last();
        Integer expected = 95;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, (int)expected);
    }

    @Test(groups={"performance"})
    public void testKnapsackArrayScaleRandom() {
        Clock p = new Clock();
        p.record();
        System.out.println(test.TestUtils.methodName(0));
        int scale=100000;
        ArrayInt weight=arrayInt(new Random().ints(scale,2,20).toArray());
        ArrayInt value =arrayInt(new Random().ints(scale,0,100).toArray());
        p.record("data is ready");
        ArrayInt2 dp = KnapsackDPa.knapsack(weight,value,6000).solvePackages();
        p.record("done");
        System.out.println(dp.last().last());
        p.record();
        System.out.println(p.laps());
        System.out.println(p.total());
    }
}
