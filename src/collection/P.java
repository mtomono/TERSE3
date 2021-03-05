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
import debug.Monitorable;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import string.Message;

/**
 * P(air) of objects.
 * this is the very the basis for typed list of objects.
 * by using this type recursively, you can build any sequence of objects which
 * are typed.
 * @author mtomono
 * @param <L>
 * @param <R>
 */
public class P<L, R> extends AbstractList<Object> implements Monitorable {
    L l;
    R r;
    
    public P(L l, R r) {
        this.l = l;
        this.r = r;
    }
    
    public static <L, R> P<L, R> p(L l, R r) {
        return new P<>(l, r);
    }
    
    public static <L, R> P<L,R> f(L l, Function<L,R> f) {
        return P.p(l,f.apply(l));
    }
    
    public static <L, R> Optional<P<L, R>> op(Optional<L> l, Optional<R> r) {
        return l.isPresent() && r.isPresent() ? Optional.of(P.p(l.get(), r.get())) : Optional.empty();
    }
    
    public static <L, R> Optional<P<L, R>> op(P<Optional<L>, Optional<R>> p) {
        return op(p.l(), p.r());
    }
    
    public L l() {
        return l;
    }
    
    public R r() {
        return r;
    }
    
    public <RR> P<P<L, R>, RR> c(RR rr) {
        return new P<>(this, rr);
    }
    
    public boolean test(BiPredicate<L,R> test) {
        return test.test(l, r);
    }
    
    public <S> S map(BiFunction<L,R,S> map) {
        return map.apply(l, r);
    }
    
    @Override
    public boolean equals(Object e) {
        if (e==null)
            return false;
        if (!(e instanceof P))
            return false;
        P t = (P) e;
        if (l!=null&&r!=null)
            return l.equals(t.l)&&r.equals(t.r);
        if (l==null&&r!=null)
            return t.l==null&&r.equals(t.r);
        if (l!=null&&r==null)
            return l.equals(t.r)&&t.r==null;
        return l==t.l&&r==t.r;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.l);
        hash = 79 * hash + Objects.hashCode(this.r);
        return hash;
    }
    
    @Override
    public String toString() {
        return Message.nl().csv(a2l(l,r)).p("{", "}").toString();
    }
    
    TList<Object> toList(Object o) {
        if (o instanceof P)
            return TList.set(((P)o));
        else
            return TList.wrap(o);
    }
    
    public TList<Object> toList() {
        return toList(l).append(toList(r));
    }
    
    @Override
    public Object[] toArray() {
        return toList().toArray();
    }

    @Override
    public Object get(int index) {
        if (index==0)
            return l;
        if (index==1)
            return r;
        throw new NoSuchElementException("P has only 2 elements.");
    }

    @Override
    public int size() {
        return 2;
    }
    
    public <U> P<P<L,R>,U> addP(U a) {
        return p(this, a);
    }
    
    public P<R,L> flip() {
        return P.p(r,l);
    }
    
    @Override
    public String monitor() {
        return "P:\n"+indent(l)+indent(r);
    }

}
