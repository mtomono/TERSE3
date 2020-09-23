/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TListSpecificUsageNGTest {
    
    public TListSpecificUsageNGTest() {
    }
    
    @Test
    public void testTeeAndPair() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested=TList.sof(0,1,2,3);
        TList<Integer> result = tested.transform(l->l.pair(l.map(i->i*2),(a,b)->a+b));
        TList<Integer> expected = TList.sof(0,3,6,9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
