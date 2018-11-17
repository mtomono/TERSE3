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

import java.io.PrintStream;
import java.util.function.Function;

/**
 * measure performance of a method call.
 * to measure performance of a method call x() of type X, use this object in this way.
 * static Performance<X> pf = new Performance<>(System.err,x->"");
 *      :
 *      :
 * return pf.start().end(x());
 * @author masao
 */
public class Performance<T> {
    PrintStream pstr;
    Function<? super T,String> monitor;
    long start;

    public Performance(PrintStream pstr, Function<? super T,String> monitor) {
        this.pstr = pstr;
        this.monitor = monitor;
        this.start = 0;
    }
    public Performance(PrintStream pstr) {
        this(pstr, x->"");
    }
    public Performance<T> start() {
        pstr.println("performance check start");
        start = System.currentTimeMillis();
        return this;
    }
    public T end(T value) {
        pstr.println("performance check end:"+monitor.apply(value)+"("+(System.currentTimeMillis()-start)+")");
        return value;
    }
}
