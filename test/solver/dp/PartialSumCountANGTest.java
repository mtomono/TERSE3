/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import collection.ArrayInt2;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class PartialSumCountANGTest {
    
    public PartialSumCountANGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testLine14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,1,1,1);
        ArrayInt2 result = KnapsackDPa.psumCount(ArrayInt.set(tested),5).solvePackages();
        int expected = 3;
        System.out.println("result  : "+result.last().last());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last(), expected);
    }
    @Test
    public void testLine10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        ArrayInt2 result = KnapsackDPa.psumCount(ArrayInt.set(tested),14).solvePackages();
        int expected = 2;
        System.out.println("result  : "+result.last().last());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last(), expected);
    }
}
