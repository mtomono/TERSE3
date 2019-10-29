/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.HashMap;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class MVOrderedMapNGTest {
    
    public MVOrderedMapNGTest() {
    }

    @Test
    public void testAdd() {
        System.out.println(test.TestUtils.methodName(0));
        MVOrderedMap<Integer,Integer> tested = MVOrderedMap.c(new HashMap<>());
        tested.add(500, 5);
        tested.add(500, 2);
        tested.add(500, 3);
        tested.add(500, 1);
        tested.add(500, 4);
        TList<Integer> result = tested.getList(500);
        TList<Integer> expected = TList.sof(1,2,3,4,5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
