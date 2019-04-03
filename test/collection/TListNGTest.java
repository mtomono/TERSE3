/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.TList.set;
import static collection.TList.toTList;
import static collection.c.a2l;
import debug.Print;
import static function.ComparePolicy.inc;
import function.Holder;
import iterator.TListIterator;
import java.util.*;
import orderedSet.Range;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static test.TestUtils.methodName;

/**
 *
 * @author masao
 */
public class TListNGTest {
    
    public TListNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    static <T> Optional<T> o(T t) {
        return Optional.of(t);
    }
    
    static <T> Optional<T> e() {
        return Optional.empty();
    }

    @Test
    public void testWrap() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.wrap(1);
        List<Integer> expected = a2l(1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testC() {
        // no test
    }

        @Test
    public void testEmpty() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.empty();
        List<Integer> expected = Collections.emptyList();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testEquals() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2, 3, 4);
        TList<Integer> expected = TList.of(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEquals2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.of(TList.of(0, 1, 2, 3, 4), TList.of(5, 6, 7));
        TList<TList<Integer>> expected = TList.of(TList.of(0, 1, 2, 3, 4), TList.of(5, 6, 7));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSet() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.set(a2l(0, 1, 2, 3));
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOf() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3);
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOf_GenericType() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(3, 2, 1, 0);
        List<Integer> expected = a2l(3, 2, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRange() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.range(3, 5);
        List<Integer> expected = a2l(3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLast_0args() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.range(0, 5).last();
        Integer expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLast_int() {
        System.out.println(test.TestUtils.methodName(0));
        Integer result = TList.range(0, 5).last(1);
        Integer expected = 3;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFix() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4, 5, 6).filter(i->i%3!=0).fix();
        List<Integer> expected = a2l(1, 2, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFix2seq() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4, 5, 6).filter(i->i%3!=0).fix2seq();
        List<Integer> expected = a2l(1, 2, 4, 5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTee() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).tee(Print.ln());
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAddOne() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.<Integer>c().addOne(1);
        TList<Integer> expected = TList.sof(1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

//----------- Transforming --------------------
    
    @Test
    public void testTransform() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TList.of(0, 1, 2, 3).transform(l->l.size());
        int expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap_Function_Function() {
        System.out.println(test.TestUtils.methodName(0));
        List<String> result = TList.of(0, 1, 2, 3, 4).map(i->Integer.toString(i), s->Integer.parseInt(s));
        List<String> expected = a2l("0", "1", "2", "3", "4");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        result.add("5");
        List<String> expected2 = a2l("0", "1", "2", "3", "4", "5");
        System.out.println("result  : " + result);
        System.out.println("expected1: " + expected);
        assertEquals(result, expected2);
    }

    @Test
    public void testMap_Function() {
        System.out.println(test.TestUtils.methodName(0));
        List<String> result = TList.of(0, 1, 2, 3, 4).map(i->Integer.toString(i));
        List<String> expected = a2l("0", "1", "2", "3", "4");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFlatMap() {
        System.out.println(test.TestUtils.methodName(0));
        List<String> result = TList.of("0,1,2,3", "4,5,6", "7,8").flatMap(s->a2l(s.split(",")));
        List<String> expected = a2l("0", "1", "2", "3", "4", "5", "6", "7", "8");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTranspose() {
        System.out.println(test.TestUtils.methodName(0));
        List<List<Integer>> result = TList.of(a2l(0, 1, 2, 3), a2l(4, 5, 6), a2l(7)).transpose(l->l);
        List<List<Integer>> expected = a2l(a2l(0, 4, 7), a2l(1, 5), a2l(2, 6), a2l(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccum() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(1, 2, 4, 8).accum(0, (a,b)->a+b);
        List<Integer> expected = a2l(0, 1, 3, 7, 15);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
//------------ Filtering
    @Test
    public void testFilter() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4, 5).filter(i->i%3==0);
        List<Integer> expected = a2l(0, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToMask_int_List() {
        System.out.println(test.TestUtils.methodName(0));
        BitSet result = TList.toMask(5, a2l(2, 4));
        BitSet expected = new BitSet();
        expected.set(2);
        expected.set(4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToMask_int_intArr() {
        System.out.println(test.TestUtils.methodName(0));
        BitSet result = TList.toMask(5, new int[]{2, 4});
        BitSet expected = new BitSet();
        expected.set(2);
        expected.set(4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShow_List() {
        System.out.println(test.TestUtils.methodName(0));
        List<Character> result = TList.of('a', 'b', 'c', 'd').show(a2l(1, 3));
        List<Character> expected = a2l('b', 'd');
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHide_List() {
        System.out.println(test.TestUtils.methodName(0));
        List<Character> result = TList.of('a', 'b', 'c', 'd').hide(a2l(0, 2));
        List<Character> expected = a2l('b', 'd');
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMask() {
        System.out.println(test.TestUtils.methodName(0));
        BitSet tested = new BitSet();
        tested.set(0);tested.set(2);
        TList<Integer> result = TList.of(0, 1, 2, 3).mask(tested);
        List<Integer> expected = a2l(0, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPickUp() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4).pickUp(a2l(3, 1));
        List<Integer> expected = a2l(3, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistinctLocally_0args() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 2, 2, 3, 4).distinctLocally();
        List<Integer> expected = a2l(0, 1, 2, 3, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistinctLocally_0args2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(1, 1, 1, 1, 1, 1).distinctLocally();
        List<Integer> expected = TList.empty();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDistinctLocally_Comparator() {
        System.out.println(test.TestUtils.methodName(0));
        List<String> result = TList.of("a", "bo", "and", "bad", "dad", "exit").distinctLocally((a,b)->a.length()-b.length());
        List<String> expected = a2l("a", "bo", "and", "exit");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
//----------------- Ordering
    
    @Test
    public void testSortTo() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(1, 0, 2, 4).sortTo((a,b)->a-b);
        List<Integer> expected = a2l(0, 1, 2, 4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testReverse() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4).reverse();
        List<Integer> expected = a2l(4, 3, 2, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSeek() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).seek(2);
        List<Integer> expected = a2l(2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeek2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).seek(-2);
        List<Integer> expected = a2l(0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeek3() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).seek(0);
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeek4() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).seek(4);
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSeek5() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).seek(-4);
        List<Integer> expected = a2l();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRepeat_int() {
        System.out.println(test.TestUtils.methodName(0));
        TListIterator<Integer> i = TList.of(0, 1, 2, 3, 4).repeat(3);
        List<Integer> result = a2l(i.next(), i.next(), i.next(), i.next(), i.next(), i.next());
        List<Integer> expected = a2l(3, 4, 0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRepeat_0args() {
        System.out.println(test.TestUtils.methodName(0));
        TListIterator<Integer> i = TList.of(0, 1, 2, 3, 4).repeat();
        List<Integer> result = a2l(i.next(), i.next(), i.next(), i.next(), i.next(), i.next());
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRotate() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4).rotate(2);
        List<Integer> expected = a2l(2, 3, 4, 0, 1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testRotate2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3, 4).rotate(-2);
        List<Integer> expected = a2l(3, 4, 0, 1, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testSkip() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(0,10).skip(4);
        TList<Integer> expected = TList.sof(0,4,8);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDivide() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(1, 3, 7, 9, 11, 2, 3, 8, 9, 11, 9).divide(11);
        TList<TList<Integer>> expected = TList.sof(TList.sof(1, 3, 7, 9, 11), TList.sof(2, 3, 8, 9, 11, 9));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(1, 3, 7, 9, 11, 2, 3, 8, 9, 11, 9, 9, 12).chunk(i->i>10);
        TList<TList<Integer>> expected = TList.sof(TList.sof(1, 3, 7, 9, 11), TList.sof(2, 3, 8, 9, 11), TList.sof(9, 9, 12), TList.empty());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(1, 3, 7, 9, 11, 2, 3, 8, 9, 11, 9, 9, 12, 3, 2).chunk(i->i>10);
        TList<TList<Integer>> expected = TList.sof(TList.sof(1, 3, 7, 9, 11), TList.sof(2, 3, 8, 9, 11), TList.sof(9, 9, 12), TList.of(3, 2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testChunk3() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(11, 1, 3, 7, 9, 11, 2, 3, 8, 9, 11, 9, 9, 12, 3, 2, 11).chunk(i->i>10);
        TList<TList<Integer>> expected = TList.sof(TList.sof(11),TList.sof(1, 3, 7, 9, 11), TList.sof(2, 3, 8, 9, 11), TList.sof(9, 9, 12), TList.sof(3, 2, 11), TList.sof());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
//-----------------Composing
    
    @Test
    public void testAppend_ListArr() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).append(a2l(4, 5, 6), a2l(7, 8));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAppendLists() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).appendLists(a2l(a2l(4, 5, 6), a2l(7, 8)));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConcat_ListArr() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.concat(a2l(0, 1, 2), a2l(3, 4, 5), a2l(6, 7));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testConcat_List() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.concat(a2l(a2l(0, 1, 2), a2l(3, 4, 5), a2l(6, 7)));
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCross_List() {
        System.out.println(methodName(0));
        List<P<Integer, Integer>> result = set(a2l(0, 2)).cross(a2l(1, 3));
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(0, 3), P.p(2, 1), P.p(2, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

        @Test
    public void testCross_List_BiFunction() {
        System.out.println(methodName(0));
        List<P<Integer, Integer>> result = set(a2l(0, 2)).cross(a2l(1, 3), (a,b)->P.p(a,b));
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(0, 3), P.p(2, 1), P.p(2, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testWeave() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(a2l(0, 3, 6, 9), a2l(1, 4, 7, 10), a2l(2, 5, 8)).weave(l->l);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDelimit() {
        System.out.println(test.TestUtils.methodName(0));
        List<String> result = TList.sof(0,1,3,4,2,5).delimit(i->Integer.toString(i), (a,b)->a<b?"<":">");
        List<String> expected = TList.sof("0","<","1","<","3","<","4",">","2","<","5");
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMerge() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 3, 4, 6, 10).merge(a2l(1, 2, 5, 7, 8, 9), (a,b)->a-b);
        List<Integer> expected = a2l(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMerge2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 3, 4, 6, 10).merge(a2l(1, 2, 5, 7, 8, 9), (a,b)->a-b).reverse();
        List<Integer> expected = a2l(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair() {
        System.out.println(test.TestUtils.methodName(0));
        List<P<Integer, Integer>> result = TList.of(0, 1, 2, 3, 4).pair(a2l(3, 4, 5, 6));
        List<P<Integer, Integer>> expected = a2l(P.p(0, 3), P.p(1, 4), P.p(2, 5), P.p(3, 6));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair_List() {
        System.out.println(test.TestUtils.methodName(0));
        List<P<Integer, Integer>>result = TList.of(0, 1, 2, 3, 4).pair(a2l(1, 2, 3, 4, 5));
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(1, 2), P.p(2, 3), P.p(3, 4), P.p(4, 5));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPair_List_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer>result = TList.of(0, 1, 2, 3, 4).pair(a2l(1, 2, 3, 4, 5), (a,b)->a-b);
        List<Integer> expected = a2l(-1, -1, -1, -1, -1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiff_0args() {
        System.out.println(test.TestUtils.methodName(0));
        List<P<Integer, Integer>> result = TList.of(0, 1, 2, 3).diff();
        List<P<Integer, Integer>> expected = a2l(P.p(0, 1), P.p(1, 2), P.p(2, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiff_int() {
        System.out.println(test.TestUtils.methodName(0));
        List<P<Integer, Integer>> result = TList.of(0, 1, 2, 3).diff(2);
        List<P<Integer, Integer>> expected = a2l(P.p(0, 2), P.p(1, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiff_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).diff((a,b)->a-b);
        List<Integer> expected = a2l(-1, -1, -1);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiff_int_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.of(0, 1, 2, 3).diff(2, (a,b)->a-b);
        List<Integer> expected = a2l(-2, -2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDediff() {
        System.out.println(test.TestUtils.methodName(0));
        List<Integer> result = TList.<Integer>dediff().apply(TList.of(0, 1, 2, 3).diff());
        List<Integer> expected = a2l(0, 1, 2, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffn() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = TList.sof(0,1,2,3,4).diffn(3);
        Integer[][] expected0 = {{0,1,2},{1,2,3},{2,3,4}};
        TList<List<Integer>> expected = TList.sof(expected0).map(aa->TList.sof(aa));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDiffnn_IntegerArr() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = TList.sof(0,1,2,3,4).diffnn(0,2,1);
        Integer[][] expected0 = {{0,2,1},{1,3,2},{2,4,3}};
        TList<List<Integer>> expected = TList.sof(expected0).map(aa->TList.sof(aa));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

        @Test
    public void testDiffnn_TList() {
        // this is already tested when testDiffnn_IntegerArr is tested.
    }

//------------------- Positioning
    
    @Test
    public void testOptionalMap() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2, 3).optionalMap(i->Optional.of(i));
        List<Optional<Integer>> expected = a2l(Optional.of(0), Optional.of(1), Optional.of(2), Optional.of(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOptionalFlatMap() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(TList.of(0, 1, 2), TList.of(3, 4, 5)).optionalFlatMap(l->l.optional());
        List<Optional<Integer>> expected = a2l(Optional.of(0), Optional.of(1), Optional.of(2), Optional.of(3), Optional.of(4), Optional.of(5));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShow_Predicate() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2, 3).show(i->i==2);
        List<Optional<Integer>> expected = a2l(Optional.empty(), Optional.empty(), Optional.of(2), Optional.empty());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHide_Predicate() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2, 3).hide(i->i!=2);
        List<Optional<Integer>> expected = a2l(Optional.empty(), Optional.empty(), Optional.of(2), Optional.empty());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testOptional() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2, 3).optional();
        List<Optional<Integer>> expected = a2l(Optional.of(0), Optional.of(1), Optional.of(2), Optional.of(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFill() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2, 3).fill(2, 3);
        List<Optional<Integer>> expected = a2l(Optional.of(0), Optional.of(1), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(2), Optional.of(3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShift() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2).shift(2);
        List<Optional<Integer>> expected = a2l(Optional.empty(), Optional.empty(), Optional.of(0), Optional.of(1), Optional.of(2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShift2() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = TList.of(0, 1, 2).shift(-1);
        List<Optional<Integer>> expected = a2l(Optional.of(1), Optional.of(2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testRectangle() {
        System.out.println(test.TestUtils.methodName(0));
        List<List<Optional<Integer>>> result = TList.of(a2l(0, 1), a2l(2, 3, 4, 5), a2l(6, 7)).rectangle(l->l);
        List<List<Optional<Integer>>> expected = a2l(a2l(o(0), o(1), e(), e()), 
                                                     a2l(o(2), o(3), o(4), o(5)),
                                                     a2l(o(6), o(7), e(), e()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSquare() {
        System.out.println(test.TestUtils.methodName(0));
        List<List<Optional<Integer>>> result = TList.of(a2l(0, 1), a2l(2, 3, 4, 5), a2l(6, 7)).square(l->l);
        List<List<Optional<Integer>>> expected = a2l(a2l(o(0), o(1), e(), e()), 
                                                     a2l(o(2), o(3), o(4), o(5)),
                                                     a2l(o(6), o(7), e(), e()), 
                                                     a2l(e(), e(), e(), e()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSquare2() {
        System.out.println(test.TestUtils.methodName(0));
        List<List<Optional<Integer>>> result = TList.of(a2l(0, 1), a2l(2, 3), a2l(6, 7)).square(l->l);
        List<List<Optional<Integer>>> expected = a2l(a2l(o(0), o(1), e()), 
                                                     a2l(o(2), o(3), e()),
                                                     a2l(o(6), o(7), e())
                                                 );
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMaxLength() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TList.of("0,1,2", "3,4,5,6", "7,8").maxLength(s->a2l(s.split(",")));
        int expected = 4;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

//----------------- Combination theory

    @Test
    public void testPermutation_int_int() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.permutation(5, 2);
        List<List<Integer>> expected = a2l(a2l(0, 1), a2l(0, 2), a2l(0, 3), a2l(0, 4), 
                                           a2l(1, 0), a2l(1, 2), a2l(1, 3), a2l(1, 4), 
                                           a2l(2, 0), a2l(2, 1), a2l(2, 3), a2l(2, 4), 
                                           a2l(3, 0), a2l(3, 1), a2l(3, 2), a2l(3, 4), 
                                           a2l(4, 0), a2l(4, 1), a2l(4, 2), a2l(4, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPermutation_int() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.range(0, 5).permutation(2);
        List<List<Integer>> expected = a2l(a2l(0, 1), a2l(0, 2), a2l(0, 3), a2l(0, 4), 
                                           a2l(1, 0), a2l(1, 2), a2l(1, 3), a2l(1, 4), 
                                           a2l(2, 0), a2l(2, 1), a2l(2, 3), a2l(2, 4), 
                                           a2l(3, 0), a2l(3, 1), a2l(3, 2), a2l(3, 4), 
                                           a2l(4, 0), a2l(4, 1), a2l(4, 2), a2l(4, 3));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCombination_int_int1() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.combination(5, 1);
        List<List<Integer>> expected = a2l(a2l(0), a2l(1), a2l(2), a2l(3), a2l(4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testtestCombination_int_int2() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.combination(5, 2);
        List<List<Integer>> expected = a2l(a2l(0, 1), a2l(0, 2), a2l(0, 3), a2l(0, 4), 
                                       a2l(1, 2), a2l(1, 3), a2l(1, 4), 
                                       a2l(2, 3), a2l(2, 4), 
                                       a2l(3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testCombination_int_int() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.combination(5, 3);
        List<List<Integer>> expected = a2l(a2l(0, 1, 2), a2l(0, 1, 3), a2l(0, 1, 4),
                                           a2l(0, 2, 3), a2l(0, 2, 4), 
                                           a2l(0, 3, 4), 
                                           a2l(1, 2, 3), a2l(1, 2, 4),
                                           a2l(1, 3, 4), 
                                           a2l(2, 3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCombination_int() {
        System.out.println(methodName(0));
        List<List<Integer>> result = TList.range(0, 5).combination(3);
        List<List<Integer>> expected = a2l(a2l(0, 1, 2), a2l(0, 1, 3), a2l(0, 1, 4),
                                           a2l(0, 2, 3), a2l(0, 2, 4), 
                                           a2l(0, 3, 4), 
                                           a2l(1, 2, 3), a2l(1, 2, 4),
                                           a2l(1, 3, 4), 
                                           a2l(2, 3, 4));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPermutationUpTo() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<List<Integer>>> result = TList.of(0, 1, 2, 3).permutationUpTo(2);
        List<List<List<Integer>>> expected = a2l(a2l(), 
                                                 a2l(a2l(0), a2l(1), a2l(2), a2l(3)),
                                                 a2l(a2l(0, 1), a2l(0, 2), a2l(0, 3), 
                                                     a2l(1, 0), a2l(1, 2), a2l(1, 3),
                                                     a2l(2, 0), a2l(2, 1), a2l(2, 3), 
                                                     a2l(3, 0), a2l(3, 1), a2l(3, 2)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testCombinationUpTo() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<List<Integer>>> result = TList.of(0, 1, 2, 3).combinationUpTo(2);
        List<List<List<Integer>>> expected = a2l(a2l(), 
                                                 a2l(a2l(0), a2l(1), a2l(2), a2l(3)),
                                                 a2l(a2l(0, 1), a2l(0, 2), a2l(0, 3), 
                                                     a2l(1, 2), a2l(1, 3),
                                                     a2l(2, 3)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPaint() {
        System.out.println(methodName(0));
        List<Integer> result = TList.paint(5, a2l(2, 3));
        List<Integer> expected = a2l(0, 0, 2, 2, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPaint2() {
        System.out.println(methodName(0));
        List<Integer> result = TList.paint(5, a2l(1, 3));
        List<Integer> expected = a2l(0, 1, 2, 2, 2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPaint3() {
        System.out.println(methodName(0));
        List<Integer> result = TList.paint(10, a2l(1, 3, 9));
        List<Integer> expected = a2l(0, 1, 2, 2, 2, 2, 2, 3, 3, 3);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHomogeneousProduct_int_int() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = TList.homogeneousProduct(4, 4);
        List<List<Integer>> expected = a2l(a2l(3, 3, 3, 3), 
                                           a2l(2, 3, 3, 3), 
                                           a2l(2, 2, 3, 3), 
                                           a2l(2, 2, 2, 3), 
                                           a2l(2, 2, 2, 2),
                                           a2l(1, 3, 3, 3), 
                                           a2l(1, 2, 3, 3), 
                                           a2l(1, 2, 2, 3), 
                                           a2l(1, 2, 2, 2),
                                           a2l(1, 1, 3, 3),
                                           a2l(1, 1, 2, 3), 
                                           a2l(1, 1, 2, 2),
                                           a2l(1, 1, 1, 3),
                                           a2l(1, 1, 1, 2), 
                                           a2l(1, 1, 1, 1),
                                           a2l(0, 3, 3, 3), 
                                           a2l(0, 2, 3, 3), 
                                           a2l(0, 2, 2, 3),
                                           a2l(0, 2, 2, 2),
                                           a2l(0, 1, 3, 3), 
                                           a2l(0, 1, 2, 3),
                                           a2l(0, 1, 2, 2), 
                                           a2l(0, 1, 1, 3), 
                                           a2l(0, 1, 1, 2),
                                           a2l(0, 1, 1, 1),
                                           a2l(0, 0, 3, 3), 
                                           a2l(0, 0, 2, 3),
                                           a2l(0, 0, 2, 2),
                                           a2l(0, 0, 1, 3), 
                                           a2l(0, 0, 1, 2), 
                                           a2l(0, 0, 1, 1), 
                                           a2l(0, 0, 0, 3),
                                           a2l(0, 0, 0, 2),
                                           a2l(0, 0, 0, 1),
                                           a2l(0, 0, 0, 0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testHomogeneousProduct_int() {
        System.out.println(test.TestUtils.methodName(0));
        TList<List<Integer>> result = TList.of(0, 1, 2, 3).homogeneousProduct(4);
        List<List<Integer>> expected = a2l(a2l(3, 3, 3, 3), 
                                           a2l(2, 3, 3, 3), 
                                           a2l(2, 2, 3, 3), 
                                           a2l(2, 2, 2, 3), 
                                           a2l(2, 2, 2, 2),
                                           a2l(1, 3, 3, 3), 
                                           a2l(1, 2, 3, 3), 
                                           a2l(1, 2, 2, 3), 
                                           a2l(1, 2, 2, 2),
                                           a2l(1, 1, 3, 3),
                                           a2l(1, 1, 2, 3), 
                                           a2l(1, 1, 2, 2),
                                           a2l(1, 1, 1, 3),
                                           a2l(1, 1, 1, 2), 
                                           a2l(1, 1, 1, 1),
                                           a2l(0, 3, 3, 3), 
                                           a2l(0, 2, 3, 3), 
                                           a2l(0, 2, 2, 3),
                                           a2l(0, 2, 2, 2),
                                           a2l(0, 1, 3, 3), 
                                           a2l(0, 1, 2, 3),
                                           a2l(0, 1, 2, 2), 
                                           a2l(0, 1, 1, 3), 
                                           a2l(0, 1, 1, 2),
                                           a2l(0, 1, 1, 1),
                                           a2l(0, 0, 3, 3), 
                                           a2l(0, 0, 2, 3),
                                           a2l(0, 0, 2, 2),
                                           a2l(0, 0, 1, 3), 
                                           a2l(0, 0, 1, 2), 
                                           a2l(0, 0, 1, 1), 
                                           a2l(0, 0, 0, 3),
                                           a2l(0, 0, 0, 2),
                                           a2l(0, 0, 0, 1),
                                           a2l(0, 0, 0, 0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
//----------------- simple wrapping
    
    @Test
    public void testListIterator_0args() {
        //no test
    }

    @Test
    public void testListIterator_int() {
        //no test
    }

    @Test
    public void testSubList() {
        //no test
    }

    @Test
    public void testOfStatic() {
        //no test
    }

    @Test
    public void testAccept() {
        System.out.println(test.TestUtils.methodName(0));
        Holder<String> h = new Holder<>("str");
        TList.of(0, 1, 2).accept(l->h.set(l.toString()));
        String result = h.get();
        String expected = "T[0, 1, 2]";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEqualityForListSubclass() {
    }

    @Test
    public void testIsUniform() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = TList.of(1, 1, 1, 1, 1, 1).isUniform();
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAccumFromStart_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> result = TList.of(0, 1, 2, 3).map(p->p.toString()).accumFromStart((a,b)->a+b);
        TList<String> expected = TList.of("0", "01", "012", "0123");
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAccumFromStart_Function_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> result = TList.of(0, 1, 2, 3).accumFromStart(f->Integer.toString(f), (a,b)->a+Integer.toString(b));
        TList<String> expected = TList.of("0", "01", "012", "0123");
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testIndexOf() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TList.of(0, 1, 2, 3, 4, 3, 2, 1).indexOf(i->i>2);
        int expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetOpt() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(0, 1, 2, 3, 4, 5).getOpt(3);
        Optional<Integer> expected = Optional.of(3);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetOpt1() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(0, 1, 2, 3, 4, 5).getOpt(6);
        Optional<Integer> expected = Optional.empty();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMin_Comparator() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(5, 2, 3, 1, 4, 4).min(inc(i->i));
        Optional<Integer> expected = Optional.of(1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMin_Function() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(5, 2, 3, 1, 4, 4).min(i->i);
        Optional<Integer> expected = Optional.of(1);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMax_Comparator() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(5, 2, 3, 1, 4, 4).max(inc(i->i));
        Optional<Integer> expected = Optional.of(5);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMax_Function() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(5, 2, 3, 1, 4, 4).max(i->i);
        Optional<Integer> expected = Optional.of(5);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testW_0args() {
        System.out.println(test.TestUtils.methodName(0));
        TList<P<Integer, Integer>> result = TList.of(0, 1, 2, 3).w();
        TList<P<Integer, Integer>> expected = TList.of(P.p(0, 0), P.p(1, 1), P.p(2, 2), P.p(3, 3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testW_Function_Function() {
        System.out.println(test.TestUtils.methodName(0));
        TList<P<Integer, Integer>> result = TList.of(0, 1, 2, 3).w(i->i, i->i);
        TList<P<Integer, Integer>> expected = TList.of(P.p(0, 0), P.p(1, 1), P.p(2, 2), P.p(3, 3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubListAnyway() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2).subListAnyway(3, 5);
        TList<Integer> expected = TList.empty();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubListAnyway2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2).subListAnyway(1, 5);
        TList<Integer> expected = TList.of(1, 2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubListAnyway3() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2).subListAnyway(1, 3);
        TList<Integer> expected = TList.of(1, 2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStartings() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.of(0, 1, 2, 3).startings();
        TList<TList<Integer>> expected = TList.of(TList.of(0), TList.of(0, 1), TList.of(0, 1, 2), TList.of(0, 1, 2, 3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEndings() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.of(0, 1, 2, 3).endings();
        TList<TList<Integer>> expected = TList.of(TList.of(0, 1, 2, 3), TList.of(1, 2, 3), TList.of(2, 3), TList.of(3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSetLast() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2, 3);
        result.setLast(0, 4);
        TList<Integer> expected = TList.of(0, 1, 2, 4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLastOpt() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(0, 1, 2).lastOpt(0);
        Optional<Integer> expected = Optional.of(2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testLastOpt2() {
        System.out.println(test.TestUtils.methodName(0));
        Optional<Integer> result = TList.of(0, 1, 2).lastOpt(3);
        Optional<Integer> expected = Optional.empty();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testForAll() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = TList.of(0,2,4,6).forAll(i->i%2==0);
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testForAllFalse() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = TList.of(0,2,3,6).forAll(i->i%2==0);
        boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExists() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = TList.of(0,2,3,6).exists(i->i%2!=0);
        boolean expected = true;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExistsFalse() {
        System.out.println(test.TestUtils.methodName(0));
        boolean result = TList.of(0,2,4,6).exists(i->i%2!=0);
        boolean expected = false;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNCopies() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.nCopies(4, 0);
        TList<Integer> expected = TList.of(0,0,0,0);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testNumberOfChanges_BiPredicate() {
    }

    @Test
    public void testNumberOfChanges_0args() {
        System.out.println(test.TestUtils.methodName(0));
        int result = TList.of(0,0,1,0,1,1,1,0).numberOfChanges();
        int expected = 4;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToTList() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = a2l(0,1,2,3,4).stream().collect(toTList());
        TList<Integer> expected = TList.of(0,1,2,3,4);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSubList_Range() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.range(0, 20).subList(new Range<>(1, 5));
        TList<Integer> expected = TList.range(1,5);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testAppend() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.of(0, 1, 2);
        result.append(TList.of(3,4,5));
        TList<Integer> expected = TList.of(0,1,2);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testInsertAt_int_TList() {
    }

    @Test
    public void testInsertAt_int_GenericType() {
    }

    @Test
    public void testSfix() {
    }

    @Test
    public void testFixDebug_int() {
    }

    @Test
    public void testFixDebug_0args() {
    }

    @Test
    public void testTeep_String() {
    }

    @Test
    public void testTeep_0args() {
    }

    @Test
    public void testCache() {
    }

    @Test
    public void testRange_int_int() {
    }

    @Test
    public void testRangeSym() {
    }

    @Test
    public void testRange_Range() {
    }

    @Test
    public void testSet_List() {
    }

    @Test
    public void testSet_Collection() {
    }

    @Test
    public void testSof() {
    }

    @Test
    public void testIsAscending() {
    }

    @Test
    public void testIsDescending() {
    }

    @Test
    public void testIsAscendingOrEqual() {
    }

    @Test
    public void testIsDescendingOrEqual() {
    }

    @Test
    public void testMapc() {
    }

    @Test
    public void testFlatMapc() {
    }

    @Test
    public void testTransposeT() {
    }

    @Test
    public void testHeap() {
    }

    @Test
    public void testHeapFromStart_Function_BiFunction() {
    }

    @Test
    public void testPreheap_Function_BiFunction() {
    }

    @Test
    public void testHeapFromStart_BiFunction() {
    }

    @Test
    public void testPreheap_BiFunction() {
    }

    @Test
    public void testAverageD() {
    }

    @Test
    public void testAverageL() {
    }

    @Test
    public void testAverageI() {
    }

    @Test
    public void testSumD() {
    }

    @Test
    public void testSumL() {
    }

    @Test
    public void testSumI() {
    }

    @Test
    public void testSubLists() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(0,1,2,3,4,5).subLists(3);
        TList<TList<Integer>> expected = TList.range(0,3).map(i->TList.range(i,i+3));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFold() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(0,1,2,3,4,5,6,7).fold(3);
        TList<TList<Integer>> expected = TList.sof(TList.sof(0,1,2),TList.sof(3,4,5),TList.sof(6,7));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testAppend_GenericType() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sof(0,1,2,3).append(4,5,6);
        TList<Integer> expected = TList.sof(0,1,2,3,4,5,6);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMatrix_List() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<P<Integer,Integer>>> result = TList.sof(0,1,2).matrix(TList.sof(3,4));
        TList<TList<P<Integer,Integer>>> expected = TList.sof(TList.sof(P.p(0,3),P.p(0,4)),
                                                              TList.sof(P.p(1,3),P.p(1,4)),
                                                              TList.sof(P.p(2,3),P.p(2,4)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMatrix_List_BiFunction() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<P<Integer,Integer>>> result = TList.sof(0,1,2).matrix(TList.sof(3,4),(a,b)->P.p(a,b));
        TList<TList<P<Integer,Integer>>> expected = TList.sof(TList.sof(P.p(0,3),P.p(0,4)),
                                                              TList.sof(P.p(1,3),P.p(1,4)),
                                                              TList.sof(P.p(2,3),P.p(2,4)));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
     }

    @Test
    public void testDelimitByValue() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> result = TList.sof(1,2,3,4).delimitByValue(i->Integer.toString(i), "|");
        TList<String> expected = TList.sof("1","|","2","|","3","|","4");
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToWrappedString() {
        System.out.println(test.TestUtils.methodName(0));
        String result = TList.sof("test","a bit","longer").toWrappedString();
        String expected = "test\na bit\nlonger";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToFlatString() {
        System.out.println(test.TestUtils.methodName(0));
        String result = TList.sof("test","a bit","longer").toFlatString();
        String expected = "testa bitlonger";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToDelimitedString() {
        System.out.println(test.TestUtils.methodName(0));
        String result = TList.sof("test","a bit","longer").toDelimitedString(" ");
        String expected = "test a bit longer";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testToCatenatedString() {
        System.out.println(test.TestUtils.methodName(0));
        String result = TList.sof("test","a bit","longer").toCatenatedString(" ");
        String expected = "test a bit longer";
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testDataProvider() {
        // test is omitted
    }

    @Test
    public void testHomogeneousProductBorders() {
    }

    @Test
    public void testIterator() {
        // test is omitted.
    }

    @Test
    public void testSubList_int_int() {
        // test is omitted.
    }
    
    @Test
    public void testSkipRange() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.skipRange(new Range<>(5,55), 10);
        TList<Integer> expected = TList.sof(5,15,25,35,45);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSkipRange2() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.skipRange(new Range<>(5,56), 10);
        TList<Integer> expected = TList.sof(5,15,25,35,45,55);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSkipRange3() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.skipRange(new Range<>(0,100), 10);
        TList<Integer> expected = TList.sof(0,10,20,30,40,50,60,70,80,90);
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testDiffChunk() {
        System.out.println(test.TestUtils.methodName(0));
        Integer[][] resultO = {{0,1},{0,2},{1,2},{1,3},{2,4},{2,5},{2,6},{3,7},{3,8}};
        TList<TList<Integer>> tested = TList.sof(resultO).map(a->TList.sof(a));
        TList<TList<TList<Integer>>> result = tested.diffChunk((a,b)->!a.get(0).equals(b.get(0)));
        Integer[][][] expectedO = {{{0,1},{0,2}},{{1,2},{1,3}},{{2,4},{2,5},{2,6}},{{3,7},{3,8}}};
        TList<TList<TList<Integer>>> expected = TList.sof(expectedO).map(aa->TList.sof(aa).map(a->TList.sof(a))).sfix();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testDiffChunk2() {
        System.out.println(test.TestUtils.methodName(0));
        Integer[][] resultO = {{0,1},{0,2},{1,2},{1,3},{2,4},{2,5},{2,6},{3,7},{3,8},{4,9}};
        TList<TList<Integer>> tested = TList.sof(resultO).map(a->TList.sof(a));
        TList<TList<TList<Integer>>> result = tested.diffChunk((a,b)->!a.get(0).equals(b.get(0)));
        Integer[][][] expectedO = {{{0,1},{0,2}},{{1,2},{1,3}},{{2,4},{2,5},{2,6}},{{3,7},{3,8}},{{4,9}}};
        TList<TList<TList<Integer>>> expected = TList.sof(expectedO).map(aa->TList.sof(aa).map(a->TList.sof(a))).sfix();
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> tested = TList.sof(10000,10001,10002,1003,10004,10005);
        TList<Integer> other1 = TList.sof(9999,10001,10002,10005);
        TList<Integer> other2 = TList.sof(10001,10002,10006);
        TList<Integer> result = tested.intersect(other1,other2);
        TList<Integer> expected = TList.sof(10001,10002);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersectByIdentity() {
        System.out.println(test.TestUtils.methodName(0));
        Integer i = 10001;
        Integer j = 10002;
        TList<Integer> tested = TList.sof(10000,i,j,10003,10004,10005);
        TList<Integer> other1 = TList.sof(9999,i,j,10003,10005);
        TList<Integer> other2 = TList.sof(i,j,10003,10006);
        TList<Integer> result = tested.intersectByIdentity(other1,other2);
        TList<Integer> expected = TList.sof(10001,10002);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testIntersect_Function() {
        System.out.println(test.TestUtils.methodName(0));
        Integer i = 10001;
        Integer j = 10002;
        Set<Integer> result = TList.sof(TList.sof(9999,i,j,10003,10005),TList.sof(i,j,10003,10006)).intersect(l->l);
        Set<Integer> expected = TList.sof(10001,10002,10003).toSet();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    

    @Test
    public void testIntersectByIdentity_Function() {
        System.out.println(test.TestUtils.methodName(0));
        Integer i = 10001;
        Integer j = 10002;
        Set<Integer> result = TList.sof(TList.sof(9999,i,j,10003,10005),TList.sof(i,j,10003,10006)).intersectByIdentity(l->l);
        Set<Integer> expected = TList.sof(i,j).toIdentitySet();
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testTrimmedChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(0,5,5,5,0,4,4,0,0,6).trimmedChunk(i->i<=0);
        TList<TList<Integer>> expected = TList.sof(TList.sof(),TList.sof(5,5,5),TList.sof(4,4),TList.sof(),TList.sof(6));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testEnvelopChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(0,5,5,5,0,4,4,0,0,6).envelopChunk(i->i<=0);
        TList<TList<Integer>> expected = TList.sof(TList.sof(0),TList.sof(0,5,5,5,0),TList.sof(0,4,4,0),TList.sof(0,0),TList.sof(0,6));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testReverseChunk() {
        System.out.println(test.TestUtils.methodName(0));
        TList<TList<Integer>> result = TList.sof(0,5,5,5,0,4,4,0,0,6).reverseChunk(i->i<=0);
        TList<TList<Integer>> expected = TList.sof(TList.sof(),TList.sof(0,5,5,5),TList.sof(0,4,4),TList.sof(0),TList.sof(0,6));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStartFrom_ListArr() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sof(5,6,7).startFrom(TList.sof(0,1,2),TList.sof(3,4));
        TList<Integer> expected = TList.sof(0,1,2,3,4,5,6,7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testStartFrom_GenericType() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sof(3,4,5).startFrom(0,1,2);
        TList<Integer> expected = TList.sof(0,1,2,3,4,5);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testGetDebug() {
    }

    @Test
    public void testAverageSampleD() {
    }

    @Test
    public void testAverageSampleL() {
    }

    @Test
    public void testAverageSampleI() {
    }

    @Test
    public void testMinval() {
    }

    @Test
    public void testMaxval() {
    }

    @Test
    public void testDiffChunk_BiPredicate() {
    }

    @Test
    public void testDiffChunk_BiPredicate_GenericType() {
    }

    @Test
    public void testToIdentitySet() {
    }

    @Test
    public void testToSet() {
        // this is merely a wrapper of jdk
    }

    @Test
    public void testIntersect_TListArr() {
    }

    @Test
    public void testIntersect_TList() {
    }

    @Test
    public void testIntersectByIdentity_TListArr() {
    }

    @Test
    public void testIntersectByIdentity_TList() {
    }

    @Test
    public void testFilterAt() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Integer> result = TList.sof(0,1,0,0,1,0,1,1).filterAt(i->i==1);
        TList<Integer> expected = TList.sof(1,4,6,7);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
