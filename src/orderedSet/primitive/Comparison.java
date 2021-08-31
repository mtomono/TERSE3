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
abstract public class Comparison<T> implements TRange<T> {
    final public TRangeBuilder<T> builder;
    final public T base;
    public Comparison(T v, TRangeBuilder<T> builder) {
        this.base=v;
        this.builder=builder;
    }
    enum GL{
        G,L;
    }
    abstract GL dir();
    @Override
    public T representative() {
        return base;
    }
    @Override
    public TRangeBuilder<T> builder() {
        return builder;
    }
    public int compare(T v) {
        return builder.comparator.compare(v, this.base);
    }
    boolean containsBase(Comparison<T> c) {
        return dir().equals(c.dir())&&(equals(c)?true:contains(c.base));
    }
    boolean overlapsBase(Comparison<T> c) {
        return !((Comparison<T>)c.negate()).containsBase(this);
    }
    @Override
    public boolean contains(TRange<T> other) {
        return !other.overlaps(negate());
    }
    @Override
    public boolean overlaps(TRange<T> other) {
        if (other instanceof Comparison<T> c) return overlapsBase(c);
        return other.overlaps(this);
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!getClass().equals(e.getClass())) {
            return false;
        }
        Comparison<T> t = (Comparison<T>) e;
        return compare(t.base)==0;
    }
    static public class GE<T> extends Comparison<T> {
        public GE(T v, TRangeBuilder<T> builder) {
            super(v,builder);
        }
        @Override GL dir() {
            return GL.G;
        }
        @Override public boolean contains(T v) {
            return compare(v)>=0;
        }
        @Override public Comparison<T> negate() {
            return builder.lt(base);
        }
        @Override public String toString() {
            return base.toString()+"<=x";
        }                
    }
    static public class LT<T> extends Comparison<T> {
        public LT(T v, TRangeBuilder<T> builder) {
            super(v,builder);
        }
        @Override GL dir() {
            return GL.L;
        }
        @Override public boolean contains(T v) {
            return compare(v)<0;
        }
        @Override public Comparison<T> negate() {
            return builder.ge(base);
        }
        @Override public String toString() {
            return "x<"+base;
        }
    }
    static public class GT<T> extends Comparison<T> {
        public GT(T v, TRangeBuilder<T> builder) {
            super(v,builder);
        }
        @Override GL dir() {
            return GL.G;
        }
        @Override public boolean contains(T v) {
            return compare(v)>0;
        }
        @Override public Comparison<T> negate() {
            return builder.le(base);
        }
        @Override public String toString() {
            return base.toString()+"<x";
        }
    }
    static public class LE<T> extends Comparison<T> {
        public LE(T v, TRangeBuilder<T> builder) {
            super(v,builder);
        }
        @Override GL dir() {
            return GL.L;
        }
        @Override public boolean contains(T v) {
            return compare(v)<=0;
        }
       @Override public Comparison<T> negate() {
            return builder.gt(base);
        }
        @Override public String toString() {
            return "x<="+base;
        }
    }

}
