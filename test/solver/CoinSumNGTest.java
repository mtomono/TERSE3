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
public class CoinSumNGTest {
    
    public CoinSumNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,6);
        Result<Boolean> result = new CoinSum().target(tested).solve(15);
        boolean expected = true;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testLine() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,6);
        TList<Boolean> result = new CoinSumLine().solve(tested,15);
        Boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), (Boolean)expected);
    }
    @Test
    public void testBasic14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2);
        Result<Boolean> result = new CoinSum().target(tested).solve(13);
        boolean expected = true;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testLine14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2);
        TList<Boolean> result = new CoinSumLine().solve(tested,13);
        Boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), (Boolean)expected);
    }
    @Test
    public void testBasic10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(6,12,7,23);
        Result<Boolean> result = new CoinSum().target(tested).solve(10);
        boolean expected = false;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testLine10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(6,12,7,23);
        TList<Boolean> result = new CoinSumLine().solve0(tested,10);
        Boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), (Boolean)expected);
    }
}
