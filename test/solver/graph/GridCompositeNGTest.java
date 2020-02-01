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
import shape.TPoint3d;
import static solver.graph.GridBuilder.basis;
import static solver.graph.GridBuilder.gmono;
import static solver.graph.GridBuilder.origin;

/**
 *
 * @author masao
 */
public class GridCompositeNGTest {
    GridComposite grid;
        
    public GridCompositeNGTest() {
        grid=GridBuilder.builder().gmonos(
            gmono(basis(2,0,0, 0,2,0, 0,0,2),origin(0,0,0), 0,0,0,0, 3,3,3,0),
            gmono(basis(-1,1,0, -1,-1,0, 0,0,1),origin(-3,-2,0), 0,0,0,1, 2,2,2,1)
        ).links(
             pni(0,1,0,0),pni(1,0,0,1),
             pni(0,0,0,1),pni(0,0,0,0)
        ).build();
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
            {point3(1.2,1.3,0),pni(1,1,0,0)},
            {point3(-3.5,-2,0),pni(0,0,0,1)},
            {point3(-4.5,-2,0),pni(1,1,0,1)}
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
        TPoint3d result = grid.globalize(pni(2,1,0,1));
        TPoint3d expected = point3(-6,-1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCube() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = grid.toCube(point3(-10,-0.5,0),point3(10,-0.5,0));
        TList<List<Integer>> expected = TList.sof(pni(0,0,0,0),pni(1,0,0,0),pni(2,0,0,0),pni(3,0,0,0),pni(2,1,0,1),pni(2,0,0,1),pni(1,0,0,1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAll() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = grid.graph.all();
        System.out.println("result  : " + result);
        result.forEach(l->assertEquals(l.size(), 4));
    }

}
