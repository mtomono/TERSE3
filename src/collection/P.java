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
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import string.Message;

/**
 * P(air) of objects
 * @author mtomono
 */
public class P<L, R> extends AbstractList<Object>{
    L l;
    R r;
    
    public P(L l, R r) {
        this.l = l;
        this.r = r;
    }
    
    public static <L, R> P<L, R> p(L l, R r) {
        return new P(l, r);
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
    
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof P))
            return false;
        P t = (P) e;
        return t.l.equals(this.l)&&t.r.equals(this.r);
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
}
