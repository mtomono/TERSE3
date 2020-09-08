/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import debug.Te;
import static function.ComparePolicy.inc;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static solver.dp.TspRandomGraphGenerator.generator;

/**
 *
 * @author masao
 */
public class TspGreedyNGTest {
    
    public TspGreedyNGTest() {
    }
    @Test
    public void testScaleR() {
        System.out.println(test.TestUtils.methodName(0));
        int vertices=20000;
        int[][] map=generator(vertices).generate();
        System.out.println("numbers generated");
        TspGreedy tsp = new TspGreedy(vertices,map);
        System.out.println("ready");
        TList<Integer> tested = Te.e(TList.set(wrap(tsp.solve())));
        TList<Integer> result = tested.sortTo(inc(i->i));
        TList<Integer> expected = TList.range(0,vertices);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
