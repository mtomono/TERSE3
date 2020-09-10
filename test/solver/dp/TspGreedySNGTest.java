/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.TList;
import static function.ComparePolicy.inc;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static solver.dp.TspRandomGraphGenerator.generator;
import test.Clock;

/**
 *
 * @author masao
 */
public class TspGreedySNGTest {
    
    public TspGreedySNGTest() {
    }
    @Test
    public void testScaleR() {
        Clock p = new Clock();
        p.record();
        System.out.println(test.TestUtils.methodName(0));
        int vertices=20000;
        int[][] map=generator(vertices).generate();
        p.record("numbers generated").show();
        TspGreedyS tsp = new TspGreedyS(vertices,map);
        p.record("ready").show();
        TList<Integer> tested = tsp.solve();
        p.record("solved").show();
        TList<Integer> result = tested.sortTo(inc(i->i));
        TList<Integer> expected = TList.range(0,vertices);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        p.record();
        System.out.println(p.laps());
        System.out.println(p.total());
    }
    
}
