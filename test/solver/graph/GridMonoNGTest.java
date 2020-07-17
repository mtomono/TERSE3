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
import org.testng.annotations.Test;
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;
import shape.TPoint3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 */
public class GridMonoNGTest {
    
    GridScale coord = new GridScale(TList.sof(vector3(2,0,0),vector3(0,2,0),vector3(0,0,2)),point3(2,2,2));
    GridCoord gcoord = GridCoord.gcoord(0,0,0, 3,3,3);
    GridGraph graph = GridGraphBuilder.builder(gcoord).alt().build();
    GridMono gmono = new GridMono(graph,coord);
    public GridMonoNGTest() {
    }

    @Test
    public void testCompensateHv() {
    }

    @Test
    public void testGlobalize() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3d result = gmono.globalizeI(TList.sof(1,1,1));
        TPoint3d expected = point3(4,4,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGlobalize_TList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint3d> result = gmono.globalizeI(TList.sof(TList.sof(1,1,1),TList.sof(1,0,0)));
        TList<TPoint3d> expected = TList.sof(point3(4,4,4),point3(4,2,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLocalize() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<List<Integer>> result = gmono.localize(point3(1.1,1.3,2.4));
        Optional<List<Integer>> expected = Optional.of(TList.sof(0,0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCube_TPoint3d_TPoint3d() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = gmono.toCube(point3(8.5,1.2,8.7),point3(1.2,8.5,1.2));
        TList<List<Integer>> expected = TList.sof(
                TList.sof(3,0,3), TList.sof(2,0,3), TList.sof(2,0,2), TList.sof(2,1,2), TList.sof(1,1,2), 
                TList.sof(1,1,1), TList.sof(1,2,1), TList.sof(0,2,1), TList.sof(0,2,0), TList.sof(0,3,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCube_fromOutside() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(gmono.toCubeUnabridged(point3(5.5,-0.5,2),point3(-0.5,5.5,2)));
        TList<List<Integer>> result = gmono.toCube(point3(5.5,-0.5,2),point3(-0.5,5.5,2));
        TList<List<Integer>> expected = TList.sof(
                TList.sof(1,0,0), TList.sof(0,0,0), TList.sof(0,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCube_fromOutside2() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(gmono.toCubeUnabridged(point3(5,0,2),point3(0,5,2)));
        TList<List<Integer>> result = gmono.toCube(point3(5,0,2),point3(0,5,2));
        TList<List<Integer>> expected = TList.sof(
                TList.sof(1,0,0), TList.sof(0,0,0), TList.sof(0,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBottom() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint3d> result = gmono.bottom(TList.sof(0,0,0), TList.sof(0,0,0));
        TList<TPoint3d> expected = TList.sof(point3(1,1,1),point3(3,1,1),point3(3,3,1),point3(1,3,1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTop() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint3d> result = gmono.top(TList.sof(0,0,0), TList.sof(0,0,0));
        TList<TPoint3d> expected = TList.sof(point3(1,1,3),point3(3,1,3),point3(3,3,3),point3(1,3,3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCircumference() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint3d> result = gmono.circumference();
        TList<TPoint3d> expected = TList.sof(point3(1,1,1),point3(9,1,1),point3(9,9,1),point3(1,9,1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
