/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static string.Strings.asCharList;
import test.PTe;

/**
 *
 * @author masao
 */
public class LevenshteinNGTest1 {
    
    public LevenshteinNGTest1() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testBasicVINTNER() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("vintner");
        TList<Character> y = asCharList("writers");
        TList<Integer> result = KnapsackDP1.levenshtein(x,y).psolve();
        PTe.e(KnapsackDP1.levenshtein(x,y).psolvew().toWrappedString());
        Integer expected = 5;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }

    @Test
    public void testBasicBOOK() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("book");
        TList<Character> y = asCharList("desk");
        TList<Integer> result = KnapsackDP1.levenshtein(x,y).psolve();
        PTe.e(KnapsackDP1.levenshtein(x,y).psolvew().toWrappedString());
        Integer expected = 3;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }

    @Test
    public void testBasicPirikapirirara() {
        System.out.println(test.TestUtils.methodName(0));
        TList<Character> x = asCharList("pirikapirirara");
        TList<Character> y = asCharList("poporinapeperuto");
        TList<Integer> result = KnapsackDP1.levenshtein(x,y).psolve();
        PTe.e(KnapsackDP1.levenshtein(x,y).psolvew().toWrappedString());
        Integer expected =10;
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result.last(), expected);
    }
}
