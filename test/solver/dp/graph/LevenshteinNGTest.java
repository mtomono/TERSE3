/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp.graph;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static shape.ShapeUtil.pni;
import static string.Strings.asCharList;
import test.PTe;

/**
 *
 * @author masao
 */
public class LevenshteinNGTest {
    
    public LevenshteinNGTest() {
    }

    @Test
    public void testVINTNER() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("vintner");
        TList<Character> y = asCharList("writers");
        Integer result = KnapsackDPg.levenshtein(x,y).grid.get(pni(x.size()-1,y.size()-1));
        PTe.e(KnapsackDPg.levenshtein(x,y).grid);
        Integer expected = 5;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
    
    @Test
    public void testPirikapirirara() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("pirikapirirara");
        TList<Character> y = asCharList("poporinapeperuto");
        Integer result = KnapsackDPg.levenshtein(x,y).grid.get(pni(x.size()-1,y.size()-1));
        PTe.e(KnapsackDPg.levenshtein(x,y).grid);
        Integer expected = 10;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }
}
