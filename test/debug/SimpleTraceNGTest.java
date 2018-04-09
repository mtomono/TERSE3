/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static debug.Trace.*;

/**
 *
 * @author mtomono
 */
public class SimpleTraceNGTest {
    
    public SimpleTraceNGTest() {
    }
    
    class Tested {
        String eval() {
            fail();
            return "wep";
        }
    }

    /**
     * crメソッドのテスト、クラスSimpleTraceのテスト。
     */
    @Test
    public void testCr() {
    }

    /**
     * lメソッドのテスト、クラスSimpleTraceのテスト。
     * ClassLoader経由でAssertionを無効にすることでFailに実行が到達しない
     * なお、プロジェクトテストで実行するとAssertionを無効にできないため失敗する
     */
    @Test
    public void testL() {
        ClassLoader.getSystemClassLoader().setPackageAssertionStatus("debug", false);
        Trace tr= new SimpleTrace();
        tr.pl(s(""),s->new Tested().eval());
        ClassLoader.getSystemClassLoader().setPackageAssertionStatus("debug", true);
    }

    /**
     * uメソッドのテスト、クラスSimpleTraceのテスト。
     */
    @Test
    public void testU() {
    }

    /**
     * dメソッドのテスト、クラスSimpleTraceのテスト。
     */
    @Test
    public void testD() {
    }
    
}
