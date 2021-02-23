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
import collection.TListWrapper;
import function.Transformable;

/**
 * 
 * @author masao
 * @param <K>
 */
public class CList<K, T extends Context<K,T>> implements TListWrapper<T,CList<K,T>>,Transformable<CList<K,T>>{
    public final ContextBuilder<K,T> b;
    final TList<T> body;
    public CList(ContextBuilder<K,T> context, TList<T> body) {
        this.body=body;
        this.b=context;
    }
    static public <K, T extends Context<K,T>> CList<K,T> c(ContextBuilder<K,T> context, TList<K> vs) {
        return new CList<>(context, vs.map(v->context.c(v)));
    }
    static public <K, T extends Context<K,T>> CList<K,T> c(ContextBuilder<K,T> context, K... v) {
        return c(context,TList.sof(v));
    }
    @Override
    public TList<T> body() {
        return body;
    }
    @Override
    public CList<K,T> wrap(TList<T> body) {
        return new CList<>(b,body);
    }
    @Override
    public CList<K,T> self() {
        return this;
    }
    public T average() {
        return sigma().div(b.b(body.size()));
    }
    public T sampleAverage() {
        return sigma().div(b.b(body.size()-1));
    }

    public T sigma() {
        return body.stream().reduce(b.zero(),(a,b)->a.add(b));
    }
    
    public T pai() {
        return body.stream().reduce(b.one(),(a,b)->a.mul(b));
    }
    
    public CList<K,T> add() {
        return wrap(body.accumFromStart((a,b)->a.add(b)));
    }

    public CList<K,T> mul() {
        return wrap(body.accumFromStart((a,b)->a.mul(b)));
    }
    
    public T dot(CList<K,T> o) {
        return mul(o).sigma();
    }
    public T dot(TList<K> o) {
        return dot(c(b,o));
    }
        
    public CList<K,T> scale(T s) {
        return wrap(body.map(v->v.mul(s)));
    }
    public CList<K,T> scale(K s) {
        return scale(b.c(s));
    }
    public CList<K,T> inv() {
        return wrap(body.map(v->v.inv()));
    }
    public CList<K,T> negate() {
        return wrap(body.map(v->v.negate()));
    }
    public CList<K,T> abs() {
        return wrap(body.map(v->v.abs()));
    }
    
    public CList<K,T> add(CList<K,T> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.add(b)));
    }
    public CList<K,T> add(TList<K> o) {
        return add(c(b,o));
    }
        
    public CList<K,T> sub(CList<K,T> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.sub(b)));
    }
    public CList<K,T> sub(TList<K> o) {
        return sub(c(b,o));
    }
        
    public CList<K,T> mul(CList<K,T> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.mul(b)));
    }
    public CList<K,T> mul(TList<K> o) {
        return mul(c(b,o));
    }
        
    public CList<K,T> div(CList<K,T> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.div(b)));
    }
    public CList<K,T> div(TList<K> o) {
        return div(c(b,o));
    }
    
    public CList<K,T> interpolate(T rate, CList<K,T> o, T orate) {
        return o.wrap(body.pair(o.body, (a,b)->a.interpolate(rate,b,orate)));
    }
    
    public CList<K,T> interpolate1(T rate, CList<K,T> o) {
        return interpolate(rate,o, b.one().sub(rate));
    }
    
    public CList<K,T> interpolate100(T rate, CList<K,T> o) {
        return interpolate(rate,o, b.b(100).sub(rate));
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof CList)) {
            return false;
        }
        CList t = (CList) e;
        return body.equals(t.body);
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
