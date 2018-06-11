/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import collection.TList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * TList of Comparators.
 * provides convenient ways to define composite comparators.
 * @author masao
 */
public class Comparators<T> {
    TList<Comparator<T>> body;
    /**
     * convenient method to create comparator.
     * @param <T>
     * @param c
     * @return 
     */
    static public <T> Comparator<T> c(Comparator<T> c) {
        return c;
    }
    
    public Comparators(TList<Comparator<T>> body) {
        this.body = body;
    }
    
    static public <T> Comparators<T> sof(Comparator<T>... comps) {
        assert comps.length > 0 : "Comparators#sof: parameter comps is empty";
        return new Comparators<>(TList.sof(comps));
    }
    
    static public <T> Comparators<T> sof(Function<T, Comparable>...comps) {
        assert comps.length > 0 : "Comparators#sof: parameter comps is empty";
        return new Comparators<>(TList.sof(comps).map(f->Comparator.comparing(f)));
    }

    public Comparators<T> reverse() {
        return new Comparators<>(body.reverse());
    }
    
    public Comparators<T> addOne(Comparator<T> one) {
        body.addOne(one);
        return this;
    }
    
    public Comparators<T> append(List<Comparator<T>>... appended) {
        return new Comparators<>(body.append(appended));
    }
    
    /**
     * comparator combinator.
     * equivalent of chained application of thenComparing()
     * @param <T>
     * @param comps
     * @return 
     */
    public Comparator<T> compile() {
        return body.stream().reduce((a,b)->a.thenComparing(b)).get();
    }
}
