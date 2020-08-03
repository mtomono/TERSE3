/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import solver.*;
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
public class NumberOfItemsLeftNGTest {
    
    public NumberOfItemsLeftNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.sof(5,2,1,7,6).pair(TList.sof(2,2,1,2,2),(a,b)->p2i(a,b));
        TList<Integer> result = KnapsackDP.numberOfItemsLeft(tested,36).psolve();
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last()>=0, expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.sof(5,2,1,7,6).pair(TList.sof(2,0,1,2,2),(a,b)->p2i(a,b));
        TList<Integer> result = KnapsackDP.numberOfItemsLeft(tested,39).psolve();
        boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last()>=0, expected);
    }
    @Test
    public void testBasic21() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2i> tested = TList.sof(5,2,1,8,45).pair(TList.sof(2,0,0,2,2),(a,b)->p2i(a,b));
        TList<Integer> result = KnapsackDP.numberOfItemsLeft(tested,21).psolve();
        System.out.println(KnapsackDP.numberOfItemsLeft(tested,21).psolvew().toWrappedString());
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last()>=0, expected);
    }
}
