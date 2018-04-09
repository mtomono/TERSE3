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

package debug;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Function;

/**
 *
 * @author mtomono
 */
public class SimpleTrace implements Trace {
    static String defaultIndentUnit = "  ";
    static PrintWriter defaultOutput = new PrintWriter(System.out, true);
    String indent;
    String indentUnit;
    PrintWriter output;
    
    public SimpleTrace() {
        this(defaultOutput, defaultIndentUnit);
    }
    
    public SimpleTrace(PrintWriter output, String indentUnit) {
        this.indent = "";
        this.output = output;
        this.indentUnit = indentUnit;
    }
    
    public boolean cr() {
        output.flush();
        return true;
    }
    
    private void indentUp() {
        indent += indentUnit;
    }
    private void indentDown() {
        indent = indent.substring(indentUnit.length());
    }
    private String indent() {
        return indent;
    }

    @Override
    public boolean l(Function<String, String>... log) {
        output.println(indent + Arrays.asList(log).stream().map(l->l.apply("")).reduce("", (a, b)->a+b));
        return true;
    }

    @Override
    public boolean u(Function<String, String>... log) {
        indentUp();
        l(log);
        return true;
    }

    @Override
    public boolean d(Function<String, String>... log) {
        l(log);
        indentDown();
        return true;
    }
        
}
