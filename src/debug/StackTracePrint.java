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
    static public void stackTracePrint(int from, int to) {
        System.out.println(TList.sof(Thread.currentThread().getStackTrace()).subList(from,to).toWrappedString());
    }
    
    static public void stackTracePrint(int upto) {
        stackTracePrint(1,upto);
    }
    
    static public void stackTracePrint() {
        System.out.println(TList.sof(Thread.currentThread().getStackTrace()).toWrappedString());
    }
}
