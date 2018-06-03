/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class FoldListNGTest {
    
    public FoldListNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @DataProvider(name="get")
    public Object[][] get(){
        return new Object[][]{
            {new FoldList<>(TList.of(0,1,2,3,4,5),2), TList.of(TList.of(0,1),TList.of(2,3),TList.of(4,5))}
        };
    }
    @Test(dataProvider="get")
    public void testGet(List<TList<Integer>> result, TList<TList<Integer>> expected) {
        System.out.println(test.TestUtils.methodName(0));
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testSize() {
    }
    
}
