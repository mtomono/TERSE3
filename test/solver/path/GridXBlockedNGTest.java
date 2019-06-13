/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.path;

import org.testng.annotations.Test;
import static shape.ShapeUtil.p2i;
import static shape.ShapeUtil.p3i;
import static solver.path.AStarGridXTestHelper.test2D;
import static solver.path.AStarGridXTestHelper.test2DPath;
import static solver.path.AStarGridXTestHelper.test3D;
import static solver.path.AStarGridXTestHelper.test3DPath;
import static solver.path.AStarGridXTestHelper.toGridX;

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
                "          " + //0
                "   XXX   X" + //1
                "          " + //2
                "          " + //3
                "XXXXXXXX  " + //4
                "          " + //5
                "          " ; //6
        System.out.println(case1);
        System.out.println(toGridX(case1,9,6).toString());
        System.out.println(test2DPath(toGridX(case1,9,6),p2i(3,0),p2i(5,6)));
        System.out.println(test2D(toGridX(case1,9,6),p2i(3,0),p2i(5,6)));
    }

    @Test
    public void testCase3D_1() {
        String case1 = 
             // +0123456789+
                "          " + //0
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
                
                "          " + //0
                "   XXXXXX " + //1
                "          " + //2
                "          " + //3
                "          " + //4
                "          " + //5
                "          " ; //6
                
        System.out.println(case1);
        System.out.println(toGridX(case1,9,6,2).toString());
        System.out.println(test3DPath(toGridX(case1,9,6,2),p3i(3,0,0),p3i(3,0,2)));
        System.out.println(test3D(toGridX(case1,9,6,2),p3i(3,0,0),p3i(3,0,2)));
    }

    @Test
    public void testHelix() {
        String case1 = 
             // +0123456789+
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
                
                "XXXXX     " + //0
                "XXXXX     " + //1
                "XXXXX     " + //2
                "XXXXX     " + //3
                "XXXXX     " + //4
                "XXXXX     " + //5
                "XXXXX     " ; //6
                
        System.out.println(test3D(toGridX(case1+case1,9,6,7),p3i(3,0,0),p3i(7,0,7))); //BE CAREFUL: in this case, the string is doubled.
    }
}
