/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.HashMap;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class GeneralGraphNGTest {
    MetricGeneralGraph<Integer> graph;
    public GeneralGraphNGTest() {
        graph = new MetricGeneralGraph<>(new HashMap<>());
        graph = MetricGeneralGraphBuilder.<Integer>builder()
                .a(0, 1, 1)
                .a(0, 2, 7)
                .a(0, 3, 2)
                .a(1, 4, 2)
                .a(1, 5, 4)
                .a(2, 5, 2)
                .a(2, 6, 3)
                .a(3, 6, 5)
                .a(3, 0, 2)
                .a(4, 5, 1)
                .a(5, 7, 6)
                .a(6, 0, 3)
                .a(6, 7, 2)
                .build();
    }

    @Test
    public void testFindTime() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<Integer> d = NodeGraphBuilder.<Integer>builder(graph,0,7).fullSearch().node().build();
        d.fill();
        double result = d.findCost().get();
        double expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<Integer> d = NodeGraphBuilder.<Integer>builder(graph,0,7).fullSearch().node().build();
        d.fill();
        TList<Integer> result = d.findRoute();
        TList<Integer> expected = TList.sof(0,3,6,7);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
