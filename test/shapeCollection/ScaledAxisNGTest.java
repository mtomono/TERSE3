/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.err;

/**
 *
 * @author masao
 */
public class ScaledAxisNGTest {
    
    public ScaledAxisNGTest() {
    }
    
    @Test
    public void testConstruct00() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 0.25, 1.25);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(1,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConstruct01() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.25, 1.25);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(1,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConstruct02() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.75, 1.25);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(0,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConstruct03() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -1.25, 1.25);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(0,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConstruct04() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.25, -1.25);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(1,-1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet00() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 0.25, 1.25);
        System.out.println(sa);
        List<Double> result = TList.set(sa).sfix();
        List<Double> expected = TList.sof(0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet01() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.25, 1.25);
        System.out.println(sa);
        List<Double> result = TList.set(sa).sfix();
        List<Double> expected = TList.sof(0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet03() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -1.25, 1.25);
        System.out.println(sa);
        List<Double> result = TList.set(sa).sfix();
        List<Double> expected = TList.sof(-0.5,0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGet04() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.25, -1.25);
        System.out.println(sa);
        List<Double> result = TList.set(sa).sfix();
        List<Double> expected = TList.sof(0.5,-0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStart00() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 0.25, 1.25);
        System.out.println(sa);
        int result = sa.start();
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStart04() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.25, -1.25);
        System.out.println(sa);
        int result = sa.start();
        int expected = 1;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDir() {
    }

    @Test
    public void testShift() {
    }

    @Test
    public void testScale() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.25, 1.25);
        System.out.println(sa.scale(2.0/3.0));
        List<Double> result = TList.set(sa.scale(2.0/3.0)).sfix();
        List<Double> expected = TList.sof(0.25);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFit00() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 0.25, 1.25);
        System.out.println(sa);
        List<Double> result = TList.set(sa.fit(0, 1)).sfix();
        List<Double> expected = TList.sof(0.25);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFit01() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.25, 1.25);
        System.out.println(sa.fit(0, 1));
        List<Double> result = TList.set(sa.fit(0, 1)).sfix();
        List<Double> expected = TList.sof(0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFit04() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.25, -1.25);
        System.out.println(sa.fit(0, 1));
        List<Double> result = TList.set(sa.fit(0, 1)).sfix();
        List<Double> expected = TList.sof(0.3,0.7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        TList.set(result).pair(expected).forEach(p->assertEquals(p.l(),p.r(),err));
    }

    @Test
    public void testToString() {
    }
    
}
