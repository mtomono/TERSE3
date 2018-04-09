/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viewlist;

import viewlist.ViewList;
import viewlist.View;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import static viewlist.Fixtures.*;
import static viewlist.OptionalConstructor.*;

/**
 *
 * @author mtomono
 */
public class IViewList {
    
    public IViewList() {
    }

    @Test
    public void concat2words_natural() {
        ViewList<String> words = ViewList.natural(View.pre(testList1, 2));
        String result;
        result = words.stream().map(v->v.get(0).orElse("NULL ")+v.get(1).orElse("NULL "))
                               .reduce("", (a, b)->a + "|" + b);
        assertEquals(result, "|test1test0|test2test1|test3test2|test4test3|test5test4|NULL test5");
    }

    @Test
    public void concat2words_natural_alt() {
        class OptionalException extends Exception {
        }
        ViewList<String> words = ViewList.natural(View.pre(testList1, 2));
        String result = words.stream().map(v->oConstruct(v, l->l.get(0)+l.get(1)).orElse("NULL"))
                               .reduce("", (a, b)->a + "|" + b);
        assertEquals(result, "|test1test0|test2test1|test3test2|test4test3|test5test4|NULL");
    }

    @Test
    public void concat2words_reverse() {
        ViewList<String> words = ViewList.reverse(View.pre(testList1, 2));
        String result;
        result = words.stream().map(v->v.get(0).orElse("NULL ")+v.get(1).orElse("NULL "))
                               .reduce("", (a, b)->a + "|" + b);
        assertEquals(result, "|test0NULL |test1test0|test2test1|test3test2|test4test3|test5test4");
    }


    @Test
    public void concat2words_outer() {
        ViewList<String> words = ViewList.outer(View.pre(testList1, 2));
        String result;
        result = words.stream().map(v->v.get(0).orElse("NULL ")+v.get(1).orElse("NULL "))
                               .reduce("", (a, b)->a + "|" + b);
        assertEquals(result, "|test0NULL |test1test0|test2test1|test3test2|test4test3|test5test4|NULL test5");
    }
    
    @Test
    public void concat2words_inner() {
        ViewList<String> words = ViewList.inner(View.pre(testList1, 2));
        String result;
        result = words.stream().map(v->v.get(0).orElse("NULL ")+v.get(1).orElse("NULL "))
                               .reduce("", (a, b)->a + "|" + b);
        assertEquals(result, "|test1test0|test2test1|test3test2|test4test3|test5test4");
    }
}
