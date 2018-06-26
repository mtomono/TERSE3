/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import java.util.TreeMap;
import orderedSet.Comparators;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
import shapeCollection.Grid;
import static solver.Solvers.extract2i;
import test.TestUtils;

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
        
        int result = new CoinKnapsack().target(Solvers.extract2i(tested,Luggage::weight,Luggage::value)).solve(17).value;
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
        
        Result<Integer> result = new CoinKnapsack().target(Solvers.extract2i(tested,Luggage::weight,Luggage::value)).solve(29);
        int expected = 31;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Integer)expected);
    }
}
