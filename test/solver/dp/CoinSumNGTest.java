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
public class CoinSumNGTest {
    
    public CoinSumNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testLine() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(2,1,6);
        TList<Boolean> result = KnapsackDP.possible(tested,15).csolve();
        Boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testLine14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2);
        TList<Boolean> result = KnapsackDP.possible(tested,13).csolve();
        Boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testLine10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(6,12,7,23);
        TList<Boolean> result = KnapsackDP.possible(tested,10).csolve();
        Boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
