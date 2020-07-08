/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author mtomono
 */
public class AssertNGTest {
    
    public AssertNGTest() {
    }
    
    class Tested {
        public boolean tested() {
            fail();
            return true;
        }

        public void test() {
            assert tested();
            System.out.println("www");
        }
    }
    
    /**
     * lメソッドのテスト、クラスSimpleTraceのテスト。
     * ClassLoader経由でAssertionを無効にすることでFailに実行が到達しない
     * なお、プロジェクトテストで実行するとAssertionを無効にできないため失敗する
     */
    @Test
    public void testL() {
        ClassLoader.getSystemClassLoader().setPackageAssertionStatus("debug", false);
        new Tested().test();
        ClassLoader.getSystemClassLoader().setPackageAssertionStatus("debug", true);
    }
}
