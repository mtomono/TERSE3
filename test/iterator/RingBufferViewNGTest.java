/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iterator;

import iterator.RingBufferView;
import static collection.c.a2l;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static test.TestUtils.methodNamePrint;

/**
 *
 * @author mtomono
 */
public class RingBufferViewNGTest {
    
    static public List<String> testList1 = Arrays.asList("test0", "test1", "test2", "test3", "test4", "test5");

    public RingBufferViewNGTest() {
    }

    /**
     * atメソッドのテスト、クラスViewのテスト。
     */
    @Test
    public void testAt() {
        System.out.println("at");
        RingBufferView instance = new RingBufferView(testList1, 2);
        assertEquals(instance.at(2), 2);
        assertEquals(instance.at(1), 1);
        assertEquals(instance.at(0), 0);
    }
    
    /**
     * getメソッドのテスト、クラスViewのテスト。
     */
    @Test
    public void testGet() {
        System.out.println("get");
        RingBufferView instance = new RingBufferView(testList1, 2);
        assertEquals(instance.get(2).get(), "test2");
        assertEquals(instance.get(1).get(), "test1");
        assertEquals(instance.get(0).get(), "test0");
    }
    
    @Test
    public void testPre() {
        methodNamePrint();
        List<Integer> tested = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        RingBufferView<Integer> view = RingBufferView.pre(tested, 5);
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
        RingBufferView<Integer> view = RingBufferView.fore(tested, 5);
        assertEquals(view.get(0).get(), (Integer)0);
        assertEquals(view.get(1).get(), (Integer)1);
        assertEquals(view.get(2).get(), (Integer)2);
        assertEquals(view.get(3).get(), (Integer)3);
        assertEquals(view.get(4).get(), (Integer)4);
    }

}
