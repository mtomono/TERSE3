/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static java.lang.Math.PI;
import javax.vecmath.Vector3d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class Angle3dNGTest {
    
    public Angle3dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testAngleBase() {
        testAngle();
    }

    @Test
    public void testAxis() {
        System.out.println(test.TestUtils.methodName(0));
        TVector3d result = new Angle3d(new TVector3d(1, 0, 0), new Vector3d(0, 1, 0)).axis();
        TVector3d expected = new TVector3d(0, 0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAngle() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Angle3d(new TVector3d(1, 0, 0), new Vector3d(0, 1, 0)).angle();
        double expected = PI/2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testComplement() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Angle3d(new TVector3d(1, 0, 0), new Vector3d(0, 1, 0)).complement();
        double expected = PI/2*3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
