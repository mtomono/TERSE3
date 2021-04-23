/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import debug.Te;
import static function.PassedConsumer.passC;
import static java.lang.Thread.sleep;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TListFlatMapPerformanceNGTest {
    
    public TListFlatMapPerformanceNGTest() {
    }
    static class Slow {
        static int counter;
        final private int value;
        public Slow(int value) {
            counter++;
            this.value=value;
        }
        public int get() {
            passC(x->sleep(1));
            return value;
        }
    }
    @Test
    public void testRandomAccess10() {
        System.out.println(test.TestUtils.methodName(0));
        Slow.counter=0;
        TList<Slow> testedBase=TList.range(0,1000).map(i->new Slow(i)).sfix();
        Te.e(testedBase.getClass());
        TList<TList<Slow>> tested=TList.nCopies(10, testedBase);
        boolean  result = tested.flatMap(l->l).forAll(i->i.get()>10000010);
        boolean  expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        System.out.println(Slow.counter);
    }
    @Test
    public void testRandomAccess100() {
        System.out.println(test.TestUtils.methodName(0));
        Slow.counter=0;
        TList<Slow> testedBase=TList.range(0,1000).map(i->new Slow(i)).sfix();
        Te.e(testedBase.getClass());
        TList<TList<Slow>> tested=TList.nCopies(100, testedBase);
        boolean  result = tested.flatMap(l->l).forAll(i->i.get()>10000010);
        boolean  expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        Te.e(Slow.counter);
    }
    @Test
    public void testRandomAccessIter100() {
        System.out.println(test.TestUtils.methodName(0));
        Slow.counter=0;
        TList<Slow> testedBase=TList.range(0,1000).map(i->new Slow(i)).sfix();
        Te.e(testedBase.getClass());
        TList<TList<Slow>> tested=TList.nCopies(1000, testedBase);
        boolean  result = tested.flatten(l->l).anyMatch(i->i.get()>10000010);
        boolean  expected = false;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
        Te.e(Slow.counter);
    }
}
