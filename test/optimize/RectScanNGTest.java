/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimize;

import optimize.RectScan;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class RectScanNGTest {
    
    public RectScanNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testMark() {
        System.out.println(test.TestUtils.methodName(0));
        RectScan rect = new RectScan(p2i(-16, -13),p2i(16, 13));
        rect.mark(blocked);
        System.out.println(rect.rect);
    }
    
    @Test
    public void testRotate() {
        System.out.println(test.TestUtils.methodName(0));
        RectScan rect = new RectScan(p2i(-16, -13),p2i(16, 13));
        rect.mark(blocked);
        System.out.println(rect.rotate().toWrappedString());
    }

    @Test
    public void testCheck() {
        System.out.println(test.TestUtils.methodName(0));
        RectScan rect = new RectScan(p2i(-16, -13),p2i(16, 13));
        rect.mark(blocked);
        System.out.println(rect.check().map(i->new Character((char)('a'+i))));
    }
    

    final static Set<TPoint2i> blocked = new HashSet<TPoint2i>() {
        {
            add(new TPoint2i(-2, -3));
            add(new TPoint2i(-2, -1));
            add(new TPoint2i(-2, 0));
            add(new TPoint2i(-6, -3));
            add(new TPoint2i(-6, -2));
            add(new TPoint2i(-6, 0));
            add(new TPoint2i(-6, 1));
            add(new TPoint2i(-10, -1));
            add(new TPoint2i(-2, 8));
            add(new TPoint2i(-10, 0));
            add(new TPoint2i(-2, 9));
            add(new TPoint2i(-6, 5));
            add(new TPoint2i(-10, 1));
            add(new TPoint2i(-6, 6));
            add(new TPoint2i(-6, 7));
            add(new TPoint2i(-14, 1));
            add(new TPoint2i(-14, 2));
            add(new TPoint2i(3, -6));
            add(new TPoint2i(-1, -5));
            add(new TPoint2i(-1, -4));
            add(new TPoint2i(-1, -3));
            add(new TPoint2i(-1, -2));
            add(new TPoint2i(-5, 0));
            add(new TPoint2i(-5, 1));
            add(new TPoint2i(-5, 2));
            add(new TPoint2i(-9, -2));
            add(new TPoint2i(-9, -1));
            add(new TPoint2i(-9, 0));
            add(new TPoint2i(-5, 6));
            add(new TPoint2i(12, -9));
            add(new TPoint2i(-5, 7));
            add(new TPoint2i(-13, 2));
            add(new TPoint2i(8, -9));
            add(new TPoint2i(-5, 11));
            add(new TPoint2i(-5, 12));
            add(new TPoint2i(0, -6));
            add(new TPoint2i(0, -5));
            add(new TPoint2i(0, -4));
            add(new TPoint2i(0, -3));
            add(new TPoint2i(0, -2));
            add(new TPoint2i(-4, -4));
            add(new TPoint2i(-4, -3));
            add(new TPoint2i(-4, 0));
            add(new TPoint2i(-4, 1));
            add(new TPoint2i(-4, 2));
            add(new TPoint2i(-8, -2));
            add(new TPoint2i(-4, 3));
            add(new TPoint2i(-8, -1));
            add(new TPoint2i(-8, 0));
            add(new TPoint2i(-4, 6));
            add(new TPoint2i(-4, 7));
            add(new TPoint2i(-4, 8));
            add(new TPoint2i(-4, 9));
            add(new TPoint2i(-4, 10));
            add(new TPoint2i(9, -9));
            add(new TPoint2i(-4, 11));
            add(new TPoint2i(9, -8));
            add(new TPoint2i(1, -6));
            add(new TPoint2i(1, -5));
            add(new TPoint2i(1, -3));
            add(new TPoint2i(1, -2));
            add(new TPoint2i(1, -1));
            add(new TPoint2i(1, 0));
            add(new TPoint2i(-3, -4));
            add(new TPoint2i(-3, -3));
            add(new TPoint2i(-3, -1));
            add(new TPoint2i(-3, 0));
            add(new TPoint2i(-3, 4));
            add(new TPoint2i(-3, 8));
            add(new TPoint2i(-11, 0));
            add(new TPoint2i(-3, 9));
            add(new TPoint2i(-11, 1));
            add(new TPoint2i(-3, 10));
            add(new TPoint2i(-15, 1));
            add(new TPoint2i(2, -3));
            add(new TPoint2i(2, -2));
            add(new TPoint2i(2, -1));
        }
    };
}
