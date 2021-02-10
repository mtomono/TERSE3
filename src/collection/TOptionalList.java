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

import iterator.TListIterator;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

/**
 * TOptionalList - Optional version of TList.
 * Wrapper for TList to provide convenience in writing predicate or function
 * and consistency in Optional based operations like fill.
 * That's why this class doesn't have not so many methods compared to TList.
 * So to keep the implementation minimal, rather than to wrap all the method of
 * TList by this class, I made TOptionalList and TList<Optional<>> commutative. 
 * TOptionalList can be translated as TList<Optional<>> by asList() and 
 * TList<Optional<>> can be translated as TOptionalList. 
 * Basically TOptionalList is convenient but in case you want a finer control
 * over calculation TList<Optional<>> is where you should turn to.
 * if you need to handle TList<Optional<>> then translate this list to TList by
 * TOptionalList#asList(). if you want to get that back to TOptionalList then 
 * you can call TList#optionalMap(l->l). Or if you know that only temporarily
 * handle TList<Optional<>>, in that case, asList(Function) is handy.
 * @author masao
 * @param <T>
 */
public class TOptionalList<T> extends ListWrapper<Optional<T>> {
    TList<Optional<T>> bodyx;

    static public <T> TOptionalList<T> set(List<Optional<T>> body) {
        return new TOptionalList<>(body);
    }
    
    static public <T> TOptionalList<T> empty() {
        return new TOptionalList<>(TList.empty());
    }
    
    static public <T> TOptionalList<T> of(Optional<T>... t) {
        return new TOptionalList<>(TList.of(t));
    }
    
    public TOptionalList<T> fix() {
        return new TOptionalList<>(bodyx.fix());
    }
    
    static public <T> TOptionalList<T> concat(List<Optional<T>>... t) {
        return new TOptionalList<>(TList.concat(t));
    }
    
    static public <T> TOptionalList<T> concat(List<List<Optional<T>>> t) {
        return new TOptionalList<>(TList.concat(t));
    }
    
    public TOptionalList(TList<Optional<T>> body) {
        super(body);
        this.bodyx = body;
    }
    
    public TOptionalList(List<Optional<T>> body) {
        this(new TList<>(body));
    }
    
    /**
     * filter.
     * this method enables programmer to write predicate which directly handles
     * T. if you need to handle Optional<T> then turn to TList<Optional<T>> 
     * @param test
     * @return 
     */
    public TOptionalList<T> filter(Predicate<T> test) {
        return new TOptionalList<>(bodyx.filter(e->e.filter(test).isPresent()));
    }
    
    /**
     * map.
     * this method enables programmer to write predicate which directly handles
     * T. if you need to handle Optional<T> then turn to TList<Optional<T>>
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TOptionalList<S> map(Function<T, S> map) {
        return new TOptionalList<>(bodyx.map(e->e.map(map)));
    }

    /**
     * flatMap.
     * this method enables programmer to write predicate which directly handles T.
     * by its nature, this method doesn't leave empty (because they are translated as
     * empty list). if you want to leave them or have them special handling, 
     * turn to TList<Optional<T>>
     * @param <S>
     * @param map
     * @return 
     */
    public <S> TOptionalList<S> flatMap(Function<T, List<S>> map) {
        return new TOptionalList<>(bodyx.flatMap(optionalListMap(map)));
    }

    /**
     * pair.
     * if either side of element is empty, this method automatically judges the
     * pair to be empty. if you want to handle the situation where one of the element
     * in pair is empty then turn to TList<Optional<T>>
     * @param <S>
     * @param add
     * @return 
     */
    public <S> TOptionalList<P<T, S>> pair(List<Optional<S>> add) {
        return new TOptionalList<>(bodyx.pair(add).map(p->P.op(p)));
    }

    /**
     * show.
     * this method enables programmer to write predicate which directly handles
     * T. if you need to handle Optional<T> then turn to TList<Optional<T>>
     * @param test
     * @return 
     */
    public TOptionalList<T> show(Predicate<T> test) {
        return new TOptionalList<>(bodyx.map(e->e.filter(test)));
    }
    
    /**
     * hide.
     * this method enables programmer to write predicate which directly handles
     * T. if you need to handle Optional<T> then turn to TList<Optional<T>>
     * @param test
     * @return 
     */
    public TOptionalList<T> hide(Predicate<T> test) {
        return show(test.negate());
    }
    
    public TOptionalList<T> fill(int at, int length) {
        return TOptionalList.concat(subList(0, at), new Filler<>(length), subList(at, size()));
    }
    
    static public <T, S> Function<Optional<T>, List<Optional<S>>> optionalListMap(Function<T, List<S>> map) {
        return t->t.map(map.andThen(l->TList.set(l).optional())).orElse(empty());
    }
 
    public <S> TList<List<Optional<S>>> rectangle(Function<T, List<S>> map) {
        return bodyx.rectangle(t->t.map(map).orElse(TList.empty()));
    }

    public <S> TList<List<Optional<S>>> square(Function<T, List<S>> map) {
        return bodyx.square(t->t.map(map).orElse(TList.empty()));
    }
    
    /**
     * translate into ordinary (not optional) list by removing empty element.
     * @return 
     */
    public TList<T> normalize() {
        return new TList<>(bodyx.filter(e->e.isPresent())).map(e->e.get());
    }
    
    /**
     * translate into ordinary (not optional) list by replacing empty element with parameter filler.
     * @param filler
     * @return 
     */
    public TList<T> normalize(T filler) {
        return new TList<>(bodyx.map(e->e.orElse(filler)));
    }
    
    public TList<Optional<T>> asList() {
        return bodyx;
    }
    
    public <S> TOptionalList<S> asList(Function<TList<Optional<T>>, TList<Optional<S>>> map) {
        return new TOptionalList<>(map.apply(bodyx));
    }
    
    public boolean hasAny() {
        return OptionalListOperations.hasAny(this);
    }
    
    public boolean noEmpty() {
        return OptionalListOperations.noEmpty(this);
    }
    
    public TOptionalList<T> filter() {
        return filter(e->true);
    }

    public Optional<T> min(ToDoubleFunction<T> func) {
        return filter().min(func);
    }
    
    public Optional<T> max(ToDoubleFunction<T> func) {
        return filter().max(func);
    }
    
    //------------ T series compatible wrapping

    @Override
    public TListIterator<Optional<T>> listIterator() {
        return new TListIterator<>(bodyx.listIterator());
    }

    @Override
    public TListIterator<Optional<T>> listIterator(int index) {
        return new TListIterator<>(bodyx.listIterator(index));
    }

    @Override
    public TOptionalList<T> subList(int fromIndex, int toIndex) {
        return new TOptionalList<>(bodyx.subList(fromIndex, toIndex));
    }
}
