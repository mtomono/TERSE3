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
import static todo.ToDo.todo;

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
    public void testConstruct05() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(3.0, 1, 1.25, 3.75);
        System.out.println(sa);
        TList<Integer> result = TList.sof(sa.fromStep,sa.toStep);
        TList<Integer> expected = TList.sof(-1,1);
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
    public void testScale00() {
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
    public void testScale00negative() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.25, 1.25);
        System.out.println(sa.scale(-2.0/3.0));
        List<Double> result = TList.set(sa.scale(-2.0/3.0)).sfix();
        List<Double> expected = TList.sof(-0.75);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale01() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.5, 1.5);
        System.out.println(sa.scale(0.5));
        List<Double> result = TList.set(sa.scale(0.5)).sfix();
        List<Double> expected = TList.sof(-0.5,0.0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale01negative() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, -0.5, 1.5);
        System.out.println(sa.scale(-0.5));
        List<Double> result = TList.set(sa.scale(-0.5)).sfix();
        List<Double> expected = TList.sof(-0.5,-1.0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale02() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(sa.scale(0.5));
        List<Double> result = TList.set(sa.scale(0.5)).sfix();
        List<Double> expected = TList.sof(1.5,1.0,0.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale02negative() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(sa.scale(-0.5));
        List<Double> result = TList.set(sa.scale(-0.5)).sfix();
        List<Double> expected = TList.sof(1.5,2.0,2.5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testScale03() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(sa.scale(0.4));
        List<Double> result = TList.set(sa.scale(0.4)).sfix();
        List<Double> expected = TList.sof(1.5,1.1,0.7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testScale03sub() {
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(sa);
        System.out.println(sa.scaleCalc(-0.4));
        System.out.println(sa.scale(-0.4));
    }

    @Test
    public void testScale03negative() {
        todo("this is the problem i have to find a way out");
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(sa.scale(-0.4));
        System.out.println(sa);
        TList<Double> result = TList.set(sa.scale(-0.4)).sfix();
        TList<Double> expected = TList.sof(1.5,1.9,2.3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        result.pair(expected).forEach(p->assertEquals(p.l(),p.r(),err));
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
    public void testSpecificCase01() {
        System.out.println(test.TestUtils.methodName(0));
        ScaledAxis sa = new ScaledAxis(-0.5, 1, 1.5, -1);
        System.out.println(TList.set(sa).sfix());
        System.out.println(sa.scale(1.0/2.5));
        System.out.println(sa.scale(1.0/-2.5));
        System.out.println(TList.set(sa.scale(1.0/2.5)).sfix());
        ScaledAxis sa2 = new ScaledAxis(-0.5, 1, -1, 1.5);
        System.out.println(TList.set(sa2.fit(0,1)).sfix());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToString() {
    }
    
}
