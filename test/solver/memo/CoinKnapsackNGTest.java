/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.memo;

import solver.memo.Result;
import solver.memo.CoinKnapsack;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;

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
    public void testBasic17() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(2,3),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new CoinKnapsack().target(tested.map(l->p2i(l.weight(),l.value()))).solve(17).value;
        int expected = 27;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBasic29() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(2,2),
                new Luggage(5,5),
                new Luggage(10,11)
        );
        
        Result<Integer> result = new CoinKnapsack().target(tested.map(l->p2i(l.weight(),l.value()))).solve(29);
        int expected = 31;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Integer)expected);
    }
}
