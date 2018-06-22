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

package orderedSet;

import collection.TList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * TList of Comparators.
 * provides convenient ways to define composite comparators.
 * @author masao
 * @param <T>
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
     * @return 
     */
    public Comparator<T> compile() {
        return body.stream().reduce((a,b)->a.thenComparing(b)).get();
    }
}
