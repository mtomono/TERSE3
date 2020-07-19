/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.pni;

/**
 *
 * @author masao
 */
public class GridGraph0NGTest {
    GridGraph graph;
    TList<List<Integer>> blocks;
    Metric<List<Integer>> l1=GridMetric.l1().weight(TList.sof(1,1,3)).i();
    public GridGraph0NGTest() {
        graph = GridGraphBuilder.builder(0,0, 5,5).build();
        blocks = TList.sof(
                pni(3,4),
                pni(2,4),
                pni(1,3),
                pni(1,2),
                pni(2,1),
                pni(3,1)
        );
    }

    @Test
    public void testFindTime() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(1,0),pni(3,3)).earlyExit().astar(l1).block(blocks).build();
        d.fill();
        double result = d.findCost().get();
        double expected = 7;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(1,0),pni(3,3)).earlyExit().astar(l1).block(blocks).build();
        d.fill();
        TList<List<Integer>> result= d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(1,0), pni(2,0), pni(3,0), pni(4,0), pni(4,1), pni(4,2), pni(4,3), pni(3,3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
