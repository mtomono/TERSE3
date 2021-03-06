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

/**
 *
 * @author masao
 */
public class CoinSumShortestNGTest {
    
    public CoinSumShortestNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testLine12() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,7);
        TList<Integer> result = KnapsackDP.shortest(tested,12).csolve();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testLine29() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,11,8,2);
        TList<Integer> result = KnapsackDP.shortest(tested,29).csolve();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testLine26() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Integer> result = KnapsackDP.shortest(tested,26).csolve();
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testLine34() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Integer> result = KnapsackDP.shortest(tested,34).csolve();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
