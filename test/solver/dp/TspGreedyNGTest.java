/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.MVMap;
import collection.P;
import collection.TList;
import debug.Te;
import static function.ComparePolicy.inc;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static solver.dp.TspRandomGraphGenerator.generator;
import static solver.dp.TspRandomGraphGenerator2.generator2;

/**
 *
 * @author masao
 */
public class TspGreedyNGTest {
    
    public TspGreedyNGTest() {
    }
    
    @Test
    public void testTsp() {
        System.out.println(test.TestUtils.methodName(0));
        TspHalfBaked tsp = TspHalfBaked.builder(4).e(
                0,1,2,
                0,2,3,
                1,3,2,
                2,1,5,
                2,3,2,
                3,0,3
        ).build();
        TList<Integer> result = tsp.solve();
        TList<Integer> expected = TList.sof(0,2,1,3,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTime() {
        System.out.println(test.TestUtils.methodName(0));
        TspHalfBaked tsp = TspHalfBaked.builder(5).e(
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
        TList<Integer> result = tsp.solve();
        TList<Integer> expected = TList.sof(0,1,2,3,4,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale() {
        System.out.println(test.TestUtils.methodName(0));
        TspHalfBaked tsp = TspHalfBaked.builder(7).e(
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
        TList<Integer> result = tsp.solve();
        TList<Integer> expected = TList.sof(0,1,2,3,6,5,4,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        int vertices=3000;
        MVMap<Integer,P<Integer,Integer>> map=generator(vertices).generateMap();
        System.out.println("numbers generated");
        TspHalfBaked tsp = new TspHalfBaked(vertices,map);
        System.out.println("ready");
        TList<Integer> tested = Te.e(tsp.solve());
        TList<Integer> result = tested.sortTo(inc(i->i));
        TList<Integer> expected = TList.range(0,vertices).startFrom(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testScaleR2() {
        System.out.println(test.TestUtils.methodName(0));
        int vertices=3000;
        MVMap<Integer,P<Integer,Integer>> map=generator2(vertices,2000).generateMap();
        System.out.println("numbers generated");
        TspHalfBaked tsp = new TspHalfBaked(vertices,map);
        System.out.println("ready");
        TList<Integer> tested = Te.e(tsp.solve());
        TList<Integer> result = tested.sortTo(inc(i->i));
        TList<Integer> expected = TList.range(0,vertices).startFrom(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
