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

package system;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class StdioFilter {
    InputStream in;
    PrintStream out;
    UnaryOperator<String> op;
    public StdioFilter(InputStream in, PrintStream out, UnaryOperator<String> op) {
        this.in = in;
        this.out = out;
        this.op = op;
    }
    public StdioFilter(UnaryOperator<String> op) {
        this(System.in, System.out, op);
    }
    public String load() {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
            sb.append(in.next());
        return sb.toString();
    }
    
    public void exec() {
        System.out.println(op.apply(load()));
    }
    
}
