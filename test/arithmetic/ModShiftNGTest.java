/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import static arithmetic.Arithmetic.mods;
import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ModShiftNGTest {
    
    public ModShiftNGTest() {
    }
    @Test
    public void testIntInt() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(-5,10).map(i->mods.o(i.intValue(),5));
        TList<Integer> expected = TList.sof(5,1,2,3,4,5,1,2,3,4,5,1,2,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testDoubleInt() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Double> result = TList.range(-5,10).map(i->mods.o(i.doubleValue(),5));
        TList<Double> expected = TList.sofi(5,1,2,3,4,5,1,2,3,4,5,1,2,3,4).map(i->i.doubleValue());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
