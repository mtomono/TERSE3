/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.c.a2l;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static test.OptionalTest.*;

/**
 *
 * @author masao
 */
public class FillerNGTest {
    
    public FillerNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testFiller_int() {
        System.out.println(test.TestUtils.methodName(0));
        List<Optional<Integer>> result = Filler.filler(3);
        List<Optional<Integer>> expected = a2l(e(), e(), e());
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testFiller_int_int() {
        System.out.println(test.TestUtils.methodName(0));
        List<List<Optional<Integer>>> result = Filler.filler(3, 2);
        List<List<Optional<Integer>>> expected = a2l(a2l(e(), e(), e()), a2l(e(), e(), e()));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
