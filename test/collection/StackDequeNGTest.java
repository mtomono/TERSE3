/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class StackDequeNGTest {
    
    public StackDequeNGTest() {
    }

    @Test
    public void testPush() {
        System.out.println(test.TestUtils.methodName(0));
        var result = StackDeque.c(TList.sof(2,1,0));
        result.push(TList.sof(3,4,5).iterator());
        var expected = StackDeque.c(TList.sof(5,4,3,2,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testPushR() {
        System.out.println(test.TestUtils.methodName(0));
        var result = StackDeque.c(TList.sof(2,1,0));
        result.pushR(TList.sof(5,4,3));
        var expected = StackDeque.c(TList.sof(5,4,3,2,1,0));
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    static class Node {
        int x;
        TList<Node> below;
        public Node(int x, TList<Node> below) {
            this.x=x;
            this.below=below;
        }
    }
    
    static Node n(int x, TList<Node> below) {
        return new Node(x, below);
    }
    static Node n(int x) {
        return new Node(x, TList.empty());
    }
    
    Node tested=n(0, TList.sof(n(1, TList.sof(n(2),n(3))),n(4)));
    @Test
    public void testExec() {
        System.out.println(test.TestUtils.methodName(0));
        var result = TList.c();
        new StackDeque<>(tested).exec(n->{result.add(n.x);return n.below;});
        var expected = TList.sof(0,4,1,3,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExeci() {
        System.out.println(test.TestUtils.methodName(0));
        var result = TList.c();
        new StackDeque<>(tested).execi(n->{result.add(n.x);return n.below.iterator();});
        var expected = TList.sof(0,4,1,3,2);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExecr() {
        System.out.println(test.TestUtils.methodName(0));
        var result = TList.c();
        new StackDeque<>(tested).execr(n->{result.add(n.x);return n.below;});
        var expected = TList.sof(0,1,2,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }

    @Test
    public void testExecri() {
        System.out.println(test.TestUtils.methodName(0));
        var result = TList.c();
        new StackDeque<>(tested).execri(n->{result.add(n.x);return n.below.iterator();});
        var expected = TList.sof(0,1,2,3,4);
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
    
}
