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

import static collection.c.a2l;
import static collection.c.l2aInt;
import static function.ComparePolicy.inc;
import iterator.IteratorCache;
import iterator.RotateListIterator;
import iterator.TIterator;
import iterator.TListIterator;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class TList<T> extends TListWrapper<T> {
    
    public TList(List<T> body) {
        super(body);
    }
    
    public T last() {
        return last(0);
    }
    
    public T last(int n) {
        return get(size()-1-n);
    }
    
    public T setLast(int n, T o) {
        return set(size()-1-n, o);
    }
    
    public TList<T> addOne(T added) {
        this.add(added);
        return this;
    }
    
    public TList<T> fix() {
        return new TListRandom<>(new ArrayList<>(this));
    }
    
    public TList<T> fixDebug(int interval) {
        List<T> retval = new ArrayList<>();
        Iterator<T> iter = iterator();
        int count = 0;
        while (iter.hasNext()) {
            System.out.println("next");
            for (int i=0; i<interval; i++) {
                if (!iter.hasNext())
                    break;
                retval.add(iter.next());
            }
            System.out.println(count += interval);
        }
        System.out.println("finished");
        return new TListRandom<>(retval);
    }
    
    public TList<T> fixDebug() {
        return fixDebug(1000);
    }
    
    public TList<T> fix2seq() {
        return new TList<>(new LinkedList<>(this));
    }
    
    public TList<T> tee(Consumer<T> c) {
        return map(t->{
            c.accept(t);
            return t;
        });
    }
    
    public TList<T> accept(Consumer<TList<T>> c) {
        c.accept(this);
        return this;
    }
    
    public TList<T> cache() {
        return set(new CacheList(this));
    }

//-----------Generating
    
    static public <T> TList<T> wrap(T target) {
        return set(Collections.singletonList(target));
    }
    
    static TList<Integer> rangeBase(int from, int to) {
        assert from <= to;
        return set(new ShiftedScale(from)).subList(0, to-from);
    }
    
    static TList<Integer> rangeBaseSym(int from, int to) {
        assert from <= to+1 : "from:"+from+" to:"+to;
        return rangeBase(from, to+1);
    }
    
    static public TList<Integer> range(int from, int to) {
        if (from <= to)
            return rangeBase(from, to);
        else
            return rangeBase(to, from).reverse();
    }
    
    static public TList<Integer> rangeSym(int from, int to) {
        if (from <= to)
            return rangeBaseSym(from, to);
        else
            return rangeBaseSym(to, from).reverse();
    }
    
    static public TList<Integer> range(Range<Integer> range) {
        return rangeBase(range.start(), range.end());
    }
    
    static public <T> TList<T> empty() {
        return new TList<>(Collections.emptyList());
    }
    
    static public <T> TList<T> set(List<T> body) {
        return (body instanceof RandomAccess) ? new TListRandom<>(body) : new TList<>(body);
    }
    
    static public <T> TList<T> set(Collection<T> body) {
        return set(new ArrayList<>(body));
    }
    
    static public <T> TList<T> c() {
        return new TListRandom<>(new ArrayList<>());
    }
    
    static public <T> TList<T> of(T... t) {
        return new TListRandom<>(new ArrayList<>(a2l(t)));
    }
    
    static public <T> TList<T> ofStatic(T... t) {
        return new TListRandom<>(a2l(t));
    }
    
//--------- Judging
    
    public boolean isUniform() {
        return distinctLocally().isEmpty();
    }
        
    public boolean isAscending(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d>=0).isEmpty();
    }
    
    public boolean isDescending(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d<=0).isEmpty();
    }
    
    public boolean isAscendingOrEqual(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d>0).isEmpty();
    }
    
    public boolean isDescendingOrEqual(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d<0).isEmpty();
    }
    
    public boolean forAll(Predicate<T> pred) {
        return filter(pred.negate()).isEmpty();
    }
    
    public boolean exists(Predicate<T> pred) {
        return !filter(pred).isEmpty();
    }
    
//--------- Transforming
    
    public <S> S transform(Function<TList<T>, S> map) {
        return map.apply(this);
    }
    
    public <S> TList<S> map(Function<T, S> map, Function<S, T> rmap) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new MapRandomList<T, S>(this, map, rmap)) : new TList<>(new MapSequentialList<>(this, map, rmap));
    }
    
    public <S> TList<S> map(Function<T, S> map) {
        return map(map, e->{throw new RuntimeException("NoReach : ");});
    }
    
    public <S> TList<S> mapc(Function<T, S> map) {
        return map(map).cache();
    }
    
    public <S> TList<S> flatMap(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ListRandomList<>(map(map))) : new TList<>(new ListSequentialList<>(map(map)));
    }
    
    public <S> TList<S> flatMapc(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ListRandomList<>(mapc(map))) : new TList<>(new ListSequentialList<>(mapc(map)));
    }
    
    public <S> TList<List<S>> transpose(Function<T, List<S>> map) {
        return new TListRandom<>(new TransposeList<>(map(map)));
    }
    
    public <S> TList<TList<S>> transposeT(Function<T, List<S>> map) {
        return transpose(map).map(l->TList.set(l));
    }
    
    public <S> TList<S> heap(S start, BiFunction<T, S, S> map) {
        return set(new IteratorCache<>(TIterator.set(iterator()).heap(start, map)));
    }
    
    /**
     * heap which takes first item of the list as start
     * @param <S>
     * @param first
     * @param map
     * @return 
     */
    public <S> TList<S> preheap(Function<T, S> first, BiFunction<T, S, S> map) {
        return subList(1, size()).heap(first.apply(get(0)), map);
    }
    
    public TList<T> preheap(BiFunction<T, T, T> map) {
        return subList(1, size()).heap(get(0), map);
    }
    
//-----------Calculating
    
    public double averageD(ToDoubleFunction<T> f) {
        return stream().mapToDouble(f).average().orElse(0);
    }

    public double averageL(ToLongFunction<T> f) {
        return stream().mapToLong(f).average().orElse(0);
    }

    public double averageI(ToIntFunction<T> f) {
        return stream().mapToInt(f).average().orElse(0);
    }

    public double sumD(ToDoubleFunction<T> f) {
        return stream().mapToDouble(f).sum();
    }
    
    public long sumL(ToLongFunction<T> f) {
        return stream().mapToLong(f).sum();
    }
    
    public int sumI(ToIntFunction<T> f) {
        return stream().mapToInt(f).sum();
    }
    
//---------- Filtering
    
    public int indexOf(Predicate<T> cond) {
        return TList.range(0, size()).filter(i->cond.test(get(i))).getOpt(0).orElse(-1);
    }
    
    public TList<T> subListAnyway(int from, int to) {
        if (from >= to)
            return TList.empty();
        if (from >= size())
            return TList.empty();
        if (to > size())
            return subList(from, size());
        return subList(from, to);
    }
    
    public Optional<T> getOpt(int index) {
        if (body instanceof RandomAccess)
            return index<size()?Optional.of(get(index)):Optional.empty();
        int number = -1;
        T retval=null;
        for (Iterator<T> iter = iterator(); number<index&&iter.hasNext(); retval=iter.next())
            number++;
        return number==index?Optional.of(retval):Optional.empty();
    }
    
    public Optional<T> lastOpt(int index) {
        return index<size()?Optional.of(last(index)):Optional.empty();
    }
    
    public TList<T> filter(Predicate<T> cond) {
        return new TList<>(new FilterList<>(this, cond));
    }
    
    /**
     * remove all the equivalent item only in vicinity.
     * when the list is sorted in the order which reflects the equivalency, this is 
     * good enough to leave only distinct elements.
     * @return 
     */
    public TList<T> distinctLocally() {
        return diff().filter(p->!p.l().equals(p.r())).transform(dediff());
    }
    
    /**
     * remove all the equivalent item in terms of Comparator only in vicinity.
     * when the list is sorted in the order of given comparator c, this is 
     * good enough to leave only distinct elements.
     * @param c 
     * @return 
     */
   public TList<T> distinctLocally(Comparator<T> c) {
        return diff().filter(p->c.compare(p.l(), p.r())!=0).transform(dediff());
    }
    
    public static BitSet toMask(int size, List<Integer> indices) {
        return toMask(size, l2aInt(indices));
    }
    
    public static BitSet toMask(int size, int[] indices) {
        BitSet retval = new BitSet(size);
        for (int i : indices) {
            retval.set(i);
        }
        return retval;
    }
    
    public TList<T> show(List<Integer> indices) {
        return mask(toMask(this.size(), indices));
    }
    
    public TList<T> hide(List<Integer> indices) {
        BitSet mask = toMask(this.size(), indices);
        mask.flip(0, this.size());
        return mask(mask);
    }
        
    public TList<T> mask(BitSet mask) {
        return new TList<>(new MaskedList<>(this, mask));
    }
    
    public TList<T> pickUp(List<Integer> indices) {
        return set(indices).map(i->get(i));
    }

    public TList<TList<T>> startings() {
        return range(1, size()+1).map(end->subList(0, end));
    }
    
    public TList<TList<T>> endings() {
        return range(0, size()).map(start->subList(start, size()));
    }
    
//------- Ordering
    
    /**
     * Sort to new list.
     * Unlike the List#sort(), this method returns new list. Mainly because an instance
     * of TList is highly manipulated by its methods and sort method is unlikely to be 
     * applicable.
     * @param c
     * @return 
     */
    public TList<T> sortTo(Comparator<T> c) {
        List<T> retval = new ArrayList<>(this);
        retval.sort(c);
        return set(retval);
    }

    public Optional<T> min(Comparator<T> comp) {
        return stream().min(comp);
    }
    
    public <S extends Comparable<S>> Optional<T> min(Function<T, S> func) {
        return min(inc(func));
    }
    
    public Optional<T> max(Comparator<T> comp) {
        return stream().max(comp);
    }
    
    public <S extends Comparable<S>> Optional<T> max(Function<T, S> func) {
        return max(inc(func));
    }
    
    public TList<T> seek(int seek) {
        assert -size() <= seek && seek <= size();
        return seek>0?subList(seek, size()):subList(0, size()+seek);
    }

    public TList<T> reverse() {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ReverseRandomList<>(body)) : new TList<>(new ReverseSequentialList<>(body));
    }
    
    
    public TListIterator<T> repeat(int index) {
        return new TListIterator<>(new RotateListIterator<>(this, index));
    }
    
    public TListIterator<T> repeat() {
        return TList.this.repeat(0);
    }
    
    public TList<T> rotate(int x) {
        assert -size()<x && x<size();
        if (0<x)
            return TList.concat(subList(x, size()), subList(0, x));
        else
            return TList.concat(subList(size()+x, size()), subList(0, size()+x));
    }
    
    public TList<TList<T>> fold(int n) {
        return set(new FoldList<>(this,n));
    }
    
    public TList<TList<T>> divide(T division) {
        int index = indexOf(division);
        if (index == -1) 
            return TList.ofStatic(this);
        return TList.ofStatic(subList(0, indexOf(division) + 1), subList(indexOf(division) + 1, size()));
    }
    
    public TList<TList<T>> chunk(Predicate<T> pred) {
        TList<TList<T>> retval = TList.c();
        return chunk(retval, this, pred);
    }
        
    TList<TList<T>> chunk(TList<TList<T>> current, TList<T> remain, Predicate<T> pred) {
        TList<T> divides = remain.filter(pred);
        if (divides.isEmpty())
            return current.addOne(remain);
        TList<TList<T>> divided = remain.divide(divides.get(0));
        return chunk(current.addOne(divided.get(0)), divided.get(1), pred);
    }
    
//----------- Composing
    
    static public <T> TList<T> concat(List<T>... t) {
        return concat(new ArrayList<>(a2l(t)));
    }
    
    static public <T> TList<T> concat(List<List<T>> t) {
        return t.stream().allMatch(e->(e instanceof RandomAccess)) ? new TListRandom<>(new ListRandomList<>(t)) : new TList<>(new ListSequentialList<>(t));
    }
    
    public TList<T> append(List<T>... t) {
        return append(a2l(t));
    }
    
    public TList<T> append(List<List<T>> t) {
        List<List<T>> l = new ArrayList<>(a2l(this));
        l.addAll(t);
        return concat(l);
    }
    
    public TList<T> merge(List<T> merged, Comparator<T> c) {
        return set(new MergeList<>(this, merged, c));
    }
    
    public <S> TList<P<T, S>> pair(List<S> add) {
        return (body instanceof RandomAccess) && (add instanceof RandomAccess) ? new TListRandom<>(new ZipRandomList<>(body, add)) : new TList<>(new ZipSequentialList<>(body, add));
    }
    
    public <S, U> TList<U> pair(List<S> add, BiFunction<T, S, U> map) {
        return (body instanceof RandomAccess) && (add instanceof RandomAccess) ? new TListRandom<>(new PairRandomList<>(body, add, map)) : new TList<>(new PairSequentialList<>(body, add, map));
    }
    
    public TList<P<T, T>> w() {
        return pair(this);
    }
    
    public <S, U> TList<P<S, U>> w(Function<T, S>funcL, Function<T, U>funcR) {
        return map(funcL).pair(map(funcR));
    }
    
    public TList<P<T, T>> diff() {
        return diff(1);
    }
    
    public TList<P<T, T>> diff(int seek) {
        return pair(seek(seek));
    }
    
    public <U> TList<U> diff(BiFunction<T, T, U> map) {
        return diff(1, map);
    }
    
    public <U> TList<U> diff(int seek, BiFunction<T, T, U> map) {
        return pair(seek(seek), map);
    }
    
    static public <T> Function<TList<P<T, T>>, TList<T>> dediff() {
        return l->l.isEmpty()?TList.empty():concat(wrap(l.get(0).l()), l.map(p->p.r()));
    }

    public <S> TList<P<T, S>> cross(List<S> target) {
        return cross(target, (t,s)->P.p(t,s));
    }
    
    public <S, U> TList<U> cross(List<S> target, BiFunction<T, S, U> func) {
        return flatMap(t->set(target).map(s->func.apply(t, s)));
    }
    
    public <S> TList<S> weave(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) && map(map).stream().allMatch(l->l instanceof Random) ? new TListRandom<>(new WeaveRandomList<>(map(map))) : new TList<>(new WeaveSequentialList<>(map(map)));
    }
    
//--------- Positioning
    
    public <S> TOptionalList<S> optionalMap(Function<T, Optional<S>> map) {
        return new TOptionalList<>(map(map));
    }
    
    public <S> TOptionalList<S> optionalFlatMap(Function<T, List<Optional<S>>> map) {
        return new TOptionalList<>(flatMap(map));
    }
    
    public TOptionalList<T> show(Predicate<T> test) {
        return new TOptionalList<>(map(e->test.test(e) ? Optional.of(e) : Optional.empty()));
    }
    
    public TOptionalList<T> hide(Predicate<T> test) {
        return show(test.negate());
    }
    
    public TOptionalList<T> optional() {
        return new TOptionalList<>(map(e->Optional.of(e)));
    }
    
    public TOptionalList<T> fill(int at, int length) {
        return new TOptionalList<>(TList.concat(subList(0, at).optional(), new Filler<>(length), subList(at, size()).optional()));
    }
    
    public TOptionalList<T> shift(int shift) {
        return shift < 0 ? subList(-shift, size()).optional() : fill(0, shift);
    }
    
    public <S> TList<List<Optional<S>>> rectangle(Function<T, List<S>> map) {
        int length = maxLength(map);
        return map(map).map(l->set(l).fill(l.size(), length-l.size()));
    }
    
    public <S> TList<List<Optional<S>>> square(Function<T, List<S>> map) {
        int length = maxLength(map);
        return length > size() ? TList.concat(this.rectangle(map), Filler.filler(length, length-size())) : TList.concat(this.rectangle(map).transpose(l->l), Filler.filler(size(), size()-length)).transpose(l->l);
    }
    
    public <S> int maxLength(Function<T, List<S>> map) {
        return map(map).stream().mapToInt(l->l.size()).max().orElse(0);
    }
    
    //----------- combination theory
    
    static public <T> TList<T> nCopies(int n, T x) {
        return TList.set(Collections.nCopies(n, x));
    }
    
    static private TList<TList<Integer>> permulationx(int a, int b, TList<TList<Integer>> prev) {
        assert a>=0 && b>=0;
        TList<Integer> base = range(0, a);
        if (b == 0)
            return c();
        if (b == 1)
            return base.map(e->of(e));
        return prev.map(m->base.hide(m)).pair(prev).flatMap(p->p.l().map(i->TList.concat(p.r(), wrap(i))));
    }
    
    static private TList<TList<Integer>> permulationx(int a, int b) {
        if (b == 0)
            return permulationx(a, b, null);
        return permulationx(a, b, permulationx(a, b-1));
    }
    
    static private TList<TList<TList<Integer>>> permulationxUpTo(int a, int b) {
        TList<TList<TList<Integer>>> retval = c();
        retval.add(permulationx(a, 0, null));
        for (int i=1; i<=b; i++)
            retval.add(permulationx(a, i, retval.last()).fix());
        return retval;
    }
    
    static public TList<List<Integer>> permulation(int a, int b) {
        return permulationx(a, b).map(l->(List<Integer>)l);
    }

    public TList<List<T>> permulation(int a) {
        assert a >= 0;
        return permulation(size(), a).map(p->pickUp(p));
    }
    
    public TList<List<List<T>>> permulationUpTo(int a) {
        assert a >= 0;
        return permulationxUpTo(size(), a).map(l->l.map(p->pickUp(p)));
    }
    
    static private TList<TList<Integer>> combinationx(int a, int b, TList<TList<Integer>> prev) {
        assert a>=0 && b>=0;
        if (b == 0)
            return c();
        if (b == 1) 
            return range(0, a).map(e->of(e));
        return prev.map(m->range(m.last()+1, a)).pair(prev).flatMap(p->p.l().map(i->TList.concat(p.r(), wrap(i))));
    }
    
    static public TList<TList<Integer>> combinationT(int a, int b) {
        if (b == 0) 
            return combinationx(a, b, null);
        return combinationx(a, b, combinationT(a, b-1));
    }
    
    static private TList<TList<TList<Integer>>> combinationxUpTo(int a, int b) {
        TList<TList<TList<Integer>>> retval = c();
        retval.add(combinationT(a, 0));
        for (int i=1; i<=b; i++) 
            retval.add(combinationx(a, i, retval.last()).fix());
        return retval;
    }
    
    static public TList<List<Integer>> combination(int a, int b) {
        return combinationT(a, b).map(l->(List<Integer>)l);
    }
    
    public TList<List<T>> combination(int n) {
        return combination(size(), n).map(m->TList.this.show(m));
    }
    
    public TList<List<List<T>>> combinationUpTo(int a) {
        assert a >= 0;
        return combinationxUpTo(size(), a).map(l->l.map(p->pickUp(p)));
    }
    
    static public TList<Integer> paint(int size, List<Integer> border) {
        TList<Integer> retval = c();
        TList<Integer> lengths = TList.concat(wrap(-1), border, wrap(size + border.size())).diff().map(p->p.r()-p.l()-1);
        int color = 0;
        for (int l : lengths) {
            for(int i=0; i<l; i++) {
                retval.add(color);
            }
            color++;
        }
        return retval;
    }
    
    static public TList<TList<Integer>> homogeneousProductBorders(int a, int b) {
        return combinationT(a+b-1, b-1);
    }
    
    static private TList<TList<Integer>> homogeneousProductx(int a, int b) {
        return homogeneousProductBorders(a, b).map(border->paint(a, border));
    }
    
    static public TList<List<Integer>> homogeneousProduct(int a, int b) {
        return homogeneousProductx(a, b).map(l->(List<Integer>)l);
    }
    
    public TList<List<T>> homogeneousProduct(int a) {
        assert a >=0;
        return homogeneousProduct(a, size()).map(m->pickUp(m));
    }
    
    public int numberOfChanges(BiPredicate<T, T> equality) {
        return diff((a,b)->equality.negate().test(a, b)).filter(x->x).size();
    }
    
    public int numberOfChanges() {
        return numberOfChanges((a,b)->a.equals(b));
    }
        
    //------------ T series compatible wrapping

    @Override
    public TListIterator<T> listIterator() {
        return new TListIterator<>(body.listIterator());
    }

    @Override
    public TListIterator<T> listIterator(int index) {
        return new TListIterator<>(body.listIterator(index));
    }

    @Override
    public TIterator<T> iterator() {
        return new TIterator<>(body.iterator());
    }
    
    @Override
    public TList<T> subList(int fromIndex, int toIndex) {
        return new TList<>(body.subList(fromIndex, toIndex));
    }
    
    public TList<T> subList(Range<Integer> range) {
        return subList(range.start(), range.end());
    }
    
    //
    public String toWrappedString() {
        return toCatenatedString("\n");
    }
    
    public String toFlatString() {
        return toCatenatedString("");
    }
        
    public String toDelimitedString(String delimiter) {
        return toCatenatedString(delimiter);
    }
    
    public String toCatenatedString(String x) {
        if (isEmpty())
            return "";
        return subList(1, size()).map(o->o.toString()).heap(new StringBuilder(get(0).toString()), (String a, StringBuilder b)->b.append(x).append(a)).last().toString();
    }
        
    static public <T> Collector<T, ?, TList<T>> toTList() {
        return Collector.of((Supplier<List<T>>)ArrayList::new, List::add, (left,right)->{left.addAll(right);return left;}, TList::set);
    }
    
    public Object[][] dataProvider() {
        return map(e->lineOfData(e)).toArray(new Object[][]{});
    }
    
    Object[] lineOfData(Object e) {
        if (e instanceof P)
            return ((P) e).toArray();
        else
            return new Object[]{e};
    }
}
