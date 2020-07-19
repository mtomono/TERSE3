/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import debug.Te;
import static java.lang.Math.sqrt;
import java.util.List;
import java.util.Optional;
import math.VectorOp;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.pni;

/**
 *
 * @author masao
 */
public class MetricCompositeGraphNGTest {
    MetricGraph grid;
    MetricGeneralGraph<List<Integer>> bypass;
    TList<List<Integer>> blocks0;
    TList<List<Integer>> blocks1;
    TList<List<Integer>> blocks2;
    MetricCompositeGraph<List<Integer>> layeredGraph0;
    MetricCompositeGraph<List<Integer>> layeredGraph1;
    Metric<List<Integer>> l2=GridMetric.l2().weight(TList.sof(1,1,3)).i();
    static List<Integer> l(Integer... v) {
        return pni(v);
    }
    public MetricCompositeGraphNGTest() {
        grid = GridGraphBuilder.builder(0,0,0, 20,20,5).build().metricize(l2);
        bypass = MetricGeneralGraphBuilder.<List<Integer>>builder().a(l(1,1,3), l(16,16,3), sqrt(15*15*2)).build();
        layeredGraph0 = new MetricCompositeGraph<>(grid);
        layeredGraph1 = new MetricCompositeGraph<>(grid,bypass);
        blocks0 = TList.sof(l(0,0,0),l(1,0,0),l(2,0,0),l(0,1,0),l(1,1,0),l(2,1,0),l(0,2,0),l(1,2,0),l(2,2,0)
        );
        blocks1 = blocks0.map(p->TList.range(0,6).map(i->VectorOp.addI(p,l(0,0,i)))).sfix().flatMap(l->l).sfix();
        blocks2 = blocks1.map(p->VectorOp.addI(p, l(15,15,0)));
    }
    
    @Test
    public void testShowBlock1() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(blocks1.fold(6).toWrappedString());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShowBlock2() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(blocks2.fold(6).toWrappedString());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions=AssertionError.class)
    public void testFromIsReacheable() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(15,18,0),l(17,15,4)).earlyExit().astar(l2).white(blocks2).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        int expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions=AssertionError.class)
    public void testToIsReacheable() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(15,16,0),l(17,18,4)).earlyExit().astar(l2).white(blocks2).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        int expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(0,1,0),l(2,0,4)).earlyExit().astar(l2).white(blocks1).build();
        d.fillLoop();
        double result = d.findCost().get();
        double expected = 15;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteInBlocks1() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(0,1,0),l(2,0,4)).earlyExit().astar(l2).white(blocks1).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(l(0,1,0), l(0,1,1), l(0,1,2), l(0,1,3), l(1,1,3), l(1,0,3), l(1,0,4), l(2,0,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(15,16,0),l(17,15,4)).earlyExit().astar(l2).white(blocks2).build();
        d.fillLoop();
        double result = d.findCost().get();
        double expected = 15;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteInBlocks2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(15,16,0),l(17,15,4)).earlyExit().astar(l2).white(blocks2).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(l(15,16,0), l(15,16,1), l(15,16,2), l(15,16,3), l(16,16,3), l(16,15,3), l(16,15,4), l(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1x2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph0,l(0,1,0),l(17,15,4)).earlyExit().astar(l2).white(blocks1.append(blocks2)).build();
        d.fillLoop();
        Optional<Double> result = d.findCost();
        Optional<Double> expected = Optional.empty();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1x2wBypass() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph1,l(0,1,0),l(17,15,4)).earlyExit().astar(l2).white(blocks1.append(blocks2)).build();
        d.fillLoop();
        double result = d.findCost().get();
        double expected = 36.21320343559643;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindRouteInBlocks1x2wBypass() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(layeredGraph1,l(0,1,0),l(17,15,4)).earlyExit().astar(l2).white(blocks1.append(blocks2)).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(l(0,1,0), l(1,1,0), l(1,1,1), l(1,1,2), l(1,1,3), l(16,16,3), l(17,16,3), l(17,15,3), l(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
