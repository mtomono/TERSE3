/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static java.lang.Math.sqrt;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static shape.ShapeUtil.err;

/**
 *
 * @author masao
 */
public class TriangleNGTest {
    
    public TriangleNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @DataProvider(name="area")
    public Object[][] area() {
        return new Object[][]{
            {new RightTriangleBy2Sides(3,4), 6},
            {new TriangleBy3Sides(3,4,5), 6}
        };
    }
    @Test(dataProvider="area")
    public void testArea(Triangle origin, double area) {
        System.out.println(test.TestUtils.methodName(0));
        double result = origin.area();
        double expected = area;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name="circumference")
    public Object[][] circumference() {
        return new Object[][]{
            {new RightTriangleBy2Sides(3,4), 12},
            {new TriangleBy3Sides(3,4,5), 12}
        };
    }
    @Test(dataProvider="circumference")
    public void testCircumference(Triangle origin, double area) {
        System.out.println(test.TestUtils.methodName(0));
        double result = origin.circumference();
        double expected = area;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @DataProvider(name="inscribed")
    public Object[][] inscribed() {
        return new Object[][]{
            {new RightTriangleBy2Sides(2,2), sqrt(2)/(sqrt(2)+1)},
            {new TriangleBy3Sides(3,4,5), 1}
        };
    }
    @Test(dataProvider="inscribed")
    public void testInscribedCircle(Triangle origin, double area) {
        System.out.println(test.TestUtils.methodName(0));
        double result = origin.inscribedCircle();
        double expected = area;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected, err);
    }
    
}
