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
import static shape.ShapeUtil.point3;
import static solver.graph.GridBuilder.basis;
import static solver.graph.GridBuilder.gmono;
import static solver.graph.GridBuilder.origin;

/**
 *
 * @author masao
 */
public class GridSolverNGTest {
    
    public GridSolverNGTest() {
    }

    @Test
    public void testMono() {
        System.out.println(test.TestUtils.methodName(0));
        GridMono gmono=gmono(basis(2,0,0, 0,2,0, 0,0,2),origin(0,0,0), 0,0,0, 3,3,3);
        TList<List<Integer>> result = new GridSolver(gmono,gmono.localMetric(GridMetric.l1(), 3), point3(2,2,0),point3(6,6,4)).earlyExit().astar().solve().route();
        TList<List<Integer>> expected = TList.sof(pni(1,1,0),pni(3,1,0),pni(3,1,2),pni(3,3,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testComposite() {
        System.out.println(test.TestUtils.methodName(0));
        GridComposite gcomp=GridBuilder.builder().gmonos(
            gmono(basis(2,0,0, 0,2,0, 0,0,2),origin(0,0,0), 0,0,0,0, 3,3,3,0),
            gmono(basis(-1,1,0, -1,-1,0, 0,0,1),origin(-3,-2,0), 0,0,0,1, 2,2,2,1)
        ).links(
             pni(0,1,0,0),pni(1,0,0,1),
             pni(0,0,0,1),pni(0,0,0,0)
        ).build();

        TList<List<Integer>> result = new GridSolver(gcomp,gcomp.globalMetric(GridMetric.l2(), 3),point3(2,2,0),point3(-6,-1,0)).earlyExit().astar().solve().route();
        TList<List<Integer>> expected = TList.sof(pni(1,1,0,0),pni(0,1,0,0),pni(1,0,0,1),pni(2,0,0,1),pni(2,1,0,1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAstar() {
    }

    @Test
    public void testNode() {
    }

    @Test
    public void testBlack() {
    }

    @Test
    public void testWhite() {
    }

    @Test
    public void testSolve() {
    }
    
}
