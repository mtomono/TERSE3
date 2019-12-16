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
public class GridGraph2NGTest {
    GridGraph<TPoint2i> graph;
    TList<TPoint2i> blocks;
    Metric<List<Integer>> l1=Metric.weighted(Metric.l1(), TList.sof(1,1));
    public GridGraph2NGTest() {
        graph = GridGraph2dBuilder.builder(0,0, 20,20).build();
        blocks = TList.sof(
                p2i(10,0),p2i(11,0),p2i(12,0),
                p2i(11,1),p2i(12,1),p2i(13,1),
                p2i(12,2),p2i(13,2),p2i(14,2),
                p2i(13,3),p2i(14,3),p2i(15,3),
                p2i(14,4),p2i(15,4),p2i(16,4),
                p2i(15,5),p2i(16,5),p2i(17,5),
                p2i(16,6),p2i(17,6),p2i(18,6),
                p2i(17,7),p2i(18,7),p2i(19,7),
                p2i(18,8),p2i(19,8),p2i(20,8),
                p2i(19,9),p2i(20,9),
                p2i(20,10)
        );
    }

    @Test
    public void testFindCost() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint2i> d = NodeGraphBuilder.builder(graph,p2i(10,0),p2i(20,9)).earlyExit().astar(l1).white(blocks).build();
        d.fill();
        double result = d.findCost().get();
        double expected = 19;
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
        TList<TPoint2i> expected = TList.sof(p2i(1,0), p2i(2,0), p2i(2,1), p2i(2,2), p2i(2,3), p2i(3,3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
