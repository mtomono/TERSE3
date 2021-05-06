/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiber;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TCordNGTest {
    
    public TCordNGTest() {
    }

    @Test
    public void testSet_TFiberArr() {
        System.out.println(test.TestUtils.methodName(0));
        ISource<Integer> start=new ISource<>(TList.sof(5,4,3,2,1,0).iterator());
        TList<TList<Integer>> result = TCord.set(TFiber.set(start),TFiber.set(start).map(i->i+1)).sink(()->TList.c());
        start.go();
        TList<TList<Integer>> expected = TList.sof(TList.sof(5,4,3,2,1,0),TList.sof(6,5,4,3,2,1));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testMap() {
        System.out.println(test.TestUtils.methodName(0));
        ISource<Integer> start=new ISource<>(TList.sof(5,4,3,2,1,0).iterator());
        TList<TList<Integer>> result = TCord.set(TFiber.set(start),TFiber.set(start).map(i->i+1)).map(i->i-1).sink(()->TList.c());
        start.go();
        TList<TList<Integer>> expected = TList.sof(TList.sof(4,3,2,1,0,-1),TList.sof(5,4,3,2,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFlatMap() {
        System.out.println(test.TestUtils.methodName(0));
        ISource<Integer> start=new ISource<>(TList.sof(5,4,3,2,1,0).iterator());
        TList<TList<Integer>> result = TCord.set(TFiber.set(start),TFiber.set(start).map(i->i+1)).flatMap(i->TList.range(0,i)).sink(()->TList.c());
        start.go();
        TList<TList<Integer>> expected = TList.sof(TList.sof(0,1,2,3,4,0,1,2,3,0,1,2,0,1,0),TList.sof(0,1,2,3,4,5,0,1,2,3,4,0,1,2,3,0,1,2,0,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFlatIter() {
        System.out.println(test.TestUtils.methodName(0));
        ISource<Integer> start=new ISource<>(TList.sof(5,4,3,2,1,0).iterator());
        TList<TList<Integer>> result = TCord.set(TFiber.set(start),TFiber.set(start).map(i->i+1)).flatIter(i->TList.range(0,i).iterator()).sink(()->TList.c());
        start.go();
        TList<TList<Integer>> expected = TList.sof(TList.sof(0,1,2,3,4,0,1,2,3,0,1,2,0,1,0),TList.sof(0,1,2,3,4,5,0,1,2,3,4,0,1,2,3,0,1,2,0,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFilter() {
        System.out.println(test.TestUtils.methodName(0));
        ISource<Integer> start=new ISource<>(TList.sof(5,4,3,2,1,0).iterator());
        TList<TList<Integer>> result = TCord.set(TFiber.set(start),TFiber.set(start).map(i->i+1)).filter(i->i%2==0).sink(()->TList.c());
        start.go();
        TList<TList<Integer>> expected = TList.sof(TList.sof(4,2,0),TList.sof(6,4,2));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
