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

import java.util.Arrays;
import java.util.function.Function;

/**
 *
 * @author mtomono
 */
public interface Trace {
    public static Trace nul = new NulTrace();
    public static Trace def = new SimpleTrace();
    static public Function<String, String> s(Object... obj) {
        return l->l + Arrays.asList(obj).stream().reduce("", (a, b)->a + b.toString());
    }
    static public Function<String, String> clazz(Object obj) {
        return l->l + obj.getClass().getSimpleName();
    }
    
    default void pd(Function<String, String>... log) {
        assert d(log);
    }
    
    default void pl(Function<String, String>... log) {
        assert l(log);
    }
    
    default void pu(Function<String, String>... log) {
        assert u(log);
    }

    boolean d(Function<String, String>... log);

    boolean l(Function<String, String>... log);

    boolean u(Function<String, String>... log);
    
    boolean cr();
    
}
