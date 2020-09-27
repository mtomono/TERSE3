/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class LisANGTest {
    
    public LisANGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt tested = arrayInt(6,2,3,7,6);
        ArrayInt2 result = KnapsackDPa.lis(tested).solvePackages();
        int expected = 3;
        System.out.println(result.asList2().toWrappedString());
        System.out.println("result  : "+result.last().max(i->i));
        System.out.println("expected: "+expected);
        assertEquals(result.last().max(i->i), expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,12,3,8,14,17);
        TList<Integer> result = KnapsackDP.lis(tested).psolve();
        Integer expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.max(i->i).get(), expected);
    }
}
