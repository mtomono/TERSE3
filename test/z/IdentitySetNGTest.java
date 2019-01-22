/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class IdentitySetNGTest {
    
    public IdentitySetNGTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    class Tested {
        int i;
        public Tested(int i) {
            this.i = i;
        }
        
        @Override
        public boolean equals(Object e) {
            if (e == null) {
                return false;
            }
            if (!(e instanceof Tested)) {
                return false;
            }
            Tested t = (Tested) e;
            return t.i==this.i;
        }
    }
    
    Tested survivor1 = new Tested(1);
    Tested survivor2 = new Tested(2);
    
    Set<Tested> set0 = new IdentityHashMap<Tested,Object>() {
        void add(Tested test) {
            put(test,test);
        }
        {
            add(new Tested(0));
            add(survivor1);
            add(survivor2);
            add(new Tested(3));
        }
    }.keySet();

    Set<Tested> set1 = new IdentityHashMap<Tested,Object>() {
        void add(Tested test) {
            put(test,test);
        }
        {
            add(new Tested(0));
            add(survivor1);
            add(survivor2);
            add(new Tested(3));
        }
    }.keySet();
    
    @Test
    public void testRetainAll() {
        System.out.println(test.TestUtils.methodName(0));
        set0.retainAll(set1);
        Set<Tested> result = set0;
        Set<Tested> expected = new HashSet() {{
            add(survivor1);
            add(survivor2);
        }};
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
