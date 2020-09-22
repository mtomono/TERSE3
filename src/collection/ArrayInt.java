/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.ArrayInt.ArrayIntIterator.concat;
import static collection.ArrayInt.ArrayIntIterator.one;
import function.IntBiFunction;
import function.IntBiPredicate;
import static function.IntBiPredicate.gt;
import static function.IntBiPredicate.lt;
import iterator.Iterators;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

/**
 *
 * @author masao
 */
public interface ArrayInt {
    static public ArrayInt arrayInt(int... body) {
        return new Plain(body);
    }
    static public ArrayInt empty() {
        return new Plain(new int[0]);
    }
    static public ArrayInt range(int from, int to) {
        return new Range(from,to);
    }
    static public ArrayInt set(TList<Integer> body) {
        return set(body,i->i);
    }
    static public <T> ArrayInt set(TList<T>body, ToIntFunction<T> map) {
        return ArrayIntIterator.fromList(body, map).asArray();
    }
    public int get(int i);
    public int set(int i, int v);
    public int length();
    default int last() {
        return get(length()-1);
    }
    default int last(int i) {
        return get(length()-1-i);
    }
    default ArrayInt cset(int i, int v) {
        set(i,v);
        return this;
    }
    default ArrayInt reset(ArrayInt other) {
        int upto=Integer.min(length(),other.length());
        for (int i=0;i<upto;i++) set(i,other.get(i));
        return this;
    }
    default ArrayInt reset(ArrayInt other,int seek) {
        this.seek(seek).reset(other); 
        return this;
    }
    default ArrayInt setAll(int v) {
        for (int i=0;i<length();i++) set(i,v);
        return this;
    }
    default IntUnaryOperator asMap() {
        return i->get(i);
    }
    default ArrayInt index() {
        return new Range(0,length());
    }
    default ArrayIntIterator iterator() {
        return new PlainIterator(this);
    }
    default ArrayIntIterator filterIter(IntPredicate pred) {
        return new FilterIterator(this,pred);
    }
    default ArrayIntIterator accumWithSeedIter(int start, IntBinaryOperator op) {
        return new AccumIterator(start,this,op);
    }
    default ArrayIntIterator accumIter(int start, IntBinaryOperator op) {
        return one(start).append(accumWithSeedIter(start,op));
    }
    default ArrayIntIterator accumFromStartIter(IntUnaryOperator start, IntBinaryOperator op) {
        if (length()==0) return ArrayIntIterator.empty();
        return seek(1).accumIter(start.applyAsInt(get(0)), op);
    }
    static public ArrayIntIterator concatIter(ArrayIntIterator... body) {
        return new ConcatIterator(body);
    }
    default IntStream stream() {
        return Iterators.toIntStream(iterator());
    }
    default ArrayInt fix() {
        return iterator().asArray();
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
    default ArrayInt reverse() {
        return new Reverse(this);
    }
    default int sum() {
        return stream().sum();
    }
    default ArrayInt pair(ArrayInt other, IntBinaryOperator op) {
        return range(0,Integer.min(length(),other.length())).map(i->op.applyAsInt(get(i), other.get(i)));
    }
    default <T> TList<T> pairToObj(ArrayInt other, IntBiFunction<T> op) {
        return range(0,Integer.min(length(),other.length())).mapToObj(i->op.apply(get(i), other.get(i)));
    }
    default ArrayInt diff(int seek,IntBinaryOperator op) {
        return pair(seek(seek),op);
    }
    default ArrayInt diff(IntBinaryOperator op) {
        return diff(1,op);
    }
    default <T> TList<T> diffToObj(int seek,IntBiFunction<T> op) {
        return pairToObj(seek(seek),op);
    }
    default <T> TList<T> diffToObj(IntBiFunction<T> op) {
        return diffToObj(1,op);
    }
    default TList<ArrayInt> reverseChunk(IntPredicate pred) {
        ArrayInt divides=concat(one(0),index().filterIter(i->pred.test(get(i))),one(length())).asArray();
        return divides.diffToObj((a,b)->subArray(a,b));
    }
    default ArrayInt map(IntUnaryOperator op) {
        return new Map(this,op);
    }
    default <T> TList<T> mapToObj(IntFunction<T> f) {
        return TList.set(new AbstractList<T>() {
            @Override
            public T get(int index) {
                return f.apply(ArrayInt.this.get(index));
            }
            @Override
            public int size() {
                return length();
            }
        });
    }
    default ArrayInt accumWithSeed(int start, IntBinaryOperator op) {
        return accumWithSeedIter(start,op).asArray();
    }
    default ArrayInt accum(int start, IntBinaryOperator op) {
        return accumIter(start,op).asArray();
    }
    default ArrayInt accumFromStart(IntUnaryOperator start, IntBinaryOperator op) {
        return accumFromStartIter(start,op).asArray();
    }
    default ArrayInt filter(IntPredicate pred) {
        return filterIter(pred).asArray();
    }
    default boolean forAll(IntPredicate pred) {
        for (int i=0;i<length();i++)
            if (!pred.test(i)) return false;
        return true;
    }
    default boolean isUniform() {
        return diff((a,b)->b-a).sum()==0;
    }
    default boolean isAscending() {
        return diff((a,b)->b-a).forAll(i->i>0);
    }
    default boolean isDescending() {
        return diff((a,b)->b-a).forAll(i->i<0);
    }
    default boolean isAscendingOrEqual() {
        return diff((a,b)->b-a).forAll(i->i>=0);
    }
    default boolean isDescendingOrEqual() {
        return diff((a,b)->b-a).forAll(i->i<=0);
    }
    default boolean exists(IntPredicate pred) {
        for (int i=0;i<length();i++)
            if (pred.test(i)) return true;
        return false;
    }
    default void forEach(IntConsumer c) {
        for(int i=0;i<length();i++) c.accept(get(i));
    }
    default ArrayInt swap(int i,int j) {
        set(j,set(i,get(j)));
        return this;
    }
    default boolean containsIndex(int index) {
        return 0<=index&&index<length();
    }
    default int min(IntUnaryOperator op) {
        return index().iterator().min(i->op.applyAsInt(get(i)));
    }
    default int max(IntUnaryOperator op) {
        return index().iterator().max(i->op.applyAsInt(get(i)));
    }
    default ArrayInt tee(IntConsumer c) {
        return map(i->{
            c.accept(i);
            return i;
        });
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
    static class Reverse implements ArrayInt {
        ArrayInt body;
        int length;
        public Reverse(ArrayInt body) {
            this.body=body;
            this.length=body.length();
        }
        @Override
        public int get(int i) {
            return body.get(length-1-i);
        }
        @Override
        public int set(int i, int v) {
            return body.set(length-1-i, v);
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
    static class Range implements ArrayInt {
        int from;
        int length;
        public Range(int from, int to) {
            this.from=from;
            this.length=to-from;
        }
        @Override
        public int get(int i) {
            return i+from;
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
    public static interface ArrayIntIterator extends PrimitiveIterator.OfInt {
        int maxSize();
        public static <T> ArrayIntIterator fromList(TList<T> list, ToIntFunction<T> f) {
            return new ObjWrap<>(list,f);
        }
        public static ArrayIntIterator concat(TList<ArrayIntIterator> iters) {
            return new ConcatIterator(iters);
        }
        public static ArrayIntIterator concat(ArrayIntIterator... iters) {
            return new ConcatIterator(iters);
        }
        public static ArrayIntIterator one(int v) {
            return new OneWrap(v);
        }
        public static ArrayIntIterator empty() {
            return new EmptyIterator();
        }
        default int sum() {
            int retval=0;
            while(hasNext()) retval+=nextInt();
            return retval;
        }
        default ArrayInt asArray() {
            ArrayInt retval=new Plain(new int[maxSize()]);
            int i=0;
            for (i=0;hasNext();i++) retval.set(i,nextInt());
            return retval.subArray(0, i);
        }
        default ArrayIntIterator map(IntUnaryOperator op) {
            return new MapWrap(this,op);
        }
        default ArrayIntIterator filter(IntPredicate pred) {
            return new FilterWrap(this,pred);
        }
        default ArrayIntIterator append(ArrayIntIterator... iters) {
            return new ConcatIterator(TList.sof(iters).startFrom(this).sfix());
        }
        default int min(IntUnaryOperator op) {
            return minmax(op,lt);
        }
        default int max(IntUnaryOperator op) {
            return minmax(op,gt);
        }
        default int minmax(IntUnaryOperator op, IntBiPredicate comp) {
            int x=nextInt();
            int y=op.applyAsInt(x);
            int retval=x;
            int value=y;
            while (hasNext()) {
                x=nextInt();
                y=op.applyAsInt(x);
                if(comp.test(y,value)) {
                    retval=x;
                    value=y;
                }
            }
            return retval;
        }
        default OptionalInt check(ToIntFunction<ArrayIntIterator> s) {
            return hasNext()?OptionalInt.of(s.applyAsInt(this)):OptionalInt.empty();
        }
    }
    static class EmptyIterator implements ArrayIntIterator {
        @Override
        public int maxSize() {
            return 0;
        }
        @Override
        public int nextInt() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public boolean hasNext() {
            return false;
        }
    }
    static class PlainIterator implements ArrayIntIterator {
        int i;
        ArrayInt body;
        public PlainIterator(ArrayInt body) {
            this.i=0;
            this.body=body;
        }
        @Override
        public int maxSize() {
            return body.length();
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
    static class FilterIterator extends FilterAtIterator {
        public FilterIterator(ArrayInt body,IntPredicate pred) {
            super(body,pred);
        }
        @Override
        public int nextInt() {
            return body.get(super.nextInt());
        }
    }
    static class FilterAtIterator implements ArrayIntIterator {
        int i;
        ArrayInt body;
        IntPredicate pred;
        public FilterAtIterator(ArrayInt body,IntPredicate pred) {
            this.i=0;
            this.body=body;
            this.pred=pred;
        }
        @Override
        public int maxSize() {
            return body.length();
        }
        @Override
        public int nextInt() {
            if (hasNext())
                return i++;
            else
                throw new NoSuchElementException("ArrayInt.FilterAtInt failed");
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
    static class AccumIterator implements ArrayIntIterator {
        int i;
        ArrayInt body;
        IntBinaryOperator op;
        int pre;
        public AccumIterator(int start, ArrayInt body, IntBinaryOperator op) {
            this.body=body;
            this.op=op;
            this.i=0;
            this.pre=start;
        }
        @Override
        public int maxSize() {
            return body.length();
        }
        @Override
        public int nextInt() {
            if (!hasNext())
                throw new NoSuchElementException("ArrayInt.AccumInt failed");
            pre=op.applyAsInt(pre,body.get(i++));
            return pre;
        }

        @Override
        public boolean hasNext() {
            return i<body.length();
        }
    }

    public class ConcatIterator implements ArrayIntIterator {
        TList<ArrayIntIterator> iters;
        int maxSize;
        int current;
        public ConcatIterator(TList<ArrayIntIterator> iters) {
            this.iters=iters;
            this.current=0;
            this.maxSize=new ObjWrap<>(iters,i->i.maxSize()).sum();
        }
        public ConcatIterator(ArrayIntIterator... iters) {
            this(TList.sof(iters));
        }
        @Override
        public int maxSize() {
            return maxSize;
        }
        @Override
        public int nextInt() {
            if (!hasNext())
                throw new NoSuchElementException("ArrayInt.AccumInt failed");
            return iters.get(current).nextInt();
        }
        @Override
        public boolean hasNext() {
            for (;current<iters.size();current++)
                if (iters.get(current).hasNext())
                    return true;
            return false;
        }
    }
    public class OneWrap implements ArrayIntIterator {
        int v;
        boolean hasNext;
        public OneWrap(int v) {
            this.v=v;
            this.hasNext=true;
        }
        @Override
        public int maxSize() {
            return 1;
        }
        @Override
        public int nextInt() {
            if (!hasNext)
                throw new NoSuchElementException("ArrayInt.OneWrap failed");
            this.hasNext=false;
            return v;
        }
        @Override
        public boolean hasNext() {
            return hasNext;
        }
    }
    public class MapWrap implements ArrayIntIterator {
        ArrayIntIterator body;
        IntUnaryOperator op;
        public MapWrap(ArrayIntIterator body,IntUnaryOperator op) {
            this.body=body;
            this.op=op;
        }
        @Override
        public int maxSize() {
            return body.maxSize();
        }
        @Override
        public int nextInt() {
            return op.applyAsInt(body.next());
        }
        @Override
        public boolean hasNext() {
            return body.hasNext();
        }
    }

    class ObjWrap<T> implements ArrayIntIterator {
        Iterator<T> body;
        ToIntFunction<T> f;
        int maxSize;
        public ObjWrap(TList<T> body, ToIntFunction<T> f) {
            this.body=body.iterator();
            this.f=f;
            this.maxSize=body.size();
        }
        @Override
        public int maxSize() {
            return maxSize;
        }
        @Override
        public int nextInt() {
            return f.applyAsInt(body.next());
        }
        @Override
        public boolean hasNext() {
            return body.hasNext();
        }
    }
    abstract public class AbstractBufferedWrap implements ArrayIntIterator {
        int buffer;
        boolean has;
        abstract protected void findNext();
        public void nextFound(int buffered) {
            this.buffer = buffered;
            this.has = true;
        }
        public void prepare() {
            if (!has)
                findNext();
        }
        @Override
        public boolean hasNext() {
            prepare();
            return has;
        }
        @Override
        public int nextInt() {
            prepare();
            if (!has) 
                throw new NoSuchElementException("AbstractBufferdWrap.nextInt() : gone beyond boundary (actual class is " + this.getClass().getName() + ")");
            has = false;
            return buffer;
        }
    }
    class FilterWrap extends AbstractBufferedWrap {
        ArrayIntIterator body;
        IntPredicate pred;
        public FilterWrap(ArrayIntIterator body, IntPredicate pred) {
            this.body=body;
            this.pred=pred;
        }
        @Override
        protected void findNext() {
            while (body.hasNext()) {
                int i=body.nextInt();
                if (pred.test(i))
                    nextFound(i);
            }
        }
        @Override
        public int maxSize() {
            return body.maxSize();
        }
    }
}
