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

package function;

import java.util.Comparator;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public interface ComparePolicy {
    public static <T, S extends Comparable<? super S>> Comparator<T> inc(Function<T, S> func) {
        return (a,b)->func.apply(a).compareTo(func.apply(b));
    } 

    public static <T, S extends Comparable<? super S>> Comparator<T> dec(Function<T, S> func) {
        return (a,b)->func.apply(b).compareTo(func.apply(a));
    } 
    
    public static <K extends Comparable<? super K>> boolean equalsByComparison(K th, Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Comparable)) {
            return false;
        }
        K t = (K) e;
        return th.compareTo(t)==0;
    }
}
