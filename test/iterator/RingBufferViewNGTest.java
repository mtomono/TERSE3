/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewlist;

import viewlist.View;
import static collection.c.a2l;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static test.TestUtils.methodNamePrint;
import static viewlist.Fixtures.*;

/**
 *
 * @author mtomono
 */
public class MView {
    
    public MView() {
    }

    /**
     * atメソッドのテスト、クラスViewのテスト。
     */
    @Test
    public void testAt() {
        System.out.println("at");
        View instance = new View(testList1, 2);
        assertEquals(instance.at(2), 2);
        assertEquals(instance.at(1), 1);
        assertEquals(instance.at(0), 0);
    }
    
    @Test
    public void testAt_O2() {
        System.out.println("at");
        View instance = new View(testList1, 2).offset(2);
        assertEquals(instance.at(2), 4);
        assertEquals(instance.at(1), 3);
        assertEquals(instance.at(0), 2);
    }
    
    @Test
    public void testAt_O5_R() {
        System.out.println("at");
        View instance = new View(testList1, 2).offset(5).reverse();
        assertEquals(instance.at(2), 3);
        assertEquals(instance.at(1), 4);
        assertEquals(instance.at(0), 5);
    }
    
    /**
     * getメソッドのテスト、クラスViewのテスト。
     */
    @Test
    public void testGet() {
        System.out.println("get");
        View instance = new View(testList1, 2);
        assertEquals(instance.get(2).get(), "test2");
        assertEquals(instance.get(1).get(), "test1");
        assertEquals(instance.get(0).get(), "test0");
    }
    
    @Test
    public void testGet_O2() {
        System.out.println("get");
        View instance = new View(testList1, 2).offset(2);
        assertEquals(instance.get(2).get(), "test4");
        assertEquals(instance.get(1).get(), "test3");
        assertEquals(instance.get(0).get(), "test2");
    }
    
    @Test
    public void testGet_O5_R() {
        System.out.println("get");
        View instance = new View(testList1, 2).offset(5).reverse();
        assertEquals(instance.get(2).get(), "test3");
        assertEquals(instance.get(1).get(), "test4");
        assertEquals(instance.get(0).get(), "test5");
    }

    /**
     * getメソッドのテスト、クラスViewのテスト。
     */
    @Test
    public void testIndex() {
        System.out.println("index");
        View instance = new View(testList1, 2);
        assertEquals(instance.index(2).get(0).get(), "test2");
        assertEquals(instance.index(1).get(0).get(), "test1");
        assertEquals(instance.index(0).get(0).get(), "test0");
    }
    
    @Test
    public void testIndex_O2() {
        System.out.println("index");
        View instance = new View(testList1, 2).offset(2);
        assertEquals(instance.index(2).get(0).get(), "test4");
        assertEquals(instance.index(1).get(0).get(), "test3");
        assertEquals(instance.index(0).get(0).get(), "test2");
    }
    
    @Test
    public void testIndex_O5_R() {
        System.out.println("index");
        View instance = new View(testList1, 2).offset(5).reverse();
        assertEquals(instance.index(2).get(3).get(), "test4");
        assertEquals(instance.index(1).get(3).get(), "test3");
        assertEquals(instance.index(0).get(3).get(), "test2");
    }
    
    
    @Test
    public void testPre() {
        methodNamePrint();
        List<Integer> tested = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        View<Integer> view = View.pre(tested, 5);
        assertEquals(view.get(0).get(), (Integer)4);
        assertEquals(view.get(1).get(), (Integer)3);
        assertEquals(view.get(2).get(), (Integer)2);
        assertEquals(view.get(3).get(), (Integer)1);
        assertEquals(view.get(4).get(), (Integer)0);
    }
    
    @Test
    public void testFore() {
        methodNamePrint();
        List<Integer> tested = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        View<Integer> view = View.fore(tested, 5);
        assertEquals(view.get(0).get(), (Integer)0);
        assertEquals(view.get(1).get(), (Integer)1);
        assertEquals(view.get(2).get(), (Integer)2);
        assertEquals(view.get(3).get(), (Integer)3);
        assertEquals(view.get(4).get(), (Integer)4);
    }

}
