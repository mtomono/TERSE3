/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.label;

import collection.P;
import static collection.label.Hierarchy.create;
import debug.Te;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class HierarchyNGTest {
    
    public HierarchyNGTest() {
    }

    @Test
    public void testPut() {
        System.out.println(test.TestUtils.methodName(0));
        Hierarchy<String> tested = create(0,(h,t)->Te.e(P.p(h,t)));
        tested.down()
              .put("_00000")
              .put("_00001")
              .put("_00002")
              .down()
              .put("_00003")
              .put("_00004")
              .down()
              .put("_00005")
              .put("_00006")
              .up()
              .put("_00007")
              .put("_00008")
              .put("_00009");
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
