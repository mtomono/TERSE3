/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;
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
        
        int result = new Knapsack().solve(15,Solvers.extract2i(tested,Luggage::weight,Luggage::value)).value;
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testKnapsack2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = Knapsack.solve(new Knapsack(), 15,tested,Luggage::weight,Luggage::value).value;
        int expected = 20;
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
        
        int result = Knapsack.solve(new KnapsackMemoMap(tested.size(),15),15,tested,Luggage::weight,Luggage::value).value;
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
        
        int result = Knapsack.solve(new KnapsackMemoMap(tested.size(),60),60,tested,Luggage::weight,Luggage::value).value;
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
        
        String result = Knapsack.solveElements(new Knapsack(),15,tested,Luggage::weight,Luggage::value).toWrappedString();
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
        
        int result = Knapsack.solve(new KnapsackMemoSparse(),15,tested,Luggage::weight,Luggage::value).value;
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
        
        Integer result = KnapsackFlat.solve(tested.map(l->p2i(l.weight(),l.value())),15).get(0,15);
        Integer expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
