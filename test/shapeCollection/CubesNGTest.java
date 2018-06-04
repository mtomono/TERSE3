/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static collection.c.a2l;
import static shapeCollection.Cubes.*;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.vector3;
import shape.TVector3d;

/**
 *
 * @author masao
 */
public class CubesNGTest {
    
    public CubesNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testToCubes() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(0.0,0.0,0.0);
        TVector3d to = vector3(3.0,3.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(0,0,0),a2l(1,0,0),a2l(1,1,0),a2l(2,1,0),a2l(2,2,0),a2l(3,2,0),a2l(3,3,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testToCubes2() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(0.0,0.0,0.0);
        TVector3d to = vector3(3.0,6.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(0,0,0),a2l(0,1,0),a2l(1,1,0),a2l(1,2,0),a2l(1,3,0),a2l(2,3,0),a2l(2,4,0),a2l(2,5,0),a2l(3,5,0),a2l(3,6,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testToCubes3() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(30.0,30.0,0.0);
        TVector3d to = vector3(33.0,36.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(30,30,0),a2l(30,31,0),a2l(31,31,0),a2l(31,32,0),a2l(31,33,0),a2l(32,33,0),a2l(32,34,0),a2l(32,35,0),a2l(33,35,0),a2l(33,36,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testToCubes4() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(-30.0,-30.0,0.0);
        TVector3d to = vector3(-33.0,-36.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(-30,-30,0),a2l(-30,-31,0),a2l(-31,-31,0),a2l(-31,-32,0),a2l(-31,-33,0),a2l(-32,-33,0),a2l(-32,-34,0),a2l(-32,-35,0),a2l(-33,-35,0),a2l(-33,-36,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testToCubes5() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(30.0,-30.0,0.0);
        TVector3d to = vector3(33.0,-36.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(30,-30,0),a2l(30,-31,0),a2l(31,-31,0),a2l(31,-32,0),a2l(31,-33,0),a2l(32,-33,0),a2l(32,-34,0),a2l(32,-35,0),a2l(33,-35,0),a2l(33,-36,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testToCubes6() {
        TVector3d zero = vector3(-0.5,-0.5,0.0);
        TVector3d factor = vector3(1.0, 1.0, 1.0);
        TVector3d from = vector3(30.0,3.0,0.0);
        TVector3d to = vector3(33.0,-3.0,0.0);
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = toCubes(zero, factor, from, to, 1.0);
        TList<List<Integer>> expected = TList.of(a2l(30,3,0),a2l(30,2,0),a2l(31,2,0),a2l(31,1,0),a2l(31,0,0),a2l(32,0,0),a2l(32,-1,0),a2l(32,-2,0),a2l(33,-2,0),a2l(33,-3,0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }    
}
