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
package math2;

import collection.TList;
import collection.TListWrapper;
import function.Transformable;

/**
 * 
 * @author masao
 * @param <K>
 */
public class CList<K> implements TListWrapper<K,CList<K>>,Transformable<CList<K>>{
    public final C.Builder<K> b;
    final TList<K> body;
    public CList(C.Builder<K> context, TList<K> body) {
        this.body=body;
        this.b=context;
    }
    static public <K> CList<K> c(C.Builder<K> context, K... v) {
        return new CList<>(context,TList.sof(v));
    }
    @Override
    public TList<K> body() {
        return body;
    }
    @Override
    public CList<K> wrap(TList<K> body) {
        return new CList<>(b,body);
    }
    @Override
    public CList<K> self() {
        return this;
    }
    public C<K> average() {
        return sigma().div(b.b(body.size()));
    }
    public C<K> sampleAverage() {
        return sigma().div(b.b(body.size()-1));
    }
    public TList<C<K>> toC() {
        return body.map(v->b.b(v));
    }
    public CList<K> fromC(TList<C<K>> body) {
        return wrap(body.map(c->c.v));
    }

    public C<K> sigma() {
        return toC().stream().reduce(b.zero(),(a,b)->a.add(b));
    }
    
    public C<K> pai() {
        return toC().stream().reduce(b.one(),(a,b)->a.mul(b));
    }
    
    public CList<K> scalar(C<K> s) {
        return fromC(toC().map(v->v.mul(s)));
    }
    public CList<K> scalar(K s) {
        return scalar(b.b(s));
    }
    
    public CList<K> add(CList<K> o) {
        return o.fromC(toC().pair(o.toC(), (a,b)->a.add(b)));
    }
    public CList<K> add(TList<K> o) {
        return add(new CList<>(b, o));
    }
        
    public CList<K> sub(CList<K> o) {
        return o.fromC(toC().pair(o.toC(), (a,b)->a.sub(b)));
    }
    public CList<K> sub(TList<K> o) {
        return sub(new CList<>(b, o));
    }
        
    public CList<K> mul(CList<K> o) {
        return o.fromC(toC().pair(o.toC(), (a,b)->a.mul(b)));
    }
    public CList<K> mul(TList<K> o) {
        return mul(new CList<>(b, o));
    }
        
    public CList<K> div(CList<K> o) {
        return o.fromC(toC().pair(o.toC(), (a,b)->a.div(b)));
    }
    public CList<K> div(TList<K> o) {
        return div(new CList<>(b, o));
    }
    
    public C<K> dot(CList<K> o) {
        return mul(o).sigma();
    }
    public C<K> dot(TList<K> o) {
        return dot(new CList<>(b, o));
    }
        
    public CList<K> add() {
        return fromC(toC().accumFromStart((a,b)->a.add(b)));
    }

    public CList<K> mul() {
        return fromC(toC().accumFromStart((a,b)->a.mul(b)));
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
