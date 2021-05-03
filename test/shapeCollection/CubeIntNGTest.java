/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CubeIntNGTest {
    
    public CubeIntNGTest() {
    }

    @Test
    public void testMesh() {
        System.out.println(test.TestUtils.methodName(0));
        CubeInt mesh=new CubeInt(100,100,100);
        TList<Integer> result = mesh.mesh(TList.sof(1000,1000,1000));
        TList<Integer> expected = TList.sof(10,10,10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCenter() {
        System.out.println(test.TestUtils.methodName(0));
        CubeInt mesh=new CubeInt(100,100,100);
        TList<Integer> result = mesh.center(TList.sof(0,0,0));
        TList<Integer> expected = TList.sof(50,50,50);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
