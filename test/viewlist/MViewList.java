/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewlist;

import viewlist.ViewList;
import viewlist.View;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static viewlist.Fixtures.*;

/**
 *
 * @author mtomono
 */
public class MViewList {
    
    public MViewList() {
    }

    /**
     * getメソッドのテスト、クラスViewListのテスト。
     */
    @Test
    public void testGet_lower() {
        System.out.println("get");
        ViewList<String> instance = ViewList.natural(View.pre(testList1, 2));
        assertEquals(instance.get(0).get(0).get(), "test1");
        assertEquals(instance.get(0).get(1).get(), "test0");
        assertEquals(instance.get(1).get(0).get(), "test2");
        assertEquals(instance.get(1).get(1).get(), "test1");
        assertEquals(instance.get(1).get(2).get(), "test0");
    }

    @Test
    public void testGet_upper() {
        System.out.println("get");
        ViewList<String> instance = ViewList.natural(View.pre(testList1, 2));
        assertEquals(instance.get(5).get(1).get(), "test5");
        assertEquals(instance.get(5).get(2).get(), "test4");
        assertEquals(instance.get(4).get(0).get(), "test5");
        assertEquals(instance.get(4).get(1).get(), "test4");
        assertEquals(instance.get(4).get(2).get(), "test3");
    }

    @Test
    public void testGet_lowerNull() {
        System.out.println("get");
        ViewList<String> instance = ViewList.natural(View.pre(testList1, 2));
        assertFalse(instance.get(0).get(2).isPresent());
        assertFalse(instance.get(1).get(3).isPresent());
    }

    @Test
    public void testGet_upperNull() {
        System.out.println("get");
        ViewList<String> instance = ViewList.natural(View.pre(testList1, 2));
        assertFalse(instance.get(5).get(0).isPresent());
        assertFalse(instance.get(1).get(3).isPresent());
    }

    /**
     * sizeメソッドのテスト、クラスViewListのテスト。
     */
    @Test
    public void testSize_natural() {
        System.out.println("size");
        ViewList<String> instance = ViewList.natural(View.pre(testList1, 2));
        assertEquals(instance.size(), 6);
    }
    
    @Test
    public void testSize_outer() {
        System.out.println("size");
        ViewList<String> instance = ViewList.outer(View.pre(testList1, 2));
        assertEquals(instance.size(), 7);
    }
    
    @Test
    public void testSize_inner() {
        System.out.println("size");
        ViewList<String> instance = ViewList.inner(View.pre(testList1, 2));
        assertEquals(instance.size(), 5);
    }
    
}
