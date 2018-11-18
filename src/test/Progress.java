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
import java.util.function.Consumer;

/**
 * show progress of a process dealt with TList.
 * this object is supposed to be passed to TList#tee().
 * used this way.
 * TList<X> l;
 *  :
 * Progress px = new Progress(System.err, l.size());
 *  :
 * Y yy = l.tee(px).map(....
 * @author masao
 */
public class Progress implements Consumer<Object>{
    PrintStream pstr;
    final public int whole;
    int skip;
    int count;
    long start;
    public Progress(PrintStream pstr, int whole, int skip) {
        this.pstr = pstr;
        this.whole = whole;
        this.skip = skip;
        this.count = 0;
        this.start = System.currentTimeMillis();
    }
    public Progress(PrintStream pstr, int whole) {
        this(pstr,whole,1);
        pstr.println("progress meter start");
    }
    @Override
    public void accept(Object t) {
        count++;
        long current = System.currentTimeMillis();
        long lap = current-start;
        start = current;
        if (count%skip==0)
            pstr.println("progress meter reached:"+count+"/"+whole+"("+lap+")");
    }
}
