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
package math;

import collection.TList;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 * @param <K>
 */
public class KVector<K extends Decimal<K>> {
    TList<K> body;
    MathContext<K> context;
    public KVector(TList<K> body, MathContext<K> context) {
        this.body=body;
        this.context=context;
    }
    public KVector<K> seek(int i) {
        return new KVector<>(body.seek(i),context);
    }
    public KVector<K> subVector(int start,int end) {
        return new KVector<>(body.subList(start,end),context);
    }
    public KVector<K> reverse() {
        return new KVector<>(body.reverse(),context);
    }
    public KVector<K> map(UnaryOperator<K> f) {
        return new KVector<>(body.map(f),context);
    }
    public KVector<K> scale(K scale) {
        return scaleR(scale);
    }
    public KVector<K> scaleR(K scale) {
        return context.vector(body.map(v->v.mul(scale)));
    }
    public KVector<K> scaleS(K scale) {
        body.reset(body.map(v->v.mul(scale)));
        return this;
    }
    public KVector<K> inv(K scale) {
        return invR(scale);
    }
    public KVector<K> invR(K scale) {
        return context.vector(body.map(v->v.div(scale)));
    }
    public KVector<K> invS(K scale) {
        body.reset(body.map(v->v.div(scale)));
        return this;
    }
    public KVector<K> add(KVector<K> other) {
        return addR(other);
    }
    public KVector<K> addR(KVector<K> other) {
        return context.vector(body.pair(other.body,(a,b)->a.add(b)));
    }
    public KVector<K> addS(KVector<K> other) {
        body.reset(body.pair(other.body,(a,b)->a.add(b)));
        return this;
    }
    public KVector<K> sub(KVector<K> other) {
        return subR(other);
    }
    public KVector<K> subR(KVector<K> other) {
        return context.vector(body.pair(other.body,(a,b)->a.sub(b)));
    }
    public KVector<K> subS(KVector<K> other) {
        body.reset(body.pair(other.body,(a,b)->a.sub(b)));
        return this;
    }
    public K dot(KVector<K> other) {
        return body.pair(other.body,(a,b)->a.mul(b)).toK(context).sigma(k->k);
    }
    public K get(int i) {
        return body.get(i);
    }
    
    public boolean same(KVector<K> other) {
        return body.pair(other.body, (a,b)->a.same(b)).forAll(b->b);
    }

    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KVector)) {
            return false;
        }
        KVector<K> t = (KVector<K>) e;
        return t.body.equals(body);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
