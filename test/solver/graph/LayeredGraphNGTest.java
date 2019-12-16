/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import static java.lang.Math.sqrt;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p3i;
import shape.TPoint3i;

/**
 *
 * @author masao
 */
public class LayeredGraphNGTest {
    GridGraph<TPoint3i> grid;
    GeneralGraph<TPoint3i> bypass;
    TList<TPoint3i> blocks0;
    TList<TPoint3i> blocks1;
    TList<TPoint3i> blocks2;
    LayeredGraph<TPoint3i> layeredGraph0;
    LayeredGraph<TPoint3i> layeredGraph1;
    Metric<List<Integer>> l1=Metric.weighted(Metric.l2(), TList.sof(1,1,3));
    public LayeredGraphNGTest() {
        grid = GridGraph3dBuilder.builder(0,0,0, 20,20,5).build();
        bypass = GeneralGraphBuilder.<TPoint3i>builder().a(p3i(1,1,3), p3i(16,16,3), sqrt(15*15*2)).build();
        layeredGraph0 = new LayeredGraph<>(grid);
        layeredGraph1 = new LayeredGraph<>(grid,bypass);
        blocks0 = TList.sof(
                p3i(0,0,0),p3i(1,0,0),p3i(2,0,0),p3i(0,1,0),p3i(1,1,0),p3i(2,1,0),p3i(0,2,0),p3i(1,2,0),p3i(2,2,0)
        );
        blocks1 = blocks0.map(p->TList.range(0,6).map(i->p.addR(p3i(0,0,i)))).sfix().flatMap(l->l).sfix();
        blocks2 = blocks1.map(p->p.addR(p3i(15,15,0)));
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(15,18,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        int expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test(expectedExceptions=AssertionError.class)
    public void testToIsReacheable() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(15,16,0),p3i(17,18,4)).earlyExit().astar(l1).white(blocks2).build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        int expected = 0;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(0,1,0),p3i(2,0,4)).earlyExit().astar(l1).white(blocks1).build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(0,1,0),p3i(2,0,4)).earlyExit().astar(l1).white(blocks1).build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(0,1,0), p3i(0,1,1), p3i(0,1,2), p3i(0,1,3), p3i(1,1,3), p3i(1,0,3), p3i(1,0,4), p3i(2,0,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(15,16,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(15,16,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks2).build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(15,16,0), p3i(15,16,1), p3i(15,16,2), p3i(15,16,3), p3i(16,16,3), p3i(16,15,3), p3i(16,15,4), p3i(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFindCostInBlocks1x2() {
        System.out.println(test.TestUtils.methodName(0));
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph0,p3i(0,1,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph1,p3i(0,1,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
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
        NodeGraph<TPoint3i> d = NodeGraphBuilder.builder(layeredGraph1,p3i(0,1,0),p3i(17,15,4)).earlyExit().astar(l1).white(blocks1.append(blocks2)).build();
        d.fillLoop();
        TList<TPoint3i> result = d.findRoute();
        TList<TPoint3i> expected = TList.sof(p3i(0,1,0), p3i(1,1,0), p3i(1,1,1), p3i(1,1,2), p3i(1,1,3), p3i(16,16,3), p3i(17,16,3), p3i(17,15,3), p3i(17,15,4));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
