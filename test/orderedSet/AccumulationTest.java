/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author mtomono
 */
public class AccumulationTest {
    
    public AccumulationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testOverlay() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.add(RangeSet.create(1, 12, 17, 30));
        acc.overlay(RangeSet.create(5, 10, 19, 25));
        assertEquals(RangeSet.create(1, 12, 17, 30), acc.get(0));
        assertEquals(RangeSet.create(5, 10, 19, 25), acc.get(1));
    }

    @Test
    public void testOverlay2() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.overlay(RangeSet.create(5, 10, 19, 25));
        acc.overlay(RangeSet.create(1, 12, 17, 30));
        assertEquals(RangeSet.create(1, 12, 17, 30), acc.get(0));
        assertEquals(RangeSet.create(5, 10, 19, 25), acc.get(1));
    }

    @Test
    public void testOverlay3() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.overlay(RangeSet.create(5, 10, 19, 25));
        acc.overlay(RangeSet.create(8, 23));
        assertEquals(RangeSet.create(5, 25), acc.get(0));
        assertEquals(RangeSet.create(8, 10, 19, 23), acc.get(1));
    }

    @Test
    public void testOverlay4() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.add(RangeSet.create(1, 12, 17, 30));
        acc.overlay(RangeSet.create(-1, 5, 8, 19, 25, 40));
        assertEquals(RangeSet.create(-1, 40), acc.get(0));
        assertEquals(RangeSet.create(1, 5, 8, 12, 17, 19, 25, 30), acc.get(1));
    }

    @Test
    public void testRemove() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.overlay(RangeSet.create(5, 10, 19, 25));
        acc.overlay(RangeSet.create(1, 12, 17, 30));
        acc.peel(RangeSet.create(5, 10));
        assertEquals(RangeSet.create(1, 12, 17, 30), acc.get(0));
        assertEquals(RangeSet.create(19, 25), acc.get(1));
    }

    @Test
    public void testRemove2() {
        Accumulation<Integer> acc = new Accumulation<>();
        acc.overlay(RangeSet.create(   5,    10,            19,  25));
        acc.overlay(RangeSet.create(1,           12,     17,        30));
        RangeSet<Integer> t = 
        acc.peel(RangeSet.create(  3, 7, 9,       15,       22,     40));
        assertEquals(RangeSet.create(1, 3, 5, 10, 17, 25), acc.get(0));
        assertEquals(RangeSet.create(7, 9, 19, 22), acc.get(1));
        assertEquals(RangeSet.create(12, 15, 30, 40), t);
    }
}
