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
 * placeholder for Performance.
 * some methods are critical for performance. thus, it's wise to prepare a Performance object
 * around that method. but performance check is taken in occasional manner. to keep the deployment of 
 * Performance objects, constructor of this class is made to be compatible with Performance, but at the 
 * same time, this class does nothing inside.
 * @author masao
 */
public class PerformanceNull<T> extends Performance<T> {
    public PerformanceNull(PrintStream pstr, Function<? super T, String> monitor) {
        super(pstr, monitor);
    }
    
    @Override
    public Performance<T> start() {
        return this;
    }
    
    @Override
    public T end(T value) {
        return value;
    }
}
