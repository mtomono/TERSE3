/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package orderedSet.primitive;

/**
 *
 * @author masao
 */
public class Or<T> implements TRange<T> {
    TRange<T> a;
    TRange<T> b;
    public Or(TRange<T> a, TRange<T> b) {
        this.a=a;
        this.b=b;
    }
    @Override
    public T representative() {
        return a.representative();
    }
    public TRangeBuilder<T> builder() {
        return a.builder();
    }
    @Override
    public boolean contains(T v) {
        return a.contains(v)||b.contains(v);
    }
    public boolean contains(TRange<T> other) {
        return a.contains(other)||b.contains(other);
    }
    public boolean overlaps(TRange<T> other) {
        return a.overlaps(other)||b.overlaps(other);
    }
    @Override
    public TRange<T> negate() {
        return builder().and(a.negate(),b.negate());
    }
    @Override
    public String toString() {
        return "("+a+"||"+b+")";
    }
}
