/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.label;

import collection.P;
import collection.TList;
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
        TList<P<TList<Integer>,String>> list=TList.c();
        Hierarchy<String> tested = create(0,(h,t)->list.add(Te.e(P.p(h.hierarchy,t))));
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
        TList<P<TList<Integer>,String>> result = list;
        TList<P<TList<Integer>,String>> expected = TList.sof(
                P.p(TList.sof(0),"_00000"),
                P.p(TList.sof(1),"_00001"),
                P.p(TList.sof(2),"_00002"),
                P.p(TList.sof(2,0),"_00003"),
                P.p(TList.sof(2,1),"_00004"),
                P.p(TList.sof(2,1,0),"_00005"),
                P.p(TList.sof(2,1,1),"_00006"),
                P.p(TList.sof(2,2),"_00007"),
                P.p(TList.sof(2,3),"_00008"),
                P.p(TList.sof(2,4),"_00009")
        );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
