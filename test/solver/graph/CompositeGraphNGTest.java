/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
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
public class CompositeGraphNGTest {
    GridGraph grid;
    GeneralGraph<List<Integer>> bypass;
    TList<List<Integer>> blocks0;
    TList<List<Integer>> blocks1;
    TList<List<Integer>> blocks2;
    CompositeGraph<List<Integer>> compositeGraph0;
    CompositeGraph<List<Integer>> compositeGraph1;
    Metric<List<Integer>> l1=Metric.<Double>l2().morph(Metric.weight(pni(1,1,3)));
    public CompositeGraphNGTest() {
        grid = GridGraphBuilder.builder(0,0,0, 20,20,5).build();
        bypass = GeneralGraphBuilder.<List<Integer>>builder().a(pni(1,1,3), pni(16,16,3)).build();
        compositeGraph0 = new CompositeGraph<>(grid);
        compositeGraph1 = new CompositeGraph<>(grid,bypass);
        blocks0 = TList.sof(
                pni(0,0,0),pni(1,0,0),pni(2,0,0),pni(0,1,0),pni(1,1,0),pni(2,1,0),pni(0,2,0),pni(1,2,0),pni(2,2,0)
        );
        blocks1 = blocks0.map(p->TList.range(0,6).map(i->VectorOp.addI(p,pni(0,0,i)))).sfix().flatMap(l->l).sfix();
        blocks2 = blocks1.map(p->VectorOp.addI(p,pni(15,15,0)));
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(15,18,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph1),pni(15,16,0),pni(17,18,4)).earlyExit().astar(l1).white(blocks2).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(0,1,0),pni(2,0,4)).earlyExit().astar(l1).white(blocks1).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(0,1,0),pni(2,0,4)).earlyExit().astar(l1).white(blocks1).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(0,1,0), pni(0,1,1), pni(0,1,2), pni(0,1,3), pni(1,1,3), pni(1,0,3), pni(1,0,4), pni(2,0,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(15,16,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(15,16,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(15,16,0), pni(15,16,1), pni(15,16,2), pni(15,16,3), pni(16,16,3), pni(16,15,3), pni(16,15,4), pni(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1x2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph0),pni(0,1,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph1),pni(0,1,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
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
        NodeGraph<List<Integer>> d = NodeGraphBuilder.builder(new MetricizedGraph<>(l1,compositeGraph1),pni(0,1,0),pni(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
        d.fillLoop();
        TList<List<Integer>> result = d.findRoute();
        TList<List<Integer>> expected = TList.sof(pni(0,1,0), pni(1,1,0), pni(1,1,1), pni(1,1,2), pni(1,1,3), pni(16,16,3), pni(17,16,3), pni(17,15,3), pni(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
