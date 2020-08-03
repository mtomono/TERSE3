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
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class LisNGTest1 {
    
    public LisNGTest1() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(6,2,3,7,6);
        TList<Integer> result = KnapsackDP.longestIncreasingSubsequence(tested).psolve();
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.max(i->i).get(), expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,12,3,8,14,17);
        TList<Integer> result = KnapsackDP.longestIncreasingSubsequence(tested).psolve();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.max(i->i).get(), expected);
    }
}
