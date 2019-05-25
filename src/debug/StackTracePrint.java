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
public class StackTracePrint {
    static public TList<StackTraceElement> getStackTrace() {
        return TList.sof(Thread.currentThread().getStackTrace());
    }
    
    static public TList<StackTraceElement> getStackTrace(int from, int to) {
        return getStackTrace().subList(from,to);
    }
    
    static public TList<StackTraceElement> getStackTrace(int upto) {
        return getStackTrace(1, upto);
    }
    
    static public void print(TList<StackTraceElement> stackTrace) {
        System.out.println(stackTrace.toWrappedString());
    }
}
