/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static solver.dp.TspRandomGraphGenerator.generator;

/**
 *
 * @author masao
 */
public class TspNGTest {
    public TspNGTest() {
    }
    
    @Test
    public void testTsp() {
        System.out.println(test.TestUtils.methodName(0));
        Tsp tsp = Tsp.builder(5).e(
                0,1,3,
                0,3,4,
                1,2,5,
                2,0,4,
                2,3,5,
                3,4,3,
                4,0,7,
                4,1,6
        ).build();
        int result = tsp.solve();
        int expected = 22;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDp() {
        System.out.println(test.TestUtils.methodName(0));
        Tsp tsp = Tsp.builder(5).e(
                0,1,3,
                0,3,4,
                1,2,5,
                2,0,4,
                2,3,5,
                3,4,3,
                4,0,7,
                4,1,6
        ).build();
        int result = tsp.solve();
        System.out.println(tsp.dp().toWrappedString());
        int expected = 22;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTime() {
        System.out.println(test.TestUtils.methodName(0));
        Tsp tsp = Tsp.builder(5).e(
                0,1,3,
                0,3,4,
                1,2,5,
                2,0,4,
                2,3,5,
                3,4,3,
                4,0,7,
                4,1,6
        ).build();
        for (int i=0;i<1000;i++) tsp.solve();
        int result = tsp.solve();
        int expected = 22;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale() {
        System.out.println(test.TestUtils.methodName(0));
        Tsp tsp = Tsp.builder(7).e(
                0,1,3,
                0,3,4,
                1,2,5,
                2,0,4,
                2,3,5,
                3,4,3,
                4,0,7,
                4,1,6,
                3,5,5,
                5,4,2,
                5,0,9,
                0,6,4,
                6,5,5,
                3,6,7
        ).build();
        int result = tsp.solve();
        int expected = 33;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        int vertices=25;
        Tsp tsp = new Tsp(vertices,generator(vertices).generate());
        int result = tsp.solve();
        int expected = 10;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

}
