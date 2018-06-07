/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package test;

import collection.TList;
import static collection.c.a2l;
import java.util.function.BinaryOperator;
import string.Strings;

/**
 *
 * @author masao
 */
public class TestUtils {
    static public void methodNamePrint() {
        methodNamePrint(1);
    }
    static public void methodNamePrint(int depth) {
        System.out.println(methodName(depth+1));
    }
    static public String methodName() {
        return methodName(1);
    }
    static public String methodName(int depth) {
        return new Throwable().getStackTrace()[depth+1].getMethodName();
    }
    static public void classNamePrint() {
        classNamePrint(1);
    }
    static public void classNamePrint(int depth) {
        System.out.println(className(depth+1));
    }
    static public String className() {
        return className(1);
    }
    static public String className(int depth) {
        return new Throwable().getStackTrace()[depth+1].getClassName();
    }
    static public int lineNumber(int depth) {
        return new Throwable().getStackTrace()[depth+1].getLineNumber();
    }
    
    /**
     * debug print.
     * base implementation of debug print which comes with line number.
     * who implements real pr() can adjust the depth.
     * by adjusting the depth, for example, programmer can put this method 
     * in each method and get the output with line number of where the method
     * is called. 
     * 
     * if you use this method intact in your code, the depth should be 0, but
     * it's bothering. you should make a wrapper of your own to hide this depth.
     * 
     * @param depth of nest
     * @param content objects to print
     */
    static public void pr(int depth, Object... content) {
        Object target = content.length==1 ? content[0] : a2l(content);
        System.out.println(Integer.toString(lineNumber(depth+1)) + ":" + target.toString());
    }
    
    /**
     * debug print.
     * this is the most typical way of using pr(). put this method call directly
     * on the code where matter of interest is happening.
     * see LITester for concrete usage.
     * 
     * where it is called is at depth 1 from this method
     * because this method is at depth 0.
     * @param content 
     */
    static public void pr(Object... content) {
        pr(1, content);
    }
    
    /**
     * debug print.
     * this is another typical way of using pr(). basically it's totally same as
     * pr(), but meaninglessly returning boolean value. this feature enables to
     * put this method after assert. by doing so, this output is automatically 
     * elliminated when the program is running under production mode.
     * 
     * if you use this method intact in your code, the depth should be 0, but
     * it's bothering. you should make a wrapper of your own to hide this depth.
     * 
     * @param depth
     * @param content
     * @return 
     */
    static public boolean prb(int depth, Object... content) {
        pr(depth+1, content);
        return true;
    }
    
    static public boolean prb(Object... content) {
        return prb(1, content);
    }

    /** debug print.
     * this is the typical way to use pr() to put in method while monitoring the
     * line number where method is called.
     * see LITester for concrete usage.
     * this method is 'assert' ready.
     * 
     * @param content 
     * @return  
     */
    static public boolean ppr(Object... content) {
        pr(2, content);
        return true;
    }
    
    static public String toStringCode(String source) {
        TList<String> lines = TList.set(a2l(source.split("\n", -1)));
        return "\"\" \n"+
        lines.map(s->"                + \""+s).toDelimitedString("\\n\"\n")+("\";");
    }
}
