/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.IOException;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class StringFileNGTest {
    
    public StringFileNGTest() {
    }

    @Test
    public void testWrite() throws IOException {
        System.out.println(test.TestUtils.methodName(0));
        new StringFile("me.text").write("in survival");
    }
    
}
