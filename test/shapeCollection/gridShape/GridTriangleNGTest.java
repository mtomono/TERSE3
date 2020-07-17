/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection.gridShape;

import collection.TList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import shape.TPoint2d;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.point2;
import static shape.TPoint3d.zero;
import static shape.TVector3d.x1;
import static shape.TVector3d.y1;
import static shape.TVector3d.z1;
import solver.graph.GridScale;

/**
 *
 * @author masao
 */
public class GridTriangleNGTest {
    
    public GridTriangleNGTest() {
    }
    
    GridScale coord = new GridScale(TList.sof(x1,y1,z1),zero);
    
    @Test
    public void testLongestEdgeFromX() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint2d> tested = TList.sof(point2(0,0),point2(3,0),point2(5,5),point2(0,0));
        int result = GTriangle.longestEdgeFromX(tested);
        int expected = 2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testBottom() {
        System.out.println(test.TestUtils.methodName(0));
        GTriangle tested = new GTriangle(TList.sof(point2(0,0),point2(3,0),point2(5,5)),coord);
        TList<TPoint2d> result = tested.bottom();
        TList<TPoint2d> expected = TList.sof(point2(5,5),point2(0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOthers() {
        System.out.println(test.TestUtils.methodName(0));
        GTriangle tested = new GTriangle(TList.sof(point2(0,0),point2(3,0),point2(5,5)),coord);
        TList<TPoint2d> result = tested.others();
        TList<TPoint2d> expected = TList.sof(point2(5,5),point2(3,0),point2(0,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFillingTiles() {
        System.out.println(test.TestUtils.methodName(0));
        GTriangle tested = new GTriangle(TList.sof(point2(0,0),point2(3,0),point2(5,5)),coord);
        TList<List<Integer>> result = tested.digitize();
        TList<List<Integer>> expected = TList.sof(p2i(5,4),p2i(5,5)
                ,p2i(4,1),p2i(4,2),p2i(4,3),p2i(4,4),p2i(4,5)
                ,p2i(3,0),p2i(3,1),p2i(3,2),p2i(3,3),p2i(3,4)
                ,p2i(2,0),p2i(2,1),p2i(2,2),p2i(2,3)
                ,p2i(1,0),p2i(1,1),p2i(1,2)
                ,p2i(0,0),p2i(0,1)).map(p->TList.set(p).sfix());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
