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
import debug.Monitorable;
import static function.ComparePolicy.inc;
import iterator.IteratorCache;
import iterator.RotateListIterator;
import iterator.TIterator;
import iterator.TListIterator;
import static java.lang.Math.abs;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import orderedSet.Range;

/**
 *
 * @author masao
 * @param <T>
 */
public class TList<T> extends TListWrapper<T> implements Monitorable {
    
    public TList(List<T> body) {
        super(body);
    }
    
    /**
     * last item of the list.
     * when you want the first item, you can get it by .get(0).
     * compared to that simplisity, you have to call .get(size()-1) to get the
     * last item. simply i thought it's not fair. that's the reason why i prepared
     * this method.
     * @return 
     */
    public T last() {
        return last(0);
    }
    
    /**
     * nth item from last.
     * variation of get() starting from the end of this list.
     * @param n
     * @return 
     */
    public T last(int n) {
        return get(size()-1-n);
    }
    
    /**
     * set the part of this list nth from the last.
     * this list has last(nth). why not setLast(nth).
     * @param n
     * @param o
     * @return 
     */
    public T setLast(int n, T o) {
        return set(size()-1-n, o);
    }
    
    /**
     * method chain friendly version of add.
     * @param added
     * @return 
     */
    public TList<T> addOne(T added) {
        this.add(added);
        return this;
    }
    
    public TList<T> cset(int i, T o) {
        this.set(i, o);
        return this;
    }
    
    public TList<T> insertAt(int at,TList<T> t) {
        return subList(0,at).append(t).append(subList(at,size()));
    }
    
    public TList<T> insertAt(int at, T t) {
        return insertAt(at, wrap(t));
    }
    
    /**
     * fix the calculation.
     * basically, TList accepts calculation requests but keep the actual calculation
     * to happen until the content is required. this idea works good in some senarios, 
     * but imagine when the content is required repeatedly or when you want to take a
     * snapshot of the content at some time.
     * @return 
     */
    public TList<T> fix() {
        return new TListRandom<>(new ArrayList<>(this));
    }
    
    public TList<T> sfix() {
        return new TListRandom<>(a2l((T[])toArray()));
    }
    
    /**
     * fix with debug print.
     * when you fix a list, it will try to calculate all the items. in debugging
     * phase, it should be a good time to print out contents of list. you can do
     * that simply by tee (or teep for more convenience). but what if you have 
     * so many number of items in the list? this method is for that situation.
     * the list is supposed to be 
     * @param interval
     * @return 
     */
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
    
    /**
     * fix to sequential list.
     * mostly the functionalities of this list rely upon the RandomAccess list.
     * of course, that cannot be a reason to deny the convenience of sequential
     * list. you can fix the list into a LinkedList by this.
     * @return 
     */
    public TList<T> fix2seq() {
        return new TList<>(new LinkedList<>(this));
    }
    
    public TList<T> unmod() {
        return TList.set(Collections.unmodifiableList(this));
    }
    
    /**
     * something like unix tee.
     * this list will encourage you to use method chain. you will find how good 
     * it is to write your concept in short sentence. i guess you will feel more
     * concentrated by seeing concentrated definition of the logics than get distracted.
     * but the trait comes with some tradeoffs. where can you put debug print?
     * here's the method for that purpose.
     * @param c
     * @return 
     */
    public TList<T> tee(Consumer<? super T> c) {
        return map(t->{
            c.accept(t);
            return t;
        });
    }
    
    /**
     * tee() for print out.
     * tee which is specialized for printing. takes a String parameter to mark 
     * that the String on display is printed out by a certain teep().
     * @param mark
     * @return 
     */
    public TList<T> teep(String mark) {
        return tee(e->System.out.println(mark+e));
    }
    
    /**
     * convenient form of teep().
     * @return 
     */
    public TList<T> teep() {
        return teep("");
    }
    
    public T getDebug(int at) {
        System.out.println(""+at+":"+get(at));
        return get(at);
    }
    
    /**
     * give all the items in this list to Consumer.
     * note even after 'consuming', my wild guess is all the items remain in the
     * list.
     * @param c Consumer who eats all the items in this list.
     * @return 
     */
    public TList<T> accept(Consumer<TList<T>> c) {
        c.accept(this);
        return this;
    }
    
    public TList<T> cache() {
        return set(new CacheList(this));
    }
    
//-----------Generating
    /**
     * wrap an object as this list.
     * if you want to let a single object be compatible with this list, it's the time 
     * to use this method.
     * @param <T>
     * @param target
     * @return 
     */
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
    
    /**
     * generate a list of integers in a row.
     * simple. list of integers ranging between parameters. 
     * this method goes with the same policy as the original List is taking on 
     * the subList(). from is contained. to is not contained.
     * @param from
     * @param to
     * @return 
     */
    static public TList<Integer> range(int from, int to) {
        if (from <= to)
            return rangeBase(from, to);
        else
            return rangeBase(to, from).reverse();
    }
    
    /**
     * generate a list of integers in a row, but...
     * yeah. there was a time i believed in a faith that goes like, "every range
     * can be expressed in the way an array is expressed." from included, to excluded.
     * well. that principle works in most cases. but when i looked at a 2d grid,
     * where you don't find where is 'upper' or 'lower', i was compelled to admit
     * that i need to have this method.
     * still, i believe this only happens because of the symmetry of space, so 
     * i named this method after "range with symmetry".
     * @param from
     * @param to
     * @return 
     */
    static public TList<Integer> rangeSym(int from, int to) {
        if (from <= to)
            return rangeBaseSym(from, to);
        else
            return rangeBaseSym(to, from).reverse();
    }
    
    /**
     * range method which takes the {@code Range<Integer>} as parameter.
     * 
     * @param range
     * @return 
     */
    static public TList<Integer> range(Range<Integer> range) {
        return rangeBase(range.start(), range.end());
    }
    
    static public TList<Integer> skipRange(Range<Integer> range, int interval) {
        return rangeBase(0, (range.end()-range.start()-1)/interval+1).map(i->range.start()+i*interval);
    }
    
    /**
     * empty list.
     * @param <T>
     * @return 
     */
    static public <T> TList<T> empty() {
        return set(Collections.emptyList());
    }
    
    /**
     * add functionalities to another list.
     * @param <T>
     * @param body
     * @return 
     */
    static public <T> TList<T> set(List<T> body) {
        return (body instanceof RandomAccess) ? new TListRandom<>(body) : new TList<>(body);
    }
    /**
     * add functionalities to another collection.
     * @param <T>
     * @param body
     * @return 
     */
    static public <T> TList<T> set(Collection<T> body) {
        return set(new ArrayList<>(body));
    }
    
    /**
     * create new list.
     * @param <T>
     * @return 
     */
    static public <T> TList<T> c() {
        return new TListRandom<>(new ArrayList<>());
    }
    
    /**
     * create new list from an array of items.
     * @param <T>
     * @param t
     * @return 
     */
    static public <T> TList<T> of(T... t) {
        return new TListRandom<>(new ArrayList<>(a2l(t)));
    }
    
    /**
     * create new list from an array of items.
     * this variation of of make use of list based on array
     * (notice this is different from ArrayList). that list
     * is very efficient in time and space but there is a 
     * limitation in functionality(i.e. the list cannot extend further).
     * @param <T>
     * @param t
     * @return 
     */
    static public <T> TList<T> sof(T... t) {
        return new TListRandom<>(a2l(t));
    }
    
    @Deprecated
    static public <T> TList<T> ofStatic(T... t) {
        return sof(t);
    }
    
//--------- Judging
    /**
     * check whether all the elements are the same.
     * @return 
     */
    public boolean isUniform() {
        return distinctLocally().isEmpty();
    }
    
    /**
     * check whether all the elements are in the ascending order.
     * and not any two elements are equal.
     * @param comp
     * @return 
     */
    public boolean isAscending(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d>=0).isEmpty();
    }
    
    /**
     * check whether all the elements are in the descending order.
     * and not any two elements are equal.
     * @param comp
     * @return 
     */
    public boolean isDescending(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d<=0).isEmpty();
    }
    
    /**
     * check whether all the elements are in the ascending order.
     * and allows several elements to be equal.
     * @param comp
     * @return 
     */
    public boolean isAscendingOrEqual(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d>0).isEmpty();
    }
    
    /**
     * check whether all the elements are in the descending order.
     * and allows several elements to be equal.
     * @param comp
     * @return 
     */
    public boolean isDescendingOrEqual(Comparator<T> comp) {
        return diff((a,b)->comp.compare(a,b)).filter(d->d<0).isEmpty();
    }
    
    /**
     * check whether a predicate applies to all the elements in the list.
     * @param pred
     * @return 
     */
    public boolean forAll(Predicate<T> pred) {
        return filter(pred.negate()).isEmpty();
    }
    
    /**
     * check whether a predicate applies to at least one element in the list.
     * @param pred
     * @return 
     */
    public boolean exists(Predicate<T> pred) {
        return !filter(pred).isEmpty();
    }
    
//--------- Transforming
    /**
     * apply a function to the list itself.
     * if you are really eager to continue the method chain, the hurdle is when you 
     * need to use a list at two or more place. here is the method to overcome that
     * limitation.
     * note something this method offering is merely a certain programming style and 
     * you always have equivalent ways of expression in other forms. 
     * @param <S>
     * @param map
     * @return 
     */
    public <S> S transform(Function<TList<T>, S> map) {
        return map.apply(this);
    }
    
    /**
     * map.
     * this version of map requires reverse mapping to make the resulting list editable.
     * of course it's not always the case you can find reverse mapping and in that case
     * you should gracefully give up to make it editable and use the map() below.
     * @param <S>
     * @param map
     * @param rmap
     * @return 
     */
    public <S> TList<S> map(Function<T, S> map, Function<S, T> rmap) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new MapRandomList<>(this, map, rmap)) : new TList<>(new MapSequentialList<>(this, map, rmap));
    }
    
    /**
     * map.
     * very basic operation to a list. map each element by Function given as a parameter.
     * another name for this method would be 'collect'.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<S> map(Function<T, S> map) {
        return map(map, e->{throw new RuntimeException("Tried to change a list mapped without reverse map");});
    }
    
    /**
     * map cached.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<S> mapc(Function<T, S> map) {
        return map(map).cache();
    }
    
    /**
     * flat map.
     * flat the nested list while mapping.
     * you can use this method only to flat the nested list by giving e->e
     * (i.e. identical mapping) as parameter.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<S> flatMap(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ListRandomList<>(map(map))) : new TList<>(new ListSequentialList<>(map(map)));
    }
    
    /**
     * flatMap cached.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<S> flatMapc(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ListRandomList<>(mapc(map))) : new TList<>(new ListSequentialList<>(mapc(map)));
    }
    
    /**
     * transpose.
     * remember matrix operation. in some way, this method has something common
     * with flatMap. in flatMap, each element is mapped to a list of something
     * and after that they are concatenated. also in this method each element is
     * mapped to a list of something but they are sliced in to another set of 
     * lists.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<List<S>> transpose(Function<T, List<S>> map) {
        return new TListRandom<>(new TransposeList<>(map(map)));
    }
    
    /**
     * TList<TList<..>> version of transpose.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<TList<S>> transposeT(Function<T, List<S>> map) {
        return transpose(map).map(l->TList.set(l));
    }
    
    /**
     * accum.
     * basically what it does is very similar to the 'reduce' of stream.
     * the difference is, contrary to the result of reduce, the result of this 
     * method still have the list representing the intermedeate values that 
     * lead to the result of reduce. by this, you can find where the threshold
     * is broken.
     * once it was called as 'heap', actually i liked that name but i left that 
     * name for compatibility problem with diff. heap had a BiFunction as parameter
     * but the order of the input values for it was T, S. S is supposed to be the
     * formar value and T should be the newly applied value taken from this. This
     * is quite awkward when you look at this situation through the light of 
     * parameter order of BiFunction taken by diff, in which first one comes first.
     * here i prioritized the order than name.
     * @param <S>
     * @param start
     * @param map
     * @return 
     */
    public <S> TList<S> accum(S start, BiFunction<S, T, S> map) {
        return set(new IteratorCache<>(TIterator.set(iterator()).accum(start, map)));
    }
    
    @Deprecated
    public <S> TList<S> heap(S start, BiFunction<T, S, S> map) {
        return accum(start, (a,b)->map.apply(b, a));
    }
    
    /**
     * accum which takes first item of the list as start.
     * in some cases, a list which all corresponds to the original item is needed.
     * but when you use accum(), it adds an element at the head of the list in excess.
     * to avoid that, you can use this.
     * once i called this method as 'preheap', but i wanted more self-explaining name
     * for this function and thus the today's name.
     * 
     * very important application of this function is to put something between items
     * in this list. very the exemplar usage is toCatenatedString() of this class.
     * when you put something between items, this can be the simplest answer.
     * @param <S>
     * @param first
     * @param map
     * @return 
     */
    public <S> TList<S> accumFromStart(Function<T, S> first, BiFunction<S, T, S> map) {
        if (isEmpty())
            return TList.empty();
        return subList(1, size()).accum(first.apply(get(0)), map);
    }
    
    @Deprecated
    public <S> TList<S> heapFromStart(Function<T, S> first, BiFunction<T, S, S> map) {
        return accumFromStart(first, (a,b)->map.apply(b,a));
    }
    @Deprecated
    public <S> TList<S> preheap(Function<T, S> first, BiFunction<T, S, S> map) {
        return TList.this.heapFromStart(first, map);
    }

    
    /**
     * simpler version of accumFromStart.
     * only applicable when the first item itself can be the first item of the
     * result of accumFromStart().
     * @param map
     * @return 
     */
    public TList<T> accumFromStart(BiFunction<T, T, T> map) {
        if (isEmpty())
            return TList.empty();
        return subList(1, size()).accum(get(0), map);
    }

    @Deprecated
    public TList<T> heapFromStart(BiFunction<T, T, T> map) {
        return accumFromStart((a,b)->map.apply(b,a));
    }
    @Deprecated
    public TList<T> preheap(BiFunction<T, T, T> map) {
        return heapFromStart(map);
    }
    
//-----------Calculating
    /**
     * map each item to double value and take average of them.
     * @param f
     * @return 
     */
    public double averageD(ToDoubleFunction<T> f) {
        return stream().mapToDouble(f).average().orElse(0);
    }

    /**
     * map each item to long value and take average of them.
     * @param f
     * @return 
     */
    public double averageL(ToLongFunction<T> f) {
        return stream().mapToLong(f).average().orElse(0);
    }

    /**
     * map each item to int value and take average of them.
     * @param f
     * @return 
     */
    public double averageI(ToIntFunction<T> f) {
        return stream().mapToInt(f).average().orElse(0);
    }

    /**
     * map each item to double value and take sum of them.
     * @param f
     * @return 
     */
    public double sumD(ToDoubleFunction<T> f) {
        return stream().mapToDouble(f).sum();
    }
    
    /**
     * map each item to long value and take average of them.
     * @param f
     * @return 
     */
    public long sumL(ToLongFunction<T> f) {
        return stream().mapToLong(f).sum();
    }
    
    /**
     * map each item to int value and take average of them.
     * @param f
     * @return 
     */
    public int sumI(ToIntFunction<T> f) {
        return stream().mapToInt(f).sum();
    }
    
    public double averageSampleD(ToDoubleFunction<T> f) {
        return stream().mapToDouble(f).sum()/(size()-1);
    }

    /**
     * map each item to long value and take average of them.
     * @param f
     * @return 
     */
    public double averageSampleL(ToLongFunction<T> f) {
        return (double)stream().mapToLong(f).sum()/(size()-1);
    }

    /**
     * map each item to int value and take average of them.
     * @param f
     * @return 
     */
    public double averageSampleI(ToIntFunction<T> f) {
        return (double)stream().mapToInt(f).sum()/(size()-1);
    }

    
    
//---------- Filtering
    /**
     * indexOf variant which is specified by Predicate.
     * @param cond
     * @return 
     */
    public int indexOf(Predicate<T> cond) {
        return TList.range(0, size()).filter(i->cond.test(get(i))).getOpt(0).orElse(-1);
    }
    
    /**
     * subList() variant which is receptive of irrelevant indices as parameters.
     * @param from
     * @param to
     * @return 
     */
    public TList<T> subListAnyway(int from, int to) {
        if (from >= to)
            return TList.empty();
        if (from >= size())
            return TList.empty();
        if (to > size())
            return subList(from, size());
        return subList(from, to);
    }
    
    /**
     * get() variant which is receptive of irrelevant index as parameter.
     * @param index
     * @return 
     */
    public Optional<T> getOpt(int index) {
        if (body instanceof RandomAccess)
            return index<size()?Optional.of(get(index)):Optional.empty();
        int number = -1;
        T retval=null;
        for (Iterator<T> iter = iterator(); number<index&&iter.hasNext(); retval=iter.next())
            number++;
        return number==index?Optional.of(retval):Optional.empty();
    }
    
    /**
     * last() variant which is receptive of irrelevant index as parameter.
     * @param index
     * @return 
     */
    public Optional<T> lastOpt(int index) {
        return index<size()?Optional.of(last(index)):Optional.empty();
    }
    
    /**
     * filter.
     * very basic operation to a list. check each element falls on a category 
     * described by Predicate given as a parameter.
     * another name for this method would be 'select'.
     * @param cond
     * @return 
     */
    public TList<T> filter(Predicate<T> cond) {
        return new TList<>(new FilterList<>(this, cond));
    }
    
    public TList<Integer> filterAt(Predicate<T> cond) {
        return pair(new Scale()).filter(p->cond.test(p.l())).map(p->p.r());
    }
    
    /**
     * hide all the equivalent item only in vicinity.
     * when the list is sorted in the order which reflects the equivalency, this is 
     * good enough to leave only distinct elements.
     * @return 
     */
    public TList<T> distinctLocally() {
        return diff().filter(p->!p.l().equals(p.r())).transform(dediff());
    }
    
    /**
     * hide all the equivalent item in terms of Comparator only in vicinity.
     * when the list is sorted in the order of given comparator c, this is 
     * good enough to leave only distinct elements.
     * @param c 
     * @return 
     */
   public TList<T> distinctLocally(Comparator<T> c) {
        return diff().filter(p->c.compare(p.l(), p.r())!=0).transform(dediff());
    }
    
    /**
     * convenient method to build a BitSet described by parameter indices.
     * @param size
     * @param indices
     * @return 
     */
    public static BitSet toMask(int size, List<Integer> indices) {
        return toMask(size, l2aInt(indices));
    }
    
    /**
     * array version of toMask().
     * @param size
     * @param indices
     * @return 
     */
    public static BitSet toMask(int size, int[] indices) {
        BitSet retval = new BitSet(size);
        for (int i : indices) {
            retval.set(i);
        }
        return retval;
    }
    
    /**
     * show the only items specified by indices.
     * mask() is behind.
     * @param indices
     * @return 
     */
    public TList<T> show(List<Integer> indices) {
        return mask(toMask(this.size(), indices));
    }
    
    /**
     * hide the items specified by indices.
     * mask() is behind.
     * @param indices
     * @return 
     */
    public TList<T> hide(List<Integer> indices) {
        BitSet mask = toMask(this.size(), indices);
        mask.flip(0, this.size());
        return mask(mask);
    }
    
    /**
     * show the items specified by mask.
     * @param mask
     * @return 
     */
    public TList<T> mask(BitSet mask) {
        return new TList<>(new MaskedList<>(this, mask));
    }
    
    /**
     * show the items specified by indices.
     * the specification is very similar to show or mask.
     * but this implementation is preferred when the choices are sparse.
     * remember TList sometimes conveys purely calculated objects.
     * @param indices
     * @return 
     */
    public TList<T> pickUp(List<Integer> indices) {
        return set(indices).map(i->get(i));
    }

    /**
     * list made of subList of a list which extends from start to end.
     * @return 
     */
    public TList<TList<T>> startings() {
        return range(1, size()+1).map(end->subList(0, end));
    }
    
    /**
     * list made of subList of a list which extends from end to start.
     * @return 
     */
    public TList<TList<T>> endings() {
        return range(0, size()).map(start->subList(start, size()));
    }
    
    public TList<TList<T>> subLists(int length) {
        return range(0,size()-length).map(i->subList(i,i+length));
    }
    
//------- Ordering
    
    /**
     * Sort to new list.
     * Unlike the List#sort(), this method returns new list. Mainly because an instance
     * of TList is highly manipulated by its methods and sort method is unlikely to be 
     * applicable.
     * recommend you to use fix() right before this method, when the list is long enough.
     * @param c 
     * @return 
     */
    public TList<T> sortTo(Comparator<T> c) {
        List<T> retval = new ArrayList<>(this);
        retval.sort(c);
        return set(retval);
    }

    /**
     * returns minimum item in terms of comp.
     * @param comp
     * @return 
     */
    public Optional<T> min(Comparator<T> comp) {
        return stream().min(comp);
    }
    
    /**
     * returns minimum item in terms of func.
     * values are compared in terms of the result of func, but the 
     * value returned is the item in the list.
     * @param <S>
     * @param func
     * @return 
     */
    public <S extends Comparable<S>> Optional<T> min(Function<T, S> func) {
        return min(inc(func));
    }
    
    public <S extends Comparable<S>> Optional<S> minval(Function<T,S> func) {
        return map(func).min(inc(x->x));
    }
    
    /**
     * returns maximum item in terms of comp.
     * @param comp
     * @return 
     */
    public Optional<T> max(Comparator<T> comp) {
        return stream().max(comp);
    }
    
    /**
     * returns maximum item in terms of func.
     * values are compared in terms of the result of func, but the 
     * value returned is the item in the list.
     * @param <S>
     * @param func
     * @return 
     */
    public <S extends Comparable<S>> Optional<T> max(Function<T, S> func) {
        return max(inc(func));
    }
    
    public <S extends Comparable<S>> Optional<S> maxval(Function<T,S> func) {
        return map(func).max(inc(x->x));
    }
    
    /**
     * hide start or end of the list.
     * if the parameter seek is positive, the number of items specified by the 
     * parameter is hidden. otherwise, the number of items specifed by the
     * absolute of the parameter is hidden.
     * @param seek
     * @return 
     */
    public TList<T> seek(int seek) {
        assert -size() <= seek && seek <= size() : "seek steps get out of range:"+seek+" was asked when size was "+size();
        return seek>0?subList(seek, size()):subList(0, size()+seek);
    }

    /**
     * show the list in the way upside down.
     * @return 
     */
    public TList<T> reverse() {
        return (body instanceof RandomAccess) ? new TListRandom<>(new ReverseRandomList<>(body)) : new TList<>(new ReverseSequentialList<>(body));
    }
    
    /**
     * repetitive access to the list.
     * index designates the item to start.
     * @param index
     * @return 
     */
    public TListIterator<T> repeat(int index) {
        return new TListIterator<>(new RotateListIterator<>(this, index));
    }
    
    public TListIterator<T> repeat() {
        return TList.this.repeat(0);
    }
    
    /**
     * rotate the list.
     * if the parameter x is positive, the result should start from xth item 
     * and end with x-1th item. otherwise, it should start from size()+xth and 
     * end with size()+x-1th item.
     * @param x
     * @return 
     */
    public TList<T> rotate(int x) {
        assert -size()<x && x<size();
        if (x==0)
            return this;
        if (0<x)
            return TList.concat(subList(x, size()), subList(0, x));
        else
            return TList.concat(subList(size()+x, size()), subList(0, size()+x));
    }
    
    public TList<T> flip(int from, int to) {
        if (from==to)
            return this;
        int min = Integer.min(from, to);
        int max = Integer.max(from, to);
        assert 0<=min&&max<size() : "tried to flip out of range";
        TList<T> l0 = subList(0,min);
        TList<T> l1 = subList(min+1,max);
        TList<T> l2 = subList(max+1,size());
        return l0.append(get(max)).append(l1).append(get(min)).append(l2);
    }
    
    public TList<T> skip(int n) {
        assert n>=1 : "no skip less than 1";
        if (n==1)
            return this;
        return set(new SkipList<>(this,n));
    }
    
    /**
     * divide the list as lists which have certain number of items(except the last
     * one).
     * @param n
     * @return 
     */
    public TList<TList<T>> fold(int n) {
        return set(new FoldList<>(this,n));
    }
    
    /**
     * divide the list into two by the first occurence of the item equals to division.
     * think of the parameter division as a delimiter.
     * @param division
     * @return 
     */
    public TList<TList<T>> divide(T division) {
        int index = indexOf(division);
        if (index == -1) 
            return TList.sof(this);
        return TList.sof(subList(0, indexOf(division) + 1), subList(indexOf(division) + 1, size()));
    }
    
    /**
     * divide the list by occurence of the item which matches with pred.
     * the dividing item is contained as the last item of sublist.
     * 
     * if you want it to be otherwise (meaning the dividing item to be the
     * first item of sublist), follow these steps:
     * 1. reverse the list
     * 2. chunk it up
     * 3. reverse those sublists all by map
     * 
     * the result is sfix-ed, because it has a tremendous influence in performance.
     * @param pred
     * @return 
     */
    public TList<TList<T>> chunk(Predicate<T> pred) {
        TList<TList<T>> retval = TList.c();
        return chunk(retval, this, pred);
    }
    
    public TList<TList<T>> filterChunk(Predicate<T> pred, BiFunction<TList<T>,Predicate<T>,TList<TList<T>>> chunk) {
        return chunk.apply(this,pred).filter(l->!l.filter(pred.negate()).isEmpty());
    }
    
    static public <T> BiFunction<TList<T>,Predicate<T>,TList<TList<T>>> chunk() {
        return (a,b)->a.chunk(b);
    }
    static public <T> BiFunction<TList<T>,Predicate<T>,TList<TList<T>>> reverseChunk() {
        return (a,b)->a.reverseChunk(b);
    }
    static public <T> BiFunction<TList<T>,Predicate<T>,TList<TList<T>>> trimmedChunk() {
        return (a,b)->a.trimmedChunk(b);
    }
    static public <T> BiFunction<TList<T>,Predicate<T>,TList<TList<T>>> envelopChunk() {
        return (a,b)->a.envelopChunk(b);
    }
        
    /**
     * chunk which do not contain item which divide the list.
     * @param pred
     * @return 
     */
    public TList<TList<T>> trimmedChunk(Predicate<T> pred) {
        return chunk(pred).map(l->l.filter(pred.negate()));
    }
    
    public TList<TList<T>> envelopChunk(Predicate<T> pred) {
        TList<TList<T>> chunk = chunk(pred);
        Iterator<TList<T>> iter = chunk.iterator();
        if (!iter.hasNext())
            return chunk;
        iter.next();
        if (!iter.hasNext())
            return chunk;
        return chunk.diff((a,b)->b.startFrom(a.last())).startFrom(chunk.get(0));
    }
    
    public TList<TList<T>> reverseChunk(Predicate<T> pred) {
        return reverse().chunk(pred).map(l->l.reverse()).reverse();
    }
    
    /**
     * main body of chunk.
     * recursive function to chunk up the list.
     * result is sfix-ed for memo.
     * @param current
     * @param remain
     * @param pred
     * @return 
     */
        
    TList<TList<T>> chunk(TList<TList<T>> current, TList<T> remain, Predicate<T> pred) {
        TList<Integer> divides = remain.filterAt(pred);
        if (divides.isEmpty())
            return current.addOne(remain);
        int division = divides.get(0);
        return chunk(current.addOne(remain.head(division+1).sfix()), remain.tail(division+1).sfix(), pred);
    }
    
    public TList<T> head(int i) {
        return subList(0,i);
    }
    public TList<T> tail(int i) {
        return subList(i,size());
    }

    /**
     * chunk thie list up with relationship with next item.
     * this version creates sentinel using Optional.
     * the result is sfix-ed because it has tremendous influence in performance.
     * @param pred
     * @return 
     */
    public TList<TList<T>> diffChunk(BiPredicate<T,T> pred) {
        return map(e->Optional.of(e)).sfix().diffChunk((a,b)->a.isPresent()&&b.isPresent()?pred.test(a.get(),b.get()):true, Optional.empty()).map(l->l.map(o->o.get()).sfix());
    }
    
    /**
     * chunk the list up with relationship with next item.
     * the result is sfix-ed because it has tremendous influence in performance.
     * @param pred
     * @param sentinel an item which is not contained in list to show the end of the list
     * @return 
     */
    public TList<TList<T>> diffChunk(BiPredicate<T,T> pred, T sentinel) {
        assert !contains(sentinel);
        return append(sentinel).diff().sfix().chunk(p->pred.test(p.l(),p.r())).sfix().filter(l->!l.isEmpty()).sfix().map(pl->pl.map(p->p.l())).sfix();
    }
    
//----------- Composing
    /**
     * concatenate lists in parameters.
     * @param <T>
     * @param t
     * @return 
     */
    static public <T> TList<T> concat(List<T>... t) {
        return concat(new ArrayList<>(a2l(t)));
    }
    
    /**
     * concatenate lists in a list.
     * @param <T>
     * @param t
     * @return 
     */
    static public <T> TList<T> concat(List<List<T>> t) {
        return t.stream().allMatch(e->(e instanceof RandomAccess)) ? new TListRandom<>(new ListRandomList<>(t)) : new TList<>(new ListSequentialList<>(t));
    }
    
    /**
     * append lists after this list.
     * @param t
     * @return 
     */
    public TList<T> append(List<T>... t) {
        return appendLists(a2l(t));
    }
    
    public TList<T> append(T... t) {
        return append(TList.sof(t));
    }
    
    /**
     * append lists after this list.
     * @param t
     * @return 
     */
    public TList<T> appendLists(List<List<T>> t) {
        List<List<T>> l = new ArrayList<>(a2l(this));
        l.addAll(t);
        return concat(l);
    }
    
    public TList<T> startFrom(List<T>... t) {
        TList<T> added = concat(a2l(t));
        return added.append(this);
    }
    
    public TList<T> startFrom(T... t) {
        return startFrom(sof(t));
    }
    
    /**
     * merge sort.
     * this list and the merged list are assumed to be sorted by means of c.
     * @param merged
     * @param c
     * @return 
     */
    public TList<T> merge(List<T> merged, Comparator<T> c) {
        return set(new MergeList<>(this, merged, c));
    }
    
    /**
     * handle two lists in parallel.
     * note here i don't mean any parallelism. it's all about the order of lists.
     * take one item from each lists is what this method is doing.
     * this version generates P anyway.
     * @param <S>
     * @param add
     * @return 
     */
    public <S> TList<P<T, S>> pair(List<S> add) {
        return (body instanceof RandomAccess) && (add instanceof RandomAccess) ? new TListRandom<>(new ZipRandomList<>(body, add)) : new TList<>(new ZipSequentialList<>(body, add));
    }
    
    /**
     * handle two lists in parallel.
     * note here i don't mean any parallelism. it's all about the order of lists.
     * take one item from each lists is what this method is doing.
     * you can avoid generating P by specifying map. 
     * sometimes, filtering can be made faster by producing Boolean by this method
     * than to produce P by the other variation of pair().
     * @param <S>
     * @param <U>
     * @param add
     * @param map
     * @return 
     */
    public <S, U> TList<U> pair(List<S> add, BiFunction<T, S, U> map) {
        return (body instanceof RandomAccess) && (add instanceof RandomAccess) ? new TListRandom<>(new PairRandomList<>(body, add, map)) : new TList<>(new PairSequentialList<>(body, add, map));
    }
    
    /**
     * pair by itself with map.
     * this is a flavor of pair() for someone wants to continue method chain.
     * the motivation behind is very similar to transform().
     * @param <S>
     * @param <U>
     * @param funcL
     * @param funcR
     * @return 
     */
    public <S, U> TList<P<S, U>> w(Function<T, S>funcL, Function<T, U>funcR) {
        return map(funcL).pair(map(funcR));
    }
    
    /**
     * pair by itself with map.
     * this is a flavor of pair() for someone wants to continue method chain.
     * the motivation behind is very similar to transform().
     * @return 
     */
    public TList<P<T, T>> w() {
        return pair(this);
    }
    
    /**
     * differential pair.
     * very typical variation of pair. this will save you the burden to construct
     * buffer on every pre-post comparison. this will help you a lot. believe me.
     * @return 
     */
    public TList<P<T, T>> diff() {
        return diff(1);
    }
    
    /**
     * differential pair with specified number of gap.
     * very typical variation of pair. this will save you the burden to construct
     * buffer on every pre-post comparison. this will help you a lot. believe me.
     * @param seek
     * @return 
     */
    public TList<P<T, T>> diff(int seek) {
        return pair(seek(seek));
    }
    
    /**
     * differential pair.
     * very typical variation of pair. this will save you the burden to construct
     * buffer on every pre-post comparison. this will help you a lot. believe me.
     * and this variation saves generation of P.
     * @param <U>
     * @param map
     * @return 
     */
    public <U> TList<U> diff(BiFunction<T, T, U> map) {
        return diff(1, map);
    }
    
    /**
     * differential pair with specified number of gap.
     * very typical variation of pair. this will save you the burden to construct
     * buffer on every pre-post comparison. this will help you a lot. believe me.
     * and this variation saves generation of P.
     * @param <U>
     * @param seek
     * @param map
     * @return 
     */
    public <U> TList<U> diff(int seek, BiFunction<T, T, U> map) {
        return pair(seek(seek), map);
    }
    
    /**
     * de-differencing.
     * diff()-ed list is easily be rebuilt as the list it used to be by 
     * map()ping the diff()-ed map with this function.
     * @param <T>
     * @return 
     */
    static public <T> Function<TList<P<T, T>>, TList<T>> dediff() {
        return l->l.isEmpty()?TList.empty():concat(wrap(l.get(0).l()), l.map(p->p.r()));
    }
    
    /**
     * multiple diff.
     * if you want diff with n consecutive items, this is the method you should turn to.
     * @param steps
     * @return 
     */
    public TList<List<T>> diffn(int steps) {
        return diffnn(range(0,steps));
    }
    
    /**
     * multiple diff.
     * if you want multiple items diff but they are not in consecutive order, still you can use this.
     * @param steps
     * @return 
     */
    public TList<List<T>> diffnn(TList<Integer> steps) {
        assert steps.size() >=1 : "min length of steps is 1";
        int length = size()-steps.maxval(i->i).get();
        return steps.transpose(i->seek(i).subList(0,length));
    }

    /**
     * multiple diff.
     * you can designate items conveniently.
     * @param steps
     * @return 
     */
    public TList<List<T>> diffnn(Integer... steps) {
        return diffnn(TList.sof(steps));
    }

    /**
     * cross product of two lists.
     * the most basic combination of two lists are cross product. i don't agree
     * with any other opinion.
     * and here is the method to produce the cross product of two lists as P.
     * this list will be the major and the other(meaning the parameter 'target') 
     * will be the minor, meaning the item from this list is put as the first item. 
     * item from this list is incremented only after the items from the other list are
     * all numerated. 
     * @param <S>
     * @param target
     * @return 
     */
    public <S> TList<P<T, S>> cross(List<S> target) {
        return cross(target, (t,s)->P.p(t,s));
    }
    
    /**
     * cross product of two lists.
     * the most basic combination of two lists are cross product. i don't agree
     * with any other opinion.
     * and here is the method to produce the cross product of two lists as the 
     * result of func applied to those items.
     * this list will be the major and the other(meaning the parameter 'target') 
     * will be the minor, meaning the item from this list is put as the first item. 
     * item from this list is incremented only after the items from the other list are
     * all numerated. 
     * @param <S>
     * @param <U>
     * @param target
     * @param func
     * @return 
     */
    public <S, U> TList<U> cross(List<S> target, BiFunction<T, S, U> func) {
        return flatMap(t->set(target).map(s->func.apply(t, s)));
    }
    
    /**
     * similar to cross product, but keep the structure.
     * @param <S>
     * @param target
     * @return 
     */
    public <S> TList<TList<P<T, S>>> matrix(List<S> target) {
        return matrix(target, (t,s)->P.p(t,s));
    }
    
    /**
     * similar to cross product, but keep the structure.
     * @param <S>
     * @param <U>
     * @param target
     * @param func
     * @return 
     */
    public <S, U> TList<TList<U>> matrix(List<S> target, BiFunction<T, S, U> func) {
        return map(t->set(target).map(s->func.apply(t,s)));
    }
    
    /**
     * weave up lists in lists.
     * when list are generated from the each item of this list(remember which is always
     * the case when all the items of this list are list and mapping applied is l->l(meaning
     * idential mapping)), you can also have list that goes widthwise.
     * it's very similar to the transpose except this doesn't require all the result of mapping
     * to have the similar length. and differs in the point of efficiency when it is used over 
     * sequential list.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<S> weave(Function<T, List<S>> map) {
        return (body instanceof RandomAccess) && map(map).stream().allMatch(l->l instanceof Random) ? new TListRandom<>(new WeaveRandomList<>(map(map))) : new TList<>(new WeaveSequentialList<>(map(map)));
    }
    
    /**
     * add delimitter to a list.
     * and the delimitter do not have to be uniform between elements.
     * @param <S>
     * @param map
     * @param delimit
     * @return 
     */
    public <S> TList<S> delimit(Function<T,S> map, BiFunction<T,T,S> delimit) {
        return TList.sof(map(map), diff(delimit)).weave(l->l);
    }
    
    public <S> TList<S> delimitByValue(Function<T,S> map, S delimit) {
        return delimit(map, (a,b)->delimit);
    }
//--------- Positioning
    /**
     * get a Optional-wrapped version of this list.
     * especially when you are coping with something very position-sensitive,
     * this might be a choice.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TOptionalList<S> optionalMap(Function<T, Optional<S>> map) {
        return new TOptionalList<>(map(map));
    }
    
    /**
     * flatMap version of optionalMap.
     * 
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TOptionalList<S> optionalFlatMap(Function<T, List<Optional<S>>> map) {
        return new TOptionalList<>(flatMap(map));
    }
    
    /**
     * show items which match test.
     * but in position preserving manner in comparison to filter().
     * @param test
     * @return 
     */
    public TOptionalList<T> show(Predicate<T> test) {
        return new TOptionalList<>(map(e->test.test(e) ? Optional.of(e) : Optional.empty()));
    }
    
    /**
     * negative version of show.
     * @param test
     * @return 
     */
    public TOptionalList<T> hide(Predicate<T> test) {
        return show(test.negate());
    }
    
    /**
     * simplest version of optionalMap().
     * @return 
     */
    public TOptionalList<T> optional() {
        return new TOptionalList<>(map(e->Optional.of(e)));
    }
    
    /**
     * add an Optional.empty filling of length at a part of this list.
     * 
     * @param at
     * @param length
     * @return 
     */
    public TOptionalList<T> fill(int at, int length) {
        return new TOptionalList<>(TList.concat(subList(0, at).optional(), new Filler<>(length), subList(at, size()).optional()));
    }
    
    /**
     * shift the start of this list.
     * @param shift
     * @return 
     */
    public TOptionalList<T> shift(int shift) {
        return shift < 0 ? subList(-shift, size()).optional() : fill(0, shift);
    }
    
    /**
     * add filling Optional.empty-s until result of map becomes rectangle.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<List<Optional<S>>> rectangle(Function<T, List<S>> map) {
        int length = maxLength(map);
        return map(map).map(l->set(l).fill(l.size(), length-l.size()));
    }
    
    /**
     * add filling Optional.empty-s until result of map becomes square.
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TList<List<Optional<S>>> square(Function<T, List<S>> map) {
        int length = maxLength(map);
        return length > size() ? TList.concat(this.rectangle(map), Filler.filler(length, length-size())) : TList.concat(this.rectangle(map).transpose(l->l), Filler.filler(size(), size()-length)).transpose(l->l);
    }
    
    public <S> int maxLength(Function<T, List<S>> map) {
        return map(map).stream().mapToInt(l->l.size()).max().orElse(0);
    }
    
    //----------- set
    
    public Set<T> toIdentitySet() {
        Map<T,Object> retval = new IdentityHashMap<>();
        forEach(t->retval.put(t,t));
        return retval.keySet();
    }
    
    public Set<T> toSet() {
        return new HashSet<>(this);
    }
    
    public TList<T> intersect(TList<T>... others) {
        return intersect(TList.sof(others));
    }
    
    public TList<T> intersect(TList<TList<T>> others) {
        TList<T> retval = fix();
        others.map(l->l.toSet()).forEach(retval::retainAll);
        return retval;
    }
    
    public <S> Set<S> intersect(Function<T,TList<S>> f) {
        return map(f).map(l->l.toSet()).stream().reduce((a,b)->{a.retainAll(b);return a;}).orElse(Collections.emptySet());
    }
    
    public TList<T> intersectByIdentity(TList<T>... others) {
        return intersectByIdentity(TList.sof(others));
    }
        
    public TList<T> intersectByIdentity(TList<TList<T>> others) {
        TList<T> retval = fix();
        others.map(l->l.toIdentitySet()).forEach(retval::retainAll);
        return retval;
    }
    
    public <S> Set<S> intersectByIdentity(Function<T,TList<S>> f) {
        return map(f).map(l->l.toIdentitySet()).stream().reduce((a,b)->{a.retainAll(b);return a;}).orElse(Collections.emptySet());
    }
    
    //----------- combination theory
    
    /**
     * nCopies.
     * only for convenience. to shorten the statement. DUH.
     * @param <T>
     * @param n
     * @param x
     * @return 
     */
    static public <T> TList<T> nCopies(int n, T x) {
        return TList.set(Collections.nCopies(n, x));
    }
    
    static private TList<TList<Integer>> permutationx(int a, int b, TList<TList<Integer>> prev) {
        assert a>=0 && b>=0;
        TList<Integer> base = range(0, a);
        if (b == 0)
            return c();
        if (b == 1)
            return base.map(e->of(e));
        return prev.map(m->base.hide(m)).pair(prev).flatMap(p->p.l().map(i->TList.concat(p.r(), wrap(i))));
    }
    
    static private TList<TList<Integer>> permutationx(int a, int b) {
        if (b == 0)
            return permutationx(a, b, null);
        return permutationx(a, b, permutationx(a, b-1));
    }
    
    static private TList<TList<TList<Integer>>> permutationUpTo(int a, int b) {
        TList<TList<TList<Integer>>> retval = c();
        retval.add(permutationx(a, 0, null));
        for (int i=1; i<=b; i++)
            retval.add(permutationx(a, i, retval.last()).fix());
        return retval;
    }
    
    static public TList<List<Integer>> permutation(int a, int b) {
        return permutationx(a, b).map(l->(List<Integer>)l);
    }

    public TList<List<T>> permutation(int a) {
        assert a >= 0;
        return permutation(size(), a).map(p->pickUp(p));
    }
    
    public TList<List<List<T>>> permutationUpTo(int a) {
        assert a >= 0;
        return permutationUpTo(size(), a).map(l->l.map(p->pickUp(p)));
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
    
    /**
     * subList by amount.
     * variation of subList which take a parameter in the same policy as rotate
     * @param rot
     * @return subList which is shaved in front or rear depending on the sign of the parameter rot.
     */
    public TList<T> subList(int rot) {
        assert abs(rot)<=size() : "you shaved me too much";
        if (rot>=0)
            return subList(rot,size());
        else
            return subList(0,size()+rot);
    }
    
    /**
     * a variant of toString() which returns wrapped string for item by item.
     * @return 
     */
    public String toWrappedString() {
        return toCatenatedString("\n");
    }
    
    /**
     * a variant of toString() which returns simply concatenated string.
     * @return 
     */
    public String toFlatString() {
        return toCatenatedString("");
    }
        
    /**
     * a variant of toString() which returns string concatenated with a delimiter.
     * this is another name for toCatenatedString()
     * @param delimiter
     * @return 
     */
    public String toDelimitedString(String delimiter) {
        return toCatenatedString(delimiter);
    }
    
    /**
     * a variant of toString() which returns string concatenated with a delimiter.
     * @param x
     * @return 
     */
    public String toCatenatedString(String x) {
        return toCatenatedString(x,()->"null");
    }

    public String toCatenatedString(String x, Supplier<String> forNull) {
        if (isEmpty())
            return "";
        return map(o->o!=null?o.toString():forNull.get()).accumFromStart(s->new StringBuilder(s),(a,b)->a.append(x).append(b)).last().toString();
    }

    /**
     * collector method for Stream#collect().
     * @param <T>
     * @return 
     */
    static public <T> Collector<T, ?, TList<T>> toTList() {
        return Collector.of((Supplier<List<T>>)ArrayList::new, List::add, (left,right)->{left.addAll(right);return left;}, TList::set);
    }
    
    /**
     * a method compatible with TestNG's data provider.
     * @return 
     */
    public Object[][] dataProvider() {
        return map(e->lineOfData(e)).toArray(new Object[][]{});
    }
    
    Object[] lineOfData(Object e) {
        if (e instanceof P)
            return ((P) e).toArray();
        else
            return new Object[]{e};
    }
    
    @Override
    public String monitor() {
        return "TList of "+body.size()+" elements:\n"+indent(body);
    }

}
