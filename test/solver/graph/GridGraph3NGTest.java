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
public class GridGraph3NGTest {
    GridGraph graph;
    TList<List<Integer>> blocks;
    Metric<List<Integer>> l1=GridMetric.l1d().weight(TList.sof(1,1,3)).i();
    public GridGraph3NGTest() {
        graph = GridGraphBuilder.builder(0,0,0, 20,20,20).build();
        blocks = TList.sof(
                pni(10,0,0),pni(11,0,0),pni(12,0,0),
                pni(11,0,1),pni(12,0,1),pni(13,0,1),
                pni(11,1,1),pni(12,1,1),pni(13,1,1),
                pni(12,1,2),pni(13,1,2),pni(14,1,2),
                pni(12,2,2),pni(13,2,2),pni(14,2,2),
                pni(13,2,3),pni(14,2,3),pni(15,2,3),
                pni(13,3,3),pni(14,3,3),pni(15,3,3),
                pni(14,3,4),pni(15,3,4),pni(16,3,4),
                pni(14,4,4),pni(15,4,4),pni(16,4,4),
                pni(15,4,5),pni(16,4,5),pni(17,4,5),
                pni(15,5,5),pni(16,5,5),pni(17,5,5),
                pni(16,5,6),pni(17,5,6),pni(18,5,6),
                pni(16,6,6),pni(17,6,6),pni(18,6,6),
                pni(17,6,7),pni(18,6,7),pni(19,6,7),
                pni(17,7,7),pni(18,7,7),pni(19,7,7),
                pni(18,7,8),pni(19,7,8),pni(20,7,8),
                pni(18,8,8),pni(19,8,8),pni(20,8,8),
                pni(19,8,9),pni(20,8,9),
                pni(19,9,9),pni(20,9,9),
                pni(20,9,10),
                pni(20,10,10)
        );
    }

    @Test
    public void testFindCost() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,9)).earlyExit().astar(l1).white(blocks).build();
        d.fillLoop();
        double result = d.findCost().get();
        double expected = 46;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,9)).earlyExit().astar(l1).white(blocks).build();
        d.fillLoop();
        TList<List<Integer>> result= d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,0,1), pni(12,1,1), pni(12,1,2), pni(12,2,2), pni(13,2,2), pni(13,2,3), pni(13,3,3), pni(14,3,3), pni(14,3,4), pni(14,4,4), pni(15,4,4), pni(15,4,5), pni(15,5,5), pni(16,5,5), pni(16,5,6), pni(16,6,6), pni(17,6,6), pni(17,6,7), pni(17,7,7), pni(18,7,7), pni(18,7,8), pni(18,8,8), pni(19,8,8), pni(19,8,9), pni(19,9,9), pni(20,9,9));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteLoop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,9)).earlyExit().astar(l1).white(blocks).build();
        d.fillLoop();
        TList<List<Integer>> result= d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,0,1), pni(12,1,1), pni(12,1,2), pni(12,2,2), pni(13,2,2), pni(13,2,3), pni(13,3,3), pni(14,3,3), pni(14,3,4), pni(14,4,4), pni(15,4,4), pni(15,4,5), pni(15,5,5), pni(16,5,5), pni(16,5,6), pni(16,6,6), pni(17,6,6), pni(17,6,7), pni(17,7,7), pni(18,7,7), pni(18,7,8), pni(18,8,8), pni(19,8,8), pni(19,8,9), pni(19,9,9), pni(20,9,9));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCost2Recursion() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).fullSearch().astar(l1).build();
        d.fill();
        double result = d.findCost().get();
        double expected = 79.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }


    @Test
    public void testFindCost2Loop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).fullSearch().astar(l1).build();
        d.fillLoop();
        double result = d.findCost().get();
        double expected = 79.0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute2Recursion() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).fullSearch().astar(l1).build();
        d.fill();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(
                pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,1,0), pni(12,1,1), 
                pni(12,1,2), pni(12,1,3), pni(12,1,4), pni(12,2,4), pni(12,3,4), 
                pni(12,3,5), pni(12,3,6), pni(12,4,6), pni(12,5,6), pni(13,5,6), 
                pni(14,5,6), pni(15,5,6), pni(16,5,6), pni(16,6,6), pni(16,6,7), 
                pni(17,6,7), pni(17,7,7), pni(17,7,8), pni(17,7,9), pni(17,7,10), 
                pni(17,7,11), pni(17,7,12), pni(17,7,13), pni(17,7,14), pni(17,7,15), 
                pni(17,7,16), pni(17,7,17), pni(17,7,18), pni(17,7,19), pni(17,8,19), 
                pni(18,8,19), pni(18,8,20), pni(18,9,20), pni(19,9,20), pni(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute2Loop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).fullSearch().astar(l1).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(
                pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,1,0), pni(12,1,1), 
                pni(12,1,2), pni(12,1,3), pni(12,1,4), pni(12,2,4), pni(12,3,4), 
                pni(12,3,5), pni(12,3,6), pni(12,4,6), pni(12,5,6), pni(13,5,6), 
                pni(14,5,6), pni(15,5,6), pni(16,5,6), pni(16,6,6), pni(16,6,7), 
                pni(17,6,7), pni(17,7,7), pni(17,7,8), pni(17,7,9), pni(17,7,10), 
                pni(17,7,11), pni(17,7,12), pni(17,7,13), pni(17,7,14), pni(17,7,15), 
                pni(17,7,16), pni(17,7,17), pni(17,7,18), pni(17,7,19), pni(17,8,19), 
                pni(18,8,19), pni(18,8,20), pni(18,9,20), pni(19,9,20), pni(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteEarly2Recursion() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).earlyExit().astar(l1).build();
        d.fill();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(
                pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,1,0), pni(12,1,1), 
                pni(12,1,2), pni(12,1,3), pni(12,1,4), pni(12,2,4), pni(12,3,4), 
                pni(12,3,5), pni(12,3,6), pni(12,4,6), pni(12,5,6), pni(13,5,6), 
                pni(14,5,6), pni(15,5,6), pni(16,5,6), pni(16,6,6), pni(16,6,7), 
                pni(17,6,7), pni(17,7,7), pni(17,7,8), pni(17,7,9), pni(17,7,10), 
                pni(17,7,11), pni(17,7,12), pni(17,7,13), pni(17,7,14), pni(17,7,15), 
                pni(17,7,16), pni(17,7,17), pni(17,7,18), pni(17,7,19), pni(17,8,19), 
                pni(18,8,19), pni(18,8,20), pni(18,9,20), pni(19,9,20), pni(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteEarly2Loop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,graph),pni(10,0,0),pni(20,9,20)).earlyExit().astar(l1).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(
                pni(10,0,0), pni(11,0,0), pni(12,0,0), pni(12,1,0), pni(12,1,1), 
                pni(12,1,2), pni(12,1,3), pni(12,1,4), pni(12,2,4), pni(12,3,4), 
                pni(12,3,5), pni(12,3,6), pni(12,4,6), pni(12,5,6), pni(13,5,6), 
                pni(14,5,6), pni(15,5,6), pni(16,5,6), pni(16,6,6), pni(16,6,7), 
                pni(17,6,7), pni(17,7,7), pni(17,7,8), pni(17,7,9), pni(17,7,10), 
                pni(17,7,11), pni(17,7,12), pni(17,7,13), pni(17,7,14), pni(17,7,15), 
                pni(17,7,16), pni(17,7,17), pni(17,7,18), pni(17,7,19), pni(17,8,19), 
                pni(18,8,19), pni(18,8,20), pni(18,9,20), pni(19,9,20), pni(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
