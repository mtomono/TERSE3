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

package iterator;

import collection.OneTimeIterable;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author mtomono
 */
public class Iterators {
    public static <T> Stream<T> toStream(Iterator<T> iter) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
    }
    
    public static <T> LongStream toLongStream(PrimitiveIterator.OfLong iter) {
        return StreamSupport.longStream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
    }
    
    public static <T> IntStream toIntStream(PrimitiveIterator.OfInt iter) {
        return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
    }
    
    public static <T> DoubleStream toDoubleStream(PrimitiveIterator.OfDouble iter) {
        return StreamSupport.doubleStream(Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED), false);
    }
    
    public static <T> boolean test(Iterator<T> expected, Iterator<T> tested) {
        return toStream(tested).allMatch(n->expected.hasNext() && n.equals(expected.next())) && !expected.hasNext();
    }
    
    public static <T> ListIterator<T> buffer(Iterator<T> body, int depth) {
        return new RingBufferedListIterator<>(body, 2);
    }
    
    public static int count(Iterator<?> iter) {
        int retval = 0;
        for (Object x : new OneTimeIterable(iter))
            retval++;
        return retval;
    }
    
    public static void wind(Iterator<?> iter, int count) {
        for (int i = 0; i < count; i++)
            iter.next();
    }
    
    
}
