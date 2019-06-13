/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.path;

import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class GridXBlockedNGTest {
    
    public GridXBlockedNGTest() {
    }

    @Test
    public void testCase2D_1() {
        String case1 = 
             // +0123456789+
                "   S      " + //0
                "   XXX   X" + //1
                "          " + //2
                "          " + //3
                "XXXXXXXX  " + //4
                "          " + //5
                "     G    " ; //6
        System.out.println(case1);
        AStarGridXTestHelper h = new AStarGridXTestHelper(case1,9,6);
        System.out.println(h.toString());
        System.out.println(h.path());
        System.out.println(h.solve());
    }

    @Test
    public void testCase3D_1() {
        String case1 = 
             // +0123456789+
                "   S      " + //0
                "   XXX   X" + //1
                "          " + //2
                "          " + //3
                "XXXXXXXX  " + //4
                "          " + //5
                "          " +
                
                "XXXXXXXXXX" + //0
                "XXXXXXXXXX" + //1
                "XXXXXXXXXX" + //2
                "XXX     XX" + //3
                "XXX     XX" + //4
                "XXXXXXXXXX" + //5
                "XXXXXXXXXX" + //6
                
                "   G      " + //0
                "   XXXXXX " + //1
                "          " + //2
                "          " + //3
                "          " + //4
                "          " + //5
                "          " ; //6
                
        System.out.println(case1);
        AStarGridXTestHelper h = new AStarGridXTestHelper(case1,9,6,2);
        System.out.println(h.toString());
        System.out.println(h.path());
        System.out.println(h.solve());
    }

    @Test
    public void testHelix() {
        String case1 = 
             // +0123456789+
                "   S      " + //0
                "          " + //1
                "          " + //2
                "XXXXXXXXXX" + //3
                "XXXXXXXXXX" + //4
                "XXXXXXXXXX" + //5
                "XXXXXXXXXX" + //6
                
                "     XXXXX" + //0
                "     XXXXX" + //1
                "     XXXXX" + //2
                "     XXXXX" + //3
                "     XXXXX" + //4
                "     XXXXX" + //5
                "     XXXXX" + //6
                
                "XXXXXXXXXX" + //0
                "XXXXXXXXXX" + //1
                "XXXXXXXXXX" + //2
                "XXXXXXXXXX" + //3
                "          " + //4
                "          " + //5
                "          " + //6
                
                "XXXXX     " + //0
                "XXXXX     " + //1
                "XXXXX     " + //2
                "XXXXX     " + //3
                "XXXXX     " + //4
                "XXXXX     " + //5
                "XXXXX     " + //6
                
                "          " + //0
                "          " + //1
                "          " + //2
                "XXXXXXXXXX" + //3
                "XXXXXXXXXX" + //4
                "XXXXXXXXXX" + //5
                "XXXXXXXXXX" + //6
                
                "     XXXXX" + //0
                "     XXXXX" + //1
                "     XXXXX" + //2
                "     XXXXX" + //3
                "     XXXXX" + //4
                "     XXXXX" + //5
                "     XXXXX" + //6
                
                "XXXXXXXXXX" + //0
                "XXXXXXXXXX" + //1
                "XXXXXXXXXX" + //2
                "XXXXXXXXXX" + //3
                "          " + //4
                "          " + //5
                "          " + //6
                
                "XXXXX  G  " + //0
                "XXXXX     " + //1
                "XXXXX     " + //2
                "XXXXX     " + //3
                "XXXXX     " + //4
                "XXXXX     " + //5
                "XXXXX     " ; //6
        AStarGridXTestHelper h = new AStarGridXTestHelper(case1,9,6,7);
        System.out.println(h.solve()); //BE CAREFUL: in this case, the string is doubled.
    }
}
