/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static java.lang.Math.PI;
import javax.vecmath.Vector2d;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class Angle2dNGTest {
    
    public Angle2dNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testAngle() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Angle2d(new TVector2d(1, 0), new Vector2d(0, 1)).angle();
        double expected = PI/2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAngleFromStart() {
        System.out.println(test.TestUtils.methodName(0));
        double result = new Angle2d(new TVector2d(1, 0), new Vector2d(0, 1)).angleFromStart(new Vector2d(0, 1));
        double expected = PI/2;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFallBetween() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new Angle2d(new TVector2d(1, 0), new Vector2d(0, 1)).fallBetween(new Vector2d(1, 1));
        boolean expected = true;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFallBetween1() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new Angle2d(new TVector2d(1, 0), new Vector2d(0, 1)).fallBetween(new Vector2d(-1, 1));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testFallBetween2() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = new Angle2d(new TVector2d(1, 0), new Vector2d(0, 1)).fallBetween(new Vector2d(1, -1));
        boolean expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
