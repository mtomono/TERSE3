/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author masao
 */
public interface GridX<T> {

    GridX<T> append(GridSimple<T> other);

    TList<T> asList();

    GridCoord axis();
    
    TList<T> body();

    /**
     * c(hained)set.set method for chained method.
     * @param v
     * @param address
     * @return
     */
    GridX<T> cset(T v, List<Integer> address);

    GridX<T> cset(T v, Integer... address);

    TList<Integer> filterAt(Predicate<T> p);

    GridX<T> fix();

    GridX<T> flip(TList<Integer> order);

    GridX<T> flip(int from, int to);

    T get(List<Integer> address);

    T get(Integer... address);

    GridX<T> intersect(GridCoord x);

    <U> GridX<U> map(Function<T, U> f);

    <S, U> GridX<U> pair(GridSimple<S> slice, BiFunction<T, S, U> f);

    GridX<T> reverse(Integer... axis);

    GridX<T> reverse(TList<Integer> axis);

    /**
     * yet another reverse.
     *
     * reverse the axis designated in the parameter.
     * behavior of this implementation looks natural in terms of asList() compared to
     * that of reverse(). but after all, this implementation is only a small variation
     * of transform in comparison to the radical change of reverse().
     * @param axis
     * @return
     */
    GridX<T> reverseYA(Integer... axis);

    GridX<T> reverseYA(TList<Integer> axis);

    T set(T v, List<Integer> address);

    T set(T v, Integer... address);

    GridX<T> sfix();

    GridX<T> shift(Integer... vector);

    GridX<T> shift(List<Integer> vector);

    GridX<T> transform(TList<Integer> order, Integer... changed);

    GridX<T> transform(TList<Integer> order, TList<Integer> changed);

    GridX<T> transform(Function<TList<Integer>, TList<Integer>> f, Integer... changed);

    GridX<T> transform(Function<TList<Integer>, TList<Integer>> f, TList<Integer> changed);

    /**
     * translate to new coordination.
     * new coordination has to be included in the original one.
     * @param slice
     * @return
     */
    GridX<T> translate(GridCoord slice);
    
}
