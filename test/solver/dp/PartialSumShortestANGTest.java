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
public class PartialSumShortestANGTest {
    
    public PartialSumShortestANGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testLine() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,1,7,6);
        ArrayInt2 result = KnapsackDPa.psumShortest(ArrayInt.set(tested),12).solvePackages();
        int expected = 2;
        System.out.println("result  : "+result.asList().map(a->a.asList()).toWrappedString());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last(), expected);
    }
    @Test
    public void testLine5() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(4,1,1,1);
        ArrayInt2 result = KnapsackDPa.psumShortest(ArrayInt.set(tested),5).solvePackages();
        int expected = 2;
        System.out.println("result  : "+result.asList().map(a->a.asList()).toWrappedString());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last(), expected);
    }
    @Test
    public void testLine14() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        ArrayInt2 result = KnapsackDPa.psumShortest(ArrayInt.set(tested),14).solvePackages();
        int expected = 2;
        System.out.println("result  : "+result.asList().map(a->a.asList()).toWrappedString());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last(), expected);
    }
    @Test
    public void testLine24() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(5,2,12,7,23);
        TList<Integer> result = KnapsackDP.shortest(tested,24).psolve();
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
