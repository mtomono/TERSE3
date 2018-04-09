/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package walker;

import collection.P;
import collection.TList;
import static collection.c.a2l;
import static collection.c.i2l;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class WalkerNGTest {
    
    public WalkerNGTest() {
    }

    @Test
    public void testSomeMethod() {
    }
    static class IntegerWalker extends AbstractWalker<Integer, Integer> {

        public IntegerWalker(Iterator<Integer> iterL, Iterator<Integer> iterR) {
            super(iterL, iterR);
        }

        @Override
        public boolean leftIsBehind(Integer left, Integer right) {
            return left < right;
        }
        
    }
    @Test
    public void test01() {
        System.out.println("test01");
        testRange(a2l(0, 5, 10, 15, 20, 25, 30, 35),
                  a2l(7, 8, 9, 12, 15, 21),
                  a2l(0, 5, 10, 10, 10, 10, 15, 15, 15, 20, 25),
                  a2l(7, 7,  7,  8,  9, 12, 12, 15, 21, 21, 21)
        );
    }
    public void testRange(List<Integer> leftIn, List<Integer> rightIn, List<Integer> leftOut, List<Integer> rightOut) {
        Walker<Integer, Integer> tested = new IntegerWalker(leftIn.iterator(), rightIn.iterator());
        List<P<Integer, Integer>> result = i2l(tested);
        List<P<Integer, Integer>> expected = TList.set(leftOut).pair(rightOut);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
