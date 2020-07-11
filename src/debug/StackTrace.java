/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import collection.TList;

/**
 *
 * @author masao
 */
public class StackTrace {
    final public TList<StackTraceElement> stack;
    private StackTrace(TList<StackTraceElement> stack) {
        this.stack=stack;
    }
    static public StackTrace getStackTrace() {
        return new StackTrace(TList.sof(Thread.currentThread().getStackTrace()));
    }
    
    public StackTrace subList(int from, int to) {
        return new StackTrace(stack.subList(from,to));
    }
    
    public StackTrace subList(int upto) {
        return subList(1, upto);
    }
    
    public StackTrace print() {
        System.out.println(stack.toWrappedString());
        return this;
    }
}
