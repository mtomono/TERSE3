/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ConcatListNGTest {
    
    public ConcatListNGTest() {
    }
    
    static public <T> List<T> a2al(T... e) {
        return new ArrayList<>(a2l(e));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testGet1() {
        System.out.println("testGet1");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2), a2l(3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet2() {
        System.out.println("testGet2");
        List<Integer> result = new ConcatList<>(a2l(0), a2l(1, 2, 3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet3() {
        System.out.println("testGet3");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2, 3, 4), a2l(5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet4() {
        System.out.println("testGet4");
        List<Integer> result = new ConcatList<>(a2al(), a2l(0, 1, 2, 3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet5() {
        System.out.println("testGet5");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2, 3, 4, 5), a2al());
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet6() {
        System.out.println("testGet6");
        List<Integer> result = new ConcatList<>(a2al(), a2al(0));
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet7() {
        System.out.println("testGet7");
        List<Integer> result = new ConcatList<>(a2al(0), a2al());
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet8() {
        System.out.println("testGet8");
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1));
        List<Integer> expected = a2l(0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testGet9() {
        System.out.println("testGet8");
        List<Integer> result = new ConcatList<>(a2al(), a2al());
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove1N() {
        for (int i = 0; i < 6; i++) {
            testRemove1X(i);
        }
    }
    
    public void testRemove1X(int x) {
        System.out.println("testRemove1-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2), a2al(3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRemove2N() {
        for (int i = 0; i < 4; i++) {
            testRemove2X(i);
        }
    }
    
    public void testRemove2X(int x) {
        System.out.println("testRemove2-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1, 2, 3));
        List<Integer> expected = a2al(0, 1, 2, 3);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove3N() {
        for (int i = 0; i < 4; i++) {
            testRemove3X(i);
        }
    }
    
    public void testRemove3X(int x) {
        System.out.println("testRemove3-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2), a2al(3));
        List<Integer> expected = a2al(0, 1, 2, 3);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove4N() {
        for (int i = 0; i < 4; i++) {
            testRemove4X(i);
        }
    }
    
    public void testRemove4X(int x) {
        System.out.println("testRemove4-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0, 1, 2, 3));
        List<Integer> expected = a2al(0, 1, 2, 3);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove5N() {
        for (int i = 0; i < 4; i++) {
            testRemove5X(i);
        }
    }
    
    public void testRemove5X(int x) {
        System.out.println("testRemove5-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2, 3), a2al());
        List<Integer> expected = a2al(0, 1, 2, 3);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove6N() {
        for (int i = 0; i < 1; i++) {
            testRemove6X(i);
        }
    }
    
    public void testRemove6X(int x) {
        System.out.println("testRemove6-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0));
        List<Integer> expected = a2al(0);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove7N() {
        for (int i = 0; i < 1; i++) {
            testRemove7X(i);
        }
    }
    
    public void testRemove7X(int x) {
        System.out.println("testRemove7-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al());
        List<Integer> expected = a2al(0);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRemove8N() {
        for (int i = 0; i < 2; i++) {
            testRemove8X(i);
        }
    }
    
    public void testRemove8X(int x) {
        System.out.println("testRemove8-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1));
        List<Integer> expected = a2al(0, 1);
        result.remove(x);
        expected.remove(x);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSet1N() {
        for (int i = 0; i < 6; i++) {
            testSet1X(i);
        }
    }
    
    public void testSet1X(int x) {
        System.out.println("testSet1-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2), a2al(3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet2N() {
        for (int i = 0; i < 6; i++) {
            testSet2X(i);
        }
    }
    
    public void testSet2X(int x) {
        System.out.println("testSet2-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1, 2, 3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet3N() {
        for (int i = 0; i < 6; i++) {
            testSet3X(i);
        }
    }
    
    public void testSet3X(int x) {
        System.out.println("testSet3-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2, 3, 4), a2al(5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet4N() {
        for (int i = 0; i < 6; i++) {
            testSet4X(i);
        }
    }
    
    public void testSet4X(int x) {
        System.out.println("testSet4-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0, 1, 2, 3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet5N() {
        for (int i = 0; i < 6; i++) {
            testSet5X(i);
        }
    }
    
    public void testSet5X(int x) {
        System.out.println("testSet5-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2, 3, 4, 5), a2al());
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet6N() {
        for (int i = 0; i < 1; i++) {
            testSet6X(i);
        }
    }
    
    public void testSet6X(int x) {
        System.out.println("testSet6-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al());
        List<Integer> expected = a2al(0);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet7N() {
        for (int i = 0; i < 1; i++) {
            testSet7X(i);
        }
    }
    
    public void testSet7X(int x) {
        System.out.println("testSet7-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0));
        List<Integer> expected = a2al(0);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet8N() {
        for (int i = 0; i < 2; i++) {
            testSet8X(i);
        }
    }
    
    public void testSet8X(int x) {
        System.out.println("testSet8-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1));
        List<Integer> expected = a2al(0, 1);
        result.set(x, 9);
        expected.set(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd1N() {
        for (int i = 0; i < 7; i++) {
            testAdd1X(i);
        }
    }
    
    public void testAdd1X(int x) {
        System.out.println("testAdd1-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2), a2al(3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd2N() {
        for (int i = 0; i < 7; i++) {
            testAdd2X(i);
        }
    }
    
    public void testAdd2X(int x) {
        System.out.println("testAdd2-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1, 2, 3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd3N() {
        for (int i = 0; i < 7; i++) {
            testAdd3X(i);
        }
    }
    
    public void testAdd3X(int x) {
        System.out.println("testAdd3-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2, 3, 4), a2al(5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd4N() {
        for (int i = 0; i < 7; i++) {
            testAdd4X(i);
        }
    }
    
    public void testAdd4X(int x) {
        System.out.println("testAdd4-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0, 1, 2, 3, 4, 5));
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd5N() {
        for (int i = 0; i < 7; i++) {
            testAdd5X(i);
        }
    }
    
    public void testAdd5X(int x) {
        System.out.println("testAdd5-" + x);
        List<Integer> result = new ConcatList<>(a2al(0, 1, 2, 3, 4, 5), a2al());
        List<Integer> expected = a2al(0, 1, 2, 3, 4, 5);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd6N() {
        for (int i = 0; i < 2; i++) {
            testAdd6X(i);
        }
    }
    
    public void testAdd6X(int x) {
        System.out.println("testAdd6-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al());
        List<Integer> expected = a2al(0);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd7N() {
        for (int i = 0; i < 2; i++) {
            testAdd7X(i);
        }
    }
    
    public void testAdd7X(int x) {
        System.out.println("testAdd7-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al(0));
        List<Integer> expected = a2al(0);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd8N() {
        for (int i = 0; i < 3; i++) {
            testAdd8X(i);
        }
    }
    
    public void testAdd8X(int x) {
        System.out.println("testAdd8-" + x);
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1));
        List<Integer> expected = a2al(0, 1);
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAdd9N() {
        for (int i = 0; i < 1; i++) {
            testAdd9X(i);
        }
    }
    
    public void testAdd9X(int x) {
        System.out.println("testAdd9-" + x);
        List<Integer> result = new ConcatList<>(a2al(), a2al());
        List<Integer> expected = a2al();
        result.add(x, 9);
        expected.add(x, 9);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize1() {
        System.out.println("testSize1");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2), a2l(3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize2() {
        System.out.println("testSize2");
        List<Integer> result = new ConcatList<>(a2l(0), a2l(1, 2, 3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize3() {
        System.out.println("testSize3");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2, 3, 4), a2l(5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize4() {
        System.out.println("testSize4");
        List<Integer> result = new ConcatList<>(a2al(), a2l(0, 1, 2, 3, 4, 5));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize5() {
        System.out.println("testSize5");
        List<Integer> result = new ConcatList<>(a2l(0, 1, 2, 3, 4, 5), a2al());
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize6() {
        System.out.println("testSize6");
        List<Integer> result = new ConcatList<>(a2al(), a2al(0));
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize7() {
        System.out.println("testSize7");
        List<Integer> result = new ConcatList<>(a2al(0), a2al());
        List<Integer> expected = a2l(0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize8() {
        System.out.println("testSize8");
        List<Integer> result = new ConcatList<>(a2al(0), a2al(1));
        List<Integer> expected = a2l(0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
    @Test
    public void testSize9() {
        System.out.println("testSize8");
        List<Integer> result = new ConcatList<>(a2al(), a2al());
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result.size(), expected.size());
    }
    
}
