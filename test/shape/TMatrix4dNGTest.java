/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.point3;
import static shape.ShapeUtil.vector3;

/**
 *
 * @author masao
 */
public class TMatrix4dNGTest {
    
    public TMatrix4dNGTest() {
    }

    @Test
    public void testInvertAffine() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix4d tested = TMatrix4d.affineTransform(TVector3d.y1,TVector3d.x1,TVector3d.z1,vector3(1,1,1)).invertAffine();
        TPoint3d result = tested.affine(point3(1,2,2));
        TPoint3d expected = point3(1,0,1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAffine() {
        System.out.println(test.TestUtils.methodName(0));
        TMatrix4d tested = TMatrix4d.affineTransform(TVector3d.y1,TVector3d.x1,TVector3d.z1,vector3(1,1,1));
        TPoint3d result = tested.affine(point3(1,0,1));
        TPoint3d expected = point3(1,2,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
