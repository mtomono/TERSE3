/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.memo;

import solver.memo.PartialSumCount;
import solver.memo.Result;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PartialSumCountNGTest {
    
    public PartialSumCountNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6);
        TList<Result<Boolean>> result = new PartialSumCount().target(tested).solve(12);
        int expected = 2;
        System.out.println(result.map(r->r.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.size(), expected);
    }
    @Test
    public void testBasic14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,1,1,1);
        TList<Result<Boolean>> result = new PartialSumCount().target(tested).solve(5);
        int expected = 3;
        System.out.println(result.map(r->r.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.size(), expected);
    }
    @Test
    public void testBasic10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Result<Boolean>> result = new PartialSumCount().target(tested).solve(14);
        int expected = 2;
        System.out.println(result.map(r->r.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.size(), expected);
    }
}
