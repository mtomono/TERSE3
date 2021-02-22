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
import java.util.function.Function;

/**
 * 
 * @author masao
 * @param <K>
 */
public class CList<K> implements TListWrapper<C<K>,CList<K>>,Transformable<CList<K>>{
    public final C.Builder<K> b;
    final TList<C<K>> body;
    public CList(C.Builder<K> context, TList<C<K>> body) {
        this.body=body;
        this.b=context;
    }
    static public <K> CList<K> c(C.Builder<K> context, TList<K> vs) {
        return new CList<>(context, vs.map(v->context.c(v)));
    }
    static public <K> CList<K> c(C.Builder<K> context, K... v) {
        return c(context,TList.sof(v));
    }
    @Override
    public TList<C<K>> body() {
        return body;
    }
    @Override
    public CList<K> wrap(TList<C<K>> body) {
        return new CList<>(b,body);
    }
    @Override
    public CList<K> self() {
        return this;
    }
    public CList<K> reset(Function<CList<K>,CList<K>> f) {
        body.reset(f.apply(this).body());
        return this;
    }
    public C<K> average() {
        return sigma().div(b.b(body.size()));
    }
    public C<K> sampleAverage() {
        return sigma().div(b.b(body.size()-1));
    }

    public C<K> sigma() {
        return body.stream().reduce(b.zero(),(a,b)->a.add(b));
    }
    
    public C<K> pai() {
        return body.stream().reduce(b.one(),(a,b)->a.mul(b));
    }
    
    public CList<K> scale(C<K> s) {
        return wrap(body.map(v->v.mul(s)));
    }
    public CList<K> scale(K s) {
        return scale(b.c(s));
    }
    public CList<K> inv() {
        return wrap(body.map(v->v.inv()));
    }
    public CList<K> negate() {
        return wrap(body.map(v->v.negate()));
    }
    public CList<K> abs() {
        return wrap(body.map(v->v.abs()));
    }
    
    public CList<K> add(CList<K> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.add(b)));
    }
    public CList<K> add(TList<K> o) {
        return add(c(b,o));
    }
        
    public CList<K> sub(CList<K> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.sub(b)));
    }
    public CList<K> sub(TList<K> o) {
        return sub(c(b,o));
    }
        
    public CList<K> mul(CList<K> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.mul(b)));
    }
    public CList<K> mul(TList<K> o) {
        return mul(c(b,o));
    }
        
    public CList<K> div(CList<K> o) {
        return o.wrap(body.pair(o.body, (a,b)->a.div(b)));
    }
    public CList<K> div(TList<K> o) {
        return div(c(b,o));
    }
    
    public CList<K> interpolate(C<K> rate, CList<K> o, C<K> orate) {
        return o.wrap(body.pair(o.body, (a,b)->a.interpolate(rate,b,orate)));
    }
    
    public CList<K> interpolate1(C<K> rate, CList<K> o) {
        return interpolate(rate,o, b.one().sub(rate));
    }
    
    public CList<K> interpolate100(C<K> rate, CList<K> o) {
        return interpolate(rate,o, b.b(100).sub(rate));
    }
    
    public C<K> dot(CList<K> o) {
        return mul(o).sigma();
    }
    public C<K> dot(TList<K> o) {
        return dot(c(b,o));
    }
        
    public CList<K> add() {
        return wrap(body.accumFromStart((a,b)->a.add(b)));
    }

    public CList<K> mul() {
        return wrap(body.accumFromStart((a,b)->a.mul(b)));
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
