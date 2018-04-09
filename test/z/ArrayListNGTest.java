/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import collection.TList;
import static collection.c.a2l;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ArrayListNGTest {
    
    public ArrayListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = a2l(0, 1, 2, 4);
        result.set(3, 3);
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testCloneAndSet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> sample = a2l(0, 1, 2, 4);
        List<Integer> result = a2l(sample.toArray(new Integer[]{0, 1}));
        result.set(3, 3);
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    @Test
    public void testArrayList() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        List<Integer> sample = a2l(0, 1, 2, 4);
        for (int i=0; i<1000000000; i++) {
            list = new ArrayList<>(sample);
            list.set(3, 3);
        }
    }
    
    @Test
    public void testArrayList2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        Integer[] sample = new Integer[]{0, 1, 2, 4};
        for (int i=0; i<1000000000; i++) {
            list = a2l(sample);
            list.set(3, 3);
        }
        System.out.println(sample[3]);
    }
    
    @Test
    public void testArrayList3() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        List<Integer> sample = a2l(0, 1, 2, 3);
        Integer[] array = new Integer[]{0, 1};
        for (int i=0; i<1000000000; i++) {
            list = a2l(sample.toArray(array));
            list.set(3, 3);
        }
    }
    
    @Test
    public void testArrayList4() {
        System.out.println(test.TestUtils.methodName(0));
        Integer[] list;
        Integer[] sample = new Integer[]{0, 1, 2, 4};
        Integer[] array = new Integer[]{0, 1};
        for (int i=0; i<1000000000; i++) {
            list = sample.clone();
            list[3]=3;
        }
    }
    
    @Test
    public void testArrayList41() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        Integer[] sample = new Integer[]{0, 1, 2, 4};
        Integer[] array = new Integer[]{0, 1};
        for (int i=0; i<1000000000; i++) {
            list = a2l(sample.clone());
            list.set(3, 3);
        }
    }
    
    @Test
    public void testArrayList5() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        Integer[] sample = new Integer[]{0, 1, 2, 4};
        for (int i=0; i<1000000000; i++) {
            list = new ArrList(sample);
            list.set(3, 3);
        }
    }
    
    @Test
    public void testArrayList6() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> list;
        List<Integer> sample = a2l(0, 1, 2, 4);
        for (int i=0; i<1000000000; i++) {
            list = new ArrList(sample);
            list.set(3, 3);
        }
    }

    @Test
    public void testArrayList7() {
        System.out.println(test.TestUtils.methodName(0));
        ArrList list;
        ArrList sample = new ArrList(0, 1, 2, 4);
        for (int i=0; i<1000000000; i++) {
            list = sample.clone();
            list.set(3, 3);
        }
    }

    @Test
    public void testArrayList8() {
        System.out.println(test.TestUtils.methodName(0));
        String list;
        String sample = "0124";
        for (int i=0; i<1000000000; i++) {
            list = sample + "l";
        }
    }

    @Test
    public void testArrayList9() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> list;
        Integer[] sample= new Integer[]{0, 1, 2, 4};
        for (int i=0; i<1000000000; i++) {
            list = TList.set(a2l(sample.clone()));
            list.set(3, 3);
        }
    }

    @Test
    public void testArrayList10() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> list;
        ArrList sample = new ArrList(new Integer[]{0, 1, 2, 4});
        for (int i=0; i<1000000000; i++) {
            list = TList.set(sample.clone());
            list.set(3, 3);
        }
    }

    static Integer[] array = new Integer[]{0};
    class ArrList extends AbstractList<Integer> {
        Integer[] body;
        
        public ArrList(Integer... i) {
            body = i.clone();
        }
        
        public ArrList(List<Integer> l) {
            body = l.toArray(array);
        }
        
        public ArrList clone() {
            return new ArrList(body);
        }
        
        @Override
        public Integer get(int index) {
            return body[index];
        }
        
        @Override
        public Integer set(int i, Integer o) {
            Integer retval = body[i];
            body[i] = o;
            return retval;
        }

        @Override
        public int size() {
            return body.length;
        }
        
    }
    
}
