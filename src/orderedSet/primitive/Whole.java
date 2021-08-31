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
 * @param <T>
 */
public class Whole<T> implements TRange<T> {
    final public TRangeBuilder<T> builder;
    public Whole(TRangeBuilder<T> builder) {
        this.builder=builder;
    }
    @Override
    public T representative() {
        throw new UnsupportedOperationException("whole doesn't have representative value");
    }
    @Override
    public TRangeBuilder<T> builder() {
        return builder;
    }
    @Override
    public boolean contains(T v) {
        return true;
    }
    @Override
    public boolean contains(TRange<T> other) {
        return true;
    }
    @Override
    public boolean overlaps(TRange<T> other) {
        return !(other instanceof None);
    }
    @Override
    public TRange<T> negate() {
        return builder.none();
    }
    @Override
    public String toString() {
        return "whole";
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Whole)) {
            return false;
        }
        return true;
    }
}
