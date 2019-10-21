/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.p3i;

/**
 *
 * @author masao
 */
public class TPoint3iNGTest {
    
    public TPoint3iNGTest() {
    }
    
    @Test
    public void testEquals() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3i result = p3i(0,1,0);
        TPoint3i expected = p3i(0,1,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testNotEquals() {
        System.out.println(test.TestUtils.methodName(0));
        TPoint3i result = p3i(0,1,0);
        TPoint3i expected = p3i(0,2,0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertNotEquals(result, expected);
    }
    
    @Test
    public void testHash() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TPoint3i> tested = TList.sof(p3i(0,150,0),p3i(0,150,0),p3i(0,150,0),p3i(0,150,0),p3i(0,150,0),p3i(0,150,0),p3i(0,150,0),p3i(0,150,0));
        Set<TPoint3i> result = new HashSet<>(tested);
        System.out.println(tested.map(p->p.hashCode()));
        Set<TPoint3i> expected = new HashSet<>(TList.sof(p3i(0,150,0)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
