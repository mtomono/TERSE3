/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import debug.Te;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

/**
 *
 * @author masao
 */
public interface ArrayInt {
    static public ArrayInt arrayInt(int... body) {
        return new Plain(body);
    }
    public int get(int i);
    public int set(int i, int v);
    public int length();
    default IntUnaryOperator asMap() {
        return i->get(i);
    }
    default ArrayInt index() {
        return new Index(length());
    }
    default PrimitiveIterator.OfInt iterator() {
        return new IteratorInt(this);
    }
    default PrimitiveIterator.OfInt fiterator(IntPredicate pred) {
        return new FilterInt(this,pred);
    }
    default ArrayInt asArray(Function<ArrayInt,PrimitiveIterator.OfInt> f) {
        ArrayInt retval=new Plain(new int[length()]);
        PrimitiveIterator.OfInt iter=f.apply(this);
        int i=0;
        for (i=0;iter.hasNext();i++) retval.set(i,iter.nextInt());
        return retval.subArray(0, i);
    }
    default ArrayInt fix() {
        return asArray(a->a.iterator());
    }
    default TList<Integer> asList() {
        return TList.set(i->ArrayInt.this.get(i),length());
    }
    default ArrayInt subArray(int from, int to) {
        return new SubArray(this,from,to);
    }
    default ArrayInt seek(int seek) {
        return seek>0?subArray(seek,length()):subArray(0,length()+seek);
    }
    default ArrayInt map(IntUnaryOperator op) {
        return new Map(this,op);
    }
    default ArrayInt filter(IntPredicate pred) {
        return asArray(a->a.fiterator(pred));
    }
    default void forEach(IntConsumer c) {
        for(int i=0;i<length();i++) c.accept(i);
    }
    default ArrayInt swap(int i,int j) {
        set(j,set(i,get(j)));
        return this;
    }
    default int min(int from, int to, IntUnaryOperator op) {
        int min=op.applyAsInt(get(from));
        int retval=0;
        for (int i=from+1;i<to;i++) {
            if (op.applyAsInt(get(i))<min) {
                min=op.applyAsInt(get(i));
                retval=i;
            }
        }
        return retval;
    }
    default int min(int seek, IntUnaryOperator op) {
        return seek>0?min(seek,length(),op):min(0,length()+seek,op);
    }
    default int min(IntUnaryOperator op) {
        return min(0,length(),op);
    }
    
    static class Plain implements ArrayInt {
        int[] body;
        public Plain(int[] body) {
            this.body=body;
        }
        @Override
        public int get(int i) {
            return body[i];
        }
        @Override
        public int set(int i, int v) {
            int retval=get(i);
            body[i]=v;
            return retval;
        }
        @Override
        public int length() {
            return body.length;
        }
    }
    static class SubArray implements ArrayInt {
        ArrayInt body;
        int from;
        int to;
        int length;
        public SubArray(ArrayInt body, int from, int to) {
            this.body=body;
            this.from=from;
            this.to=to;
            this.length=to-from;
        }
        @Override
        public int get(int i) {
            return body.get(from+i);
        }
        @Override
        public int set(int i, int v) {
            return body.set(from+i, v);
        }
        @Override
        public int length() {
            return length;
        }
    }
    static class Map implements ArrayInt {
        ArrayInt body;
        IntUnaryOperator map;
        public Map(ArrayInt body,IntUnaryOperator map) {
            this.body=body;
            this.map=map;
        }
        @Override
        public int get(int i) {
            return map.applyAsInt(body.get(i));
        }
        @Override
        public int set(int i, int v) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public int length() {
            return body.length();
        }
    }
    static class Index implements ArrayInt {
        int length;
        public Index(int length) {
            this.length=length;
        }
        @Override
        public int get(int i) {
            return i;
        }
        @Override
        public int set(int i, int v) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public int length() {
            return length;
        }
    }
    static class IteratorInt implements PrimitiveIterator.OfInt {
        int i;
        ArrayInt body;
        public IteratorInt(ArrayInt body) {
            this.i=0;
            this.body=body;
        }
        @Override
        public int nextInt() {
            return body.get(i++);
        }

        @Override
        public boolean hasNext() {
            return i<body.length();
        }
    }
    static class FilterInt implements PrimitiveIterator.OfInt {
        int i;
        ArrayInt body;
        IntPredicate pred;
        public FilterInt(ArrayInt body,IntPredicate pred) {
            this.i=0;
            this.body=body;
            this.pred=pred;
        }
        @Override
        public int nextInt() {
            if (hasNext())
                return body.get(i++);
            else
                throw new NoSuchElementException("ArrayInt.FilterInt failed");
        }
        @Override
        public boolean hasNext() {
            while(i<body.length()) {
                if (pred.test(body.get(i))) return true;
                i++;
            }
            return false;
        }
        
    }
}
