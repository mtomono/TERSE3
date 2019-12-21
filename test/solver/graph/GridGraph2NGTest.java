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
public class GridGraph2NGTest {
    GridGraph graph;
    TList<List<Integer>> blocks;
    Metric<List<Integer>> l1=Metric.<Double>l1().morph(Metric.weight(TList.sof(1,1)));
    public GridGraph2NGTest() {
        graph = GridGraphBuilder.builder(0,0, 20,20).build();
        blocks = TList.sof(
                pni(10,0),pni(11,0),pni(12,0),
                pni(11,1),pni(12,1),pni(13,1),
                pni(12,2),pni(13,2),pni(14,2),
                pni(13,3),pni(14,3),pni(15,3),
                pni(14,4),pni(15,4),pni(16,4),
                pni(15,5),pni(16,5),pni(17,5),
                pni(16,6),pni(17,6),pni(18,6),
                pni(17,7),pni(18,7),pni(19,7),
                pni(18,8),pni(19,8),pni(20,8),
                pni(19,9),pni(20,9),
                pni(20,10)
        );
    }

    @Test
    public void testFindCost() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0),pni(20,9)).earlyExit().astar(l1).white(blocks).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(1,0),pni(3,3)).earlyExit().astar(l1).block(blocks).build();
        d.fill();
        TList<List<Integer>> result= d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(1,0), pni(2,0), pni(2,1), pni(2,2), pni(2,3), pni(3,3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
