/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import static java.lang.Character.toChars;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class KanjiNGTest {
    
    public KanjiNGTest() {
    }
    
    @Test
    public void testHokke() {
        System.out.println(test.TestUtils.methodName(0));
        String tested="𩸽定食叱る";
        char[] chars=tested.toCharArray();
        for(char c:chars)
            System.out.println(c);
        int[] cps=tested.codePoints().toArray();
        for(int i=0;i<cps.length;i++) {
            System.out.println(new String(cps,i,1));
            System.out.println(Integer.toHexString(cps[i]));
        }
        int result = 0;
        int expected = 0;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
    /**
     * UTF8 no BOM is the official encoding for JSON.
     * UTF8 is goodly defined to preserve 0x00-0x7F to be sacred, meaning 
     * this byte pattern is never used in other code points.
     * that will make the parsing easier by keeping other characters in 
     * comments or quotes.
     */
}
