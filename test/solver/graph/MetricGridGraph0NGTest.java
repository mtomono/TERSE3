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
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class MetricGridGraph0NGTest {
    MetricGridGraph<TPoint2i> graph;
    TList<TPoint2i> blocks;
    Metric<List<Integer>> l1=Metric.weighted(Metric.l1(), TList.sof(1,1,3));
    public MetricGridGraph0NGTest() {
        graph = MetricGridGraph2dBuilder.builder(0,0, 5,5).build();
        blocks = TList.sof(
                p2i(3,4),
                p2i(2,4),
                p2i(1,3),
                p2i(1,2),
                p2i(2,1),
                p2i(3,1)
        );
    }

    @Test
    public void testFindTime() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint2i> d = NodeGraphBuilder.builder(graph,p2i(1,0),p2i(3,3)).earlyExit().astar(l1).block(blocks).build();
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
        NodeGraph<TPoint2i> d = NodeGraphBuilder.builder(graph,p2i(1,0),p2i(3,3)).earlyExit().astar(l1).block(blocks).build();
        d.fill();
        TList<TPoint2i> result= d.findRoute();
        TList<TPoint2i> expected = TList.sof(p2i(1,0), p2i(2,0), p2i(3,0), p2i(4,0), p2i(4,1), p2i(4,2), p2i(4,3), p2i(3,3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
