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

package collection;

import static iterator.Iterators.toStream;
import java.util.*;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

/**
 * converter between array, list, and iterator
 * @author mtomono
 */
public class c {
    public static <T> List<T> i2l(Iterator<T> i) {
        ArrayList<T> retval = new ArrayList<>();
        while (i.hasNext()) {
            retval.add(i.next());
        }
        return retval;
    }
    public static <T> Iterator<T> l2i(List<T> l) {
        return l.iterator();
    }
    public static <T> List<T> a2l(T... a) {
        return Arrays.<T>asList(a);
    }
    public static <T> List<T> a2al(T... a) {
        return new ArrayList<>(a2l(a));
    }
    public static <T> Object[] l2a(List<T> l) {
        return l.toArray();
    }
    public static <T> Object[] i2a(Iterator<T> i) {
        return c.<T>l2a(c.<T>i2l(i));
    }
    public static <T> Iterator<T> a2i(T... a) {
        return c.<T>l2i(c.<T>a2l(a));
    }
    
    public static int[] l2aInt(List<Integer> l) {
        int[] retval = new int[l.size()];
        Iterator<Integer> iter = l.iterator();
        for (int i = 0; i < l.size(); i++) {
            retval[i] = iter.next();
        }
        return retval;
    }
    
    public static List<String> slist(String fixture) {
        return slist(fixture, s->s);
    }
    
    public static <T> List<T> slist(String fixture, Function<String, T> parser) {
        return toStream(new Scanner(fixture)).map(parser).collect(toList());
    }

}
