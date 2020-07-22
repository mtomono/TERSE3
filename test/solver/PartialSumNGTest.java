/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import solver.recur.PartialSumLine;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PartialSumNGTest {
    
    public PartialSumNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6);
        Result<Boolean> result = new PartialSum().target(tested).solve(15);
        boolean expected = true;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testBasic14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6);
        Result<Boolean> result = new PartialSum().target(tested).solve(14);
        boolean expected = true;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testLine14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6);
        TList<Boolean> result = new PartialSumLine().solve(tested,14);
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), (Boolean)expected);
    }
    @Test
    public void testBasic10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        Result<Boolean> result = new PartialSum().target(tested).solve(10);
        boolean expected = false;
        if (result.value) System.out.println(tested.pickUp(result.content));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.value, (Boolean)expected);
    }
    @Test
    public void testLine10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Boolean> result = new PartialSumLine().solve(tested,10);
        boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), (Boolean)expected);
    }
}
