/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static shape.ShapeUtil.pni;
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;
import shape.TPoint3d;
import shape.TVector3d;
import shapeCollection.GridCoord;
import static solver.graph.GridComposite.extractGraph;

/**
 *
 * @author masao
 */
public class GridCompositeNGTest {
    GridComposite grid;
    TList<GridMono> gmonos;
    
    static public TList<TVector3d> basis(double... v) {
        return TList.sof(vector3(v[0],v[1],v[2]),vector3(v[3],v[4],v[5]),vector3(v[6],v[7],v[8]));
    }
    static public TPoint3d origin(double... v) {
        return point3(v[0],v[1],v[2]);
    }
    static public GridMono gmono(TList<TVector3d> basis, TPoint3d origin, int... fromAndTo) {
        GridCore coord=new GridCore(basis,origin);
        GridCoord gcoord=GridCoord.gcoord(fromAndTo);
        GridGraph graph=GridGraphBuilder.builder(gcoord).alt().build();
        return new GridMono(graph,coord);
    }
    public GridCompositeNGTest() {
        gmonos= TList.sof(
            gmono(basis(2,0,0, 0,2,0, 0,0,2),origin(0,0,0), 0,0,0, 3,3,3),
            gmono(basis(-1,1,0, -1,-1,0, 0,0,1),origin(-3,-2,0), 0,0,0, 2,2,2)
        );
        Graph<List<Integer>> links = GeneralGraphBuilder.<List<Integer>>builder().build(
             pni(0,0,1,0),pni(1,1,0,0),
             pni(1,0,0,0),pni(0,0,0,0)
        );
        grid=new GridComposite(new CompositeGraph<>(extractGraph(gmonos).append(links)), gmonos);
    }

    @Test
    public void testLocalize() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<List<Integer>> result = grid.localize(point3(0.5,0.5,0.5));
        Optional<List<Integer>> expected = Optional.of(pni(0,0,0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @DataProvider(name="localize")
    public Object[][] glpair() {
        return new Object[][] {
            {point3(1.2,1.3,0),pni(0,1,1,0)},
            {point3(-3.5,-2,0),pni(1,0,0,0)},
            {point3(-4.5,-2,0),pni(1,1,1,0)}
        };
    }

    @Test(dataProvider="localize")
    public void testLocalize(TPoint3d g, List<Integer> l) {
        System.out.println(test.TestUtils.methodName(0));
        Optional<List<Integer>> result = grid.localize(g);
        Optional<List<Integer>> expected = Optional.of(l);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGlobalize() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3d result = grid.globalize(pni(1,2,1,0));
        TPoint3d expected = point3(-6,-1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCube() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = grid.toCube(point3(-10,-0.5,0),point3(10,-0.5,0));
        TList<List<Integer>> expected = TList.sof(pni(0,0,0,0),pni(0,1,0,0),pni(0,2,0,0),pni(0,3,0,0),pni(1,2,1,0),pni(1,2,0,0),pni(1,1,0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCompensateHv() {
    }
    
}
