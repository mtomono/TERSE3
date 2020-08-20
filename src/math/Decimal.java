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
package math;

import orderedSet.Range;

/**
 *
 * @author masao
 * @param <K>
 */
public interface Decimal<K extends Decimal<K>> extends Field<K>,Comparable<K> {
    K negate();
    K abs();
    K mul(int v);
    K mul(long v);
    K div(int v);
    K div(long v);
    boolean isInteger();
    int intPart();
    long longPart();
    DecimalField<K> builder();
    default boolean same(K v) {
        return compareTo(v)==0;
    }
    default K inv() {
        return one().div((K)this);
    }
    default boolean lt(K v) {
        return compareTo(v)<0;
    }
    default boolean gt(K v) {
        return compareTo(v)>0;
    }
    default boolean isZero() {
        return same(zero());
    }
    default boolean isOne() {
        return same(one());
    }
    default boolean belowZero() {
        return lt(zero());
    }
    default boolean aboveZero() {
        return gt(zero());
    }
    
    public static <K extends Decimal<K>> K width(Range<K> range) {
        return range.end.sub(range.start);
    }
        
}
