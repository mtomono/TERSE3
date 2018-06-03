/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ShiftedScaleNGTest {
    
    public ShiftedScaleNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testPlus() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new ShiftedScale(0).subList(0, 4);
        List<Integer> expected = new Scale().subList(0, 4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name = "shifted")
    public Object[][] shifted() {
        return new Object[][] {
            {0, 5},
            {0, 0},
            {3, 5},
              
        };
    }
    @Test(dataProvider="shifted")
    public void testSize(int from, int to) {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = new ShiftedScale(from).subList(0, to-from);
        List<Integer> expected = new Scale().subList(from, to);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
}
