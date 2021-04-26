/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import collection.TList;
import static collection.TList.toTList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class CullIteratorNGTest {
    
    public CullIteratorNGTest() {
    }

    @Test
    public void testSomeMethod() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.set(new CullIterator<>(TList.range(0,20).iterator(),6)).stream().collect(toTList());
        TList<Integer> expected = TList.sof(0,7,14);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
