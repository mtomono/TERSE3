/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import solver.AStarGrid;
import solver.AStarNodeGrid;
import collection.P;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static solver.AStarStatus.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shapeCollection.Grid;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class AStarOverGrid2NGTest {
    
    public AStarOverGrid2NGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    static Set<TPoint2i> blocks = new HashSet<TPoint2i>() {
        {
            add(p2i(-3, -1));
            add(p2i(-3, -2));
            add(p2i(-3, -3));
            add(p2i(-4, -3));
            add(p2i(-5, -3));
            add(p2i(-6, -3));
            add(p2i(0, 2));
            add(p2i(0, 3));
            add(p2i(0, 4));
            add(p2i(0, 5));
        }
    };
    
    @Test
    public void testSearch() {
        System.out.println(test.TestUtils.methodName(0));
        Grid<AStarNodeGrid> target = new Grid<>(p2i(-10,-10),p2i(10,10),(a,b)->new AStarNodeGrid(new TPoint2i(a,b),NONE));
        target.mark(blocks, p->new AStarNodeGrid(p, BLOCKED));
        Optional<AStarNodeGrid> x = new AStarGrid(target,p2i(-7,-4),p2i(3,5)).search();
        x.ifPresent(p->System.out.println(p.result()));
    }
}
