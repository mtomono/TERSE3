/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.P;
import collection.TList;
import java.util.TreeMap;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new KnapsackBasic<>(tested, Luggage::weight, Luggage::value).solve(15);
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBasicLarge() {
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
        
        int result = new KnapsackBasic<>(tested, Luggage::weight, Luggage::value).solve(60);
        int expected = 92;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBasicMemo() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Luggage> tested = TList.of(
                new Luggage(11,15),
                new Luggage(4,4),
                new Luggage(2,3),
                new Luggage(1,2),
                new Luggage(3,1),
                new Luggage(5,8)
        );
        
        int result = new KnapsackBasicMemo<>(tested, Luggage::weight, Luggage::value, new TreeMap<>(P.comp())).solve(15);
        int expected = 20;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testBasicMemoLarge() {
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
        
        int result = new KnapsackBasicMemo<>(tested, Luggage::weight, Luggage::value, new TreeMap<>(P.comp())).solve(60);
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
        
        int result = Knapsack.<Luggage>solve(new Knapsack(),15,tested,Luggage::weight,Luggage::value).value;
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
        
        int result = Knapsack.<Luggage>solve(new KnapsackMemo(new TreeMap<>(P.comp())),15,tested,Luggage::weight,Luggage::value).value;
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
        
        int result = Knapsack.<Luggage>solve(new KnapsackMemo(new TreeMap<>(P.comp())),60,tested,Luggage::weight,Luggage::value).value;
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
        
        String result = Knapsack.<Luggage>solveElements(new Knapsack(),15,tested,Luggage::weight,Luggage::value).toWrappedString();
        String expected = "" 
                + "weight:1 value:2\n"
                + "weight:2 value:3\n"
                + "weight:11 value:15";
        System.out.println(TestUtils.toStringCode(result));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
