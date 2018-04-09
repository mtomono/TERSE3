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
public class TListSpecificProblemsTest {
    public TListSpecificProblemsTest() {
    }
    
    @Test
    public void testIndexOfWithInteger() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TList.of(0, 1, 2, 3, 4).indexOf(2);
        int expected = 2;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    class TestX {
    }
    
    @Test
    public void testIndexOfWithTestX() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TestX> tested = TList.of(new TestX(), new TestX(), new TestX(), new TestX(), new TestX());
        int result = tested.indexOf(tested.get(2));
        int expected = 2;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
