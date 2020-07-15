/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class StringGridNGTest {
    
    public StringGridNGTest() {
    }

    @Test
    public void testAroundHill() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(aroundHill().solve());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testOverHill() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(overHill().solve());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testThruSlap() {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println(thruSlap().solve());
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    StringGrid aroundHill() {
        return new StringGrid(
         // +0123456789+
            "       S  " + //0
            "   XXX   X" + //1
            "  XXXXX   " + //2
            " XXG      " + //3
            "XXX   XX  " + //4
            "      XX  " + //5
            "          " + //6

            "          " + //0
            "          " + //1
            "          " + //2
            "          " + //3
            "          " + //4
            "          " + //5
            "          " + //6

            "          " + //0
            "          " + //1
            "          " + //2
            "          " + //3
            "          " + //4
            "          " + //5
            "          "  //6
            ,9,6,2
        );
    }
        
    StringGrid overHill() {
        return new StringGrid(
             // +0123456789+
                "       S  " + //0
                "   XXX   X" + //1
                "  XXXXX   " + //2
                " XXG  XX  " + //3
                "XXX   XX  " + //4
                "      XX  " + //5
                "          " +
                
                "          " + //0
                "          " + //1
                "          " + //2
                "          " + //3
                "          " + //4
                "          " + //5
                "          " + //6
                
                "          " + //0
                "          " + //1
                "          " + //2
                "          " + //3
                "          " + //4
                "          " + //5
                "          "  //6
                ,9,6,2
        );
    }
    
    StringGrid thruSlap() {
        return new StringGrid(
             // +0123456789+
                "       S  " + //0
                "   XXX   X" + //1
                "  XXXXX   " + //2
                " XXG  XX  " + //3
                "XXX   XX  " + //4
                "      XX  " + //5
                "          " +
                
                "          " + //0
                "          " + //1
                "  XXXX    " + //2
                "  X    X  " + //3
                "  X    X  " + //4
                "          " + //5
                "          " + //6
                
                "          " + //0
                "          " + //1
                "          " + //2
                "          " + //3
                "          " + //4
                "          " + //5
                "          "  //6
                ,9,6,2
        );
    }
}
