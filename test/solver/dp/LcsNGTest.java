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
import static string.Strings.asCharList;
import test.PTe;

/**
 *
 * @author masao
 */
public class LcsNGTest {
    
    public LcsNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasic36() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> x = TList.sof(6,2,3,7,6);
        TList<Integer> y = TList.sof(11,6,2,6,7);
        TList<Integer> result = KnapsackDP.lcs(x,y).psolve();
        PTe.e(KnapsackDP.lcs(x,y).psolvew().toWrappedString());
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
    @Test
    public void testBasic37() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("sisfdadsfav");
        TList<Character> y = asCharList("tfadsfxxxv");
        TList<Integer> result = KnapsackDP.lcs(x,y).psolve();
        PTe.e(KnapsackDP.lcs(x,y).psolvew().toWrappedString());
        Integer expected = 6;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
