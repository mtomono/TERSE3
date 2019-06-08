/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shapeCollection.GridCoord.gcoord;

/**
 *
 * @author masao
 */
public class GridCoordFlippedNGTest {
    
    public GridCoordFlippedNGTest() {
    }

    @Test
    public void testSerialization() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = new GridCoordFlipped(gcoord(0,1,10, 9,10,1), 1, 0);
        int result = ge.index(5,3,4);
        int expected = 643;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAddress() {
        System.out.println(test.TestUtils.methodName(0));
        GridCoord ge = new GridCoordFlipped(gcoord(0,1,10, 9,10,1), 1, 0);
        TList<Integer> result = ge.address(643);
        TList<Integer> expected = TList.sof(5,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
