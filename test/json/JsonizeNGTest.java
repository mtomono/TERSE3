/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import collection.TList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class JsonizeNGTest {
    
    public JsonizeNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @Test
    public void testObject() {
        System.out.println(test.TestUtils.methodName(0));
        String tested = "012345";
        Jsonize x = Jsonize.c();
        StringBuilder sb = new StringBuilder();
        x.<String>object(
                x.attr("2",x.chara(s->s.charAt(2))),
                x.attr("4",x.chara(s->s.charAt(4))),
                x.attr("12",x.string(s->s.substring(1,3))),
                x.attr("6",x.value(s->s.length()))
        ).accept(sb, tested);
        String result = sb.toString();
        String expected = "{\"2\":'2',\"4\":'4',\"12\":\"12\",\"6\":6}";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testObjectNested() {
        System.out.println(test.TestUtils.methodName(0));
        String tested = "012345";
        Jsonize x = Jsonize.c();
        StringBuilder sb = new StringBuilder();
        x.<String>object(
                x.attr("2",x.chara(s->s.charAt(2))),
                x.attr("4",x.chara(s->s.charAt(4))),
                x.attr("12",x.object(s->s.substring(1,3),x.chara(s->s.charAt(0)))),
                x.attr("6",x.value(s->s.length()))
        ).accept(sb, tested);
        String result = sb.toString();
        String expected = "{\"2\":'2',\"4\":'4',\"12\":{'1'},\"6\":6}";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testArray() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> tested = TList.sof("01","12","23","34","45");
        Jsonize x = Jsonize.c();
        StringBuilder sb = new StringBuilder();
        x.<String>array(
                x.object(x.attr("0",x.chara(s->s.charAt(0))))
        ).accept(sb, tested);
        String result = sb.toString();
        String expected = "[{\"0\":'0'},{\"0\":'1'},{\"0\":'2'},{\"0\":'3'},{\"0\":'4'}]";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

    @Test
    public void testArray1() {
        System.out.println(test.TestUtils.methodName(0));
        TList<String> tested = TList.sof("01","12","23","34","45");
        Jsonize x = Jsonize.c();
        StringBuilder sb = new StringBuilder();
        x.<String>array(
                x.object(x.attr("1",x.chara(s->s.charAt(1))))
        ).accept(sb, tested);
        String result = sb.toString();
        String expected = "[{\"1\":'1'},{\"1\":'2'},{\"1\":'3'},{\"1\":'4'},{\"1\":'5'}]";
        System.out.println("result  : "+result);
        System.out.println("expected: "+expected);
        assertEquals(result, expected);
    }

}
