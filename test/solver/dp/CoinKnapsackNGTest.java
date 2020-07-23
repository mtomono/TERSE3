/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import solver.Luggage;

/**
 *
 * @author masao
 */
public class CoinKnapsackNGTest {
    
    public CoinKnapsackNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testLine17() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(2,3),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        TList<Integer> result = KnapsackDP.knapsack(tested.map(l->p2i(l.weight(),l.value())),17).csolve();
        Integer expected = 27;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }

    @Test
    public void testLine29() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(2,2),
                new Luggage(5,5),
                new Luggage(10,11)
        );
        
        TList<Integer> result = KnapsackDP.knapsack(tested.map(l->p2i(l.weight(),l.value())),29).csolve();
        Integer expected = 31;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
