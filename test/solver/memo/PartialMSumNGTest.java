/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.memo;

import solver.memo.Result;
import solver.memo.PartialSum;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PartialMSumNGTest {
    
    public PartialMSumNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test(groups={"performance","ifNeeded"})
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6).pair(TList.sof(2,2,1,2,2),(a,b)->TList.nCopies(a, b)).flatMap(l->l);
        Result<Boolean> result = new PartialSum().target(tested).solve(36);
        boolean expected = true;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6).pair(TList.sof(2,0,1,2,2),(a,b)->TList.nCopies(a, b)).flatMap(l->l);
        Result<Boolean> result = new PartialSum().target(tested).memo().solve(39);
        boolean expected = false;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
}
