/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.path;

import collection.P;
import java.util.Map;
import java.util.TreeMap;
import orderedSet.Comparators;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class ShortestPathNGTest {
    
    public ShortestPathNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testShortestPathBasic() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),3);
                put(P.p(0,2),5);
                put(P.p(1,5),2);
                put(P.p(2,5),1);
            }
        };
        ShortestPathBasic<Integer> d = new ShortestPathBasic<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 5).get();
        int expected = 5;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testShortestPathBasic0() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,7),2);
            }
        };
        ShortestPathBasic<Integer> d = new ShortestPathBasic<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShortestPathBasicCut() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                //put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,7),2);
            }
        };
        ShortestPathBasic<Integer> d = new ShortestPathBasic<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShortestPathPruned() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                //put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,7),2);
            }
        };
        ShortestPathNoRevisit<Integer> d = new ShortestPathNoRevisit<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShortestPathPrunedLooped() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                put(P.p(3,0),3);
                put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,7),2);
            }
        };
        ShortestPathNoRevisit<Integer> d = new ShortestPathNoRevisit<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShortestPathMemo() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                //put(P.p(6,1),2);
                put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,7),2);
            }
        };
        ShortestPathMemo<Integer> d = new ShortestPathMemo<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    @Test
    public void testShortestPathMemoLoop() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                put(P.p(3,0),2);
                //put(P.p(6,1),2);
                put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,0),3);
                put(P.p(6,7),2);
            }
        };
        ShortestPathMemo<Integer> d = new ShortestPathMemo<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testShortestPathH() {
        Map<P<Integer,Integer>, Integer> graph = new TreeMap<P<Integer,Integer>, Integer>(Comparators.<P<Integer,Integer>>sof(p->p.l(),p->p.r()).compile()) {
            {
                put(P.p(0,1),1);
                put(P.p(0,2),7);
                put(P.p(0,3),2);
                put(P.p(1,4),2);
                put(P.p(1,5),4);
                put(P.p(2,5),2);
                put(P.p(2,6),3);
                put(P.p(3,6),5);
                put(P.p(3,0),2);
                //put(P.p(6,1),2);
                put(P.p(4,5),1);
                put(P.p(5,7),6);
                put(P.p(6,0),3);
                put(P.p(6,7),2);
            }
        };
        ShortestPathD<Integer> d = new ShortestPathD<>(graph);
        System.out.println(test.TestUtils.methodName(0));
        int result = d.find(0, 7).get();
        int expected = 9;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
