/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p3i;
import shape.TPoint3i;

/**
 *
 * @author masao
 */
public class GridGraphNGTest3 {
    GridGraph<TPoint3i> graph;
    TList<TPoint3i> blocks;
    public GridGraphNGTest3() {
        graph = GridGraph3dBuilder.builder(0,0,0, 20,20,20).build();
        blocks = TList.sof(
                p3i(10,0,0),p3i(11,0,0),p3i(12,0,0),
                p3i(11,0,1),p3i(12,0,1),p3i(13,0,1),
                p3i(11,1,1),p3i(12,1,1),p3i(13,1,1),
                p3i(12,1,2),p3i(13,1,2),p3i(14,1,2),
                p3i(12,2,2),p3i(13,2,2),p3i(14,2,2),
                p3i(13,2,3),p3i(14,2,3),p3i(15,2,3),
                p3i(13,3,3),p3i(14,3,3),p3i(15,3,3),
                p3i(14,3,4),p3i(15,3,4),p3i(16,3,4),
                p3i(14,4,4),p3i(15,4,4),p3i(16,4,4),
                p3i(15,4,5),p3i(16,4,5),p3i(17,4,5),
                p3i(15,5,5),p3i(16,5,5),p3i(17,5,5),
                p3i(16,5,6),p3i(17,5,6),p3i(18,5,6),
                p3i(16,6,6),p3i(17,6,6),p3i(18,6,6),
                p3i(17,6,7),p3i(18,6,7),p3i(19,6,7),
                p3i(17,7,7),p3i(18,7,7),p3i(19,7,7),
                p3i(18,7,8),p3i(19,7,8),p3i(20,7,8),
                p3i(18,8,8),p3i(19,8,8),p3i(20,8,8),
                p3i(19,8,9),p3i(20,8,9),
                p3i(19,9,9),p3i(20,9,9),
                p3i(20,9,10),
                p3i(20,10,10)
        );
    }

    @Test
    public void testFindCost() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,9)).earlyExit().astar().white(blocks).build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,9)).earlyExit().astar().white(blocks).build();
        d.fill();
        TList<TPoint3i> result= d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,0,1), p3i(12,1,1), p3i(12,1,2), p3i(12,2,2), p3i(13,2,2), p3i(13,2,3), p3i(13,3,3), p3i(14,3,3), p3i(14,3,4), p3i(14,4,4), p3i(15,4,4), p3i(15,4,5), p3i(15,5,5), p3i(16,5,5), p3i(16,5,6), p3i(16,6,6), p3i(17,6,6), p3i(17,6,7), p3i(17,7,7), p3i(18,7,7), p3i(18,7,8), p3i(18,8,8), p3i(19,8,8), p3i(19,8,9), p3i(19,9,9), p3i(20,9,9));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteLoop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,9)).earlyExit().astar().white(blocks).build();
        d.fillLoop();
        TList<TPoint3i> result= d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,0,1), p3i(12,1,1), p3i(12,1,2), p3i(12,2,2), p3i(13,2,2), p3i(13,2,3), p3i(13,3,3), p3i(14,3,3), p3i(14,3,4), p3i(14,4,4), p3i(15,4,4), p3i(15,4,5), p3i(15,5,5), p3i(16,5,5), p3i(16,5,6), p3i(16,6,6), p3i(17,6,6), p3i(17,6,7), p3i(17,7,7), p3i(18,7,7), p3i(18,7,8), p3i(18,8,8), p3i(19,8,8), p3i(19,8,9), p3i(19,9,9), p3i(20,9,9));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCost2Recursion() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).fullSearch().astar().build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).fullSearch().astar().build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).fullSearch().astar().build();
        d.fill();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,1,0), p3i(12,1,1), p3i(12,1,2), p3i(12,1,3), p3i(12,1,4), p3i(12,1,5), p3i(12,1,6), p3i(12,1,7), p3i(12,1,8), p3i(12,1,9), p3i(12,1,10), p3i(12,1,11), p3i(12,1,12), p3i(12,1,13), p3i(12,2,13), p3i(12,3,13), p3i(12,3,14), p3i(12,4,14), p3i(12,5,14), p3i(12,5,15), p3i(12,5,16), p3i(12,5,17), p3i(12,5,18), p3i(12,5,19), p3i(13,5,19), p3i(14,5,19), p3i(15,5,19), p3i(16,5,19), p3i(17,5,19), p3i(18,5,19), p3i(19,5,19), p3i(20,5,19), p3i(20,6,19), p3i(20,6,20), p3i(20,7,20), p3i(20,8,20), p3i(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRoute2Loop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).fullSearch().astar().build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,1,0), p3i(12,1,1), p3i(12,1,2), p3i(12,1,3), p3i(12,1,4), p3i(12,1,5), p3i(12,1,6), p3i(12,1,7), p3i(12,1,8), p3i(12,1,9), p3i(12,1,10), p3i(12,1,11), p3i(12,1,12), p3i(12,1,13), p3i(12,2,13), p3i(12,3,13), p3i(12,3,14), p3i(12,4,14), p3i(12,5,14), p3i(12,5,15), p3i(12,5,16), p3i(12,5,17), p3i(12,5,18), p3i(12,5,19), p3i(13,5,19), p3i(14,5,19), p3i(15,5,19), p3i(16,5,19), p3i(17,5,19), p3i(18,5,19), p3i(19,5,19), p3i(20,5,19), p3i(20,6,19), p3i(20,6,20), p3i(20,7,20), p3i(20,8,20), p3i(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteEarly2Recursion() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).earlyExit().astar().build();
        d.fill();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,1,0), p3i(12,1,1), p3i(12,1,2), p3i(12,1,3), p3i(12,1,4), p3i(12,1,5), p3i(12,1,6), p3i(12,1,7), p3i(12,1,8), p3i(12,1,9), p3i(12,1,10), p3i(12,1,11), p3i(12,1,12), p3i(12,1,13), p3i(12,2,13), p3i(12,3,13), p3i(12,3,14), p3i(12,4,14), p3i(12,5,14), p3i(12,5,15), p3i(12,5,16), p3i(12,5,17), p3i(12,5,18), p3i(12,5,19), p3i(13,5,19), p3i(14,5,19), p3i(15,5,19), p3i(16,5,19), p3i(17,5,19), p3i(18,5,19), p3i(19,5,19), p3i(20,5,19), p3i(20,6,19), p3i(20,6,20), p3i(20,7,20), p3i(20,8,20), p3i(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteEarly2Loop() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(graph,p3i(10,0,0),p3i(20,9,20)).earlyExit().astar().build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(10,0,0), p3i(11,0,0), p3i(12,0,0), p3i(12,1,0), p3i(12,1,1), p3i(12,1,2), p3i(12,1,3), p3i(12,1,4), p3i(12,1,5), p3i(12,1,6), p3i(12,1,7), p3i(12,1,8), p3i(12,1,9), p3i(12,1,10), p3i(12,1,11), p3i(12,1,12), p3i(12,1,13), p3i(12,2,13), p3i(12,3,13), p3i(12,3,14), p3i(12,4,14), p3i(12,5,14), p3i(12,5,15), p3i(12,5,16), p3i(12,5,17), p3i(12,5,18), p3i(12,5,19), p3i(13,5,19), p3i(14,5,19), p3i(15,5,19), p3i(16,5,19), p3i(17,5,19), p3i(18,5,19), p3i(19,5,19), p3i(20,5,19), p3i(20,6,19), p3i(20,6,20), p3i(20,7,20), p3i(20,8,20), p3i(20,9,20));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
