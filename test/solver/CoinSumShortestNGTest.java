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
    public void testBasic12() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,7);
        Result<Boolean> result = new CoinSumShortest().target(tested).solve(12);
        int expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.content.size(), expected);
    }
    @Test
    public void testLine12() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,7);
        TList<Integer> result = new CoinSumShortestLine().solve(tested,12);
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testBasic29() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,11,8,2);
        Result<Boolean> result = new CoinSumShortest().target(tested).solve(29);
        int expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.content.size(), expected);
    }
    @Test
    public void testLine29() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,11,8,2);
        TList<Integer> result = new CoinSumShortestLine().solve(tested,29);
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testBasic26() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        Result<Boolean> result = new CoinSumShortest().target(tested).solve(26);
        int expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.content.size(), expected);
    }
    @Test
    public void testLine26() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Integer> result = new CoinSumShortestLine().solve(tested,26);
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testBasic34() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        Result<Boolean> result = new CoinSumShortest().target(tested).solve(34);
        int expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.content.size(), expected);
    }
    @Test
    public void testLine34() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Integer> result = new CoinSumShortestLine().solve(tested,34);
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
