/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CsumLimitedANGTest {
    
    public CsumLimitedANGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt numbers = arrayInt(5,2,1,7,6);
        ArrayInt limits = arrayInt(2,2,1,2,2);
        ArrayInt2 result = KnapsackDPa.csumLimited(numbers,limits,36).solvePackages();
        System.out.println(KnapsackDPa.csumLimited(numbers,limits,36).solvePackages().asList().map(a->a.asList()).toWrappedString());
        boolean expected = true;
        System.out.println("result  : "+result.last().last());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last()>=0, expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt numbers = arrayInt(5,2,1,7,6);
        ArrayInt limits = arrayInt(2,0,1,2,2);
        ArrayInt2 result = KnapsackDPa.csumLimited(numbers,limits,39).solvePackages();
        boolean expected = false;
        System.out.println("result  : "+result.last().last());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last()>=0, expected);
    }
    @Test
    public void testBasic21() {
        System.out.println(test.TestUtils.methodName(0));
        ArrayInt numbers = arrayInt(5,2,1,8,45);
        ArrayInt limits = arrayInt(2,0,0,2,2);
        ArrayInt2 result = KnapsackDPa.csumLimited(numbers,limits,21).solvePackages();
        System.out.println(KnapsackDPa.csumLimited(numbers,limits,21).solvePackages().asList2().toWrappedString());
        boolean expected = true;
        System.out.println("result  : "+result.last().last());
        System.out.println("expected: "+expected);
        assertEquals(result.last().last()>=0, expected);
    }
}
