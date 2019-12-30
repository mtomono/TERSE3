/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class RoundNGTest {
    
    public RoundNGTest() {
    }
    
    public void ceilTest(double v) {
        System.out.println(v+":"+ceil(v));
    }
    
    public void floorTest(double v) {
        System.out.println(v+":"+floor(v));
    }
    @Test
    public void testCeil() {
        System.out.println(test.TestUtils.methodName(0));
        ceilTest(1);
        ceilTest(0.5);
        ceilTest(0);
        ceilTest(-0.5);
        ceilTest(-1);
    }
    @Test
    public void testFloor() {
        System.out.println(test.TestUtils.methodName(0));
        floorTest(1);
        floorTest(0.5);
        floorTest(0);
        floorTest(-0.5);
        floorTest(-1);
    }
}
