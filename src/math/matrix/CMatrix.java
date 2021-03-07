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
package math.matrix;

import collection.TList;
import collection.TListWrapper;
import collection.TransparentTranspose;
import function.Transformable;
import static java.lang.Integer.min;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;
import math.C2;
import math.CList;
import math.Context;
import math.ContextBuilder;
import math.ContextOrdered;
import math.PivotingMightBeRequiredException;
import math.Rational;


/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class CMatrix<K, T extends Context<K,T>&ContextOrdered<K,T>> implements TListWrapper<TList<T>,CMatrix<K,T>>,Transformable<CMatrix<K,T>>{
    public static Builder<Double,C2<Double>,C2.Builder<Double>> d=b(C2.d);
    public static Builder<Double,C2<Double>,C2.Builder<Double>> dc=b(C2.dc);
    public static Builder<Double,C2<Double>,C2.Builder<Double>> derr=derr(1e-10);
    public static Builder<Double,C2<Double>,C2.Builder<Double>> derr(double err) {
        return b(C2.derr(err));
    }
    public static Builder<BigDecimal,C2<BigDecimal>,C2.Builder<BigDecimal>> bd=b(C2.bd);
    public static Builder<Rational,C2<Rational>,C2.Builder<Rational>> r=b(C2.r);
    public static <K, T extends Context<K,T>&ContextOrdered<K,T>, B extends ContextBuilder<K,T>> Builder<K,T,B> b(B b) {
        return new Builder<>(b);
    }
    static public class Builder<K, T extends Context<K,T>&ContextOrdered<K,T>, B extends ContextBuilder<K,T>> {
        B b;
        public Builder(B b) {
            this.b=b;
        }
        public CMatrix<K,T> b(TList<TList<T>> body) {
            return new CMatrix<>(b, body);
        }

        public CMatrix<K,T> b(Integer[][] matrix) {
            return Builder.this.b(TList.sof(matrix).map((a) -> TList.sof(a).map((i) -> b.b(i)).sfix()).sfix());
        }

        public CMatrix<K,T> b(String source) {
            return Builder.this.b(TList.sof(source.split(";")).map((r) -> TList.sof(r.split(",")).map((s) -> b.b(s)).sfix()).sfix());
        }
        
        public CMatrix<K,T> i(int n) {
            return b(b.i(n));
        }
    }
    final public ContextBuilder<K,T> bb;
    final public TList<TList<T>> body;
    final public int x;//row
    final public int y;//column
    public CMatrix(ContextBuilder<K,T> bb, TList<TList<T>> body) {
        this.body=body;
        this.bb=bb;
        assert body.map(r->r.size()).isUniform() : "all the raws have to have the same size";
        this.x=body.size();
        this.y=body.isEmpty()?0:body.get(0).size();
    }
    @Override
    public TList<TList<T>> body() {
        return body;
    }
    @Override
    public CMatrix<K,T> wrap(TList<TList<T>> body) {
        return new CMatrix<>(bb,body);
    }
    @Override
    public CMatrix<K,T> self() {
        return this;
    }
    public boolean isSquare() {
        return x==y;
    }
    public void assertSquare() {
        assert isSquare() : "this method is incompatible for a matrix that is not square";
    }
    public TList<CList<K,T>> rows() {
        return body.map(r->new CList<>(bb, r));
    }
    public TList<CList<K,T>> columns() {
        return TransparentTranspose.transpose(body).map(r->new CList<>(bb,r)); //if you use body.transpose() instead of TransparentTranspose.transpose(), you will get some exceptions caused by trying to chnage a mapped list which doesn't have reverse map.
    }
    public T get(int x0, int y0) {
        return body.get(x0).get(y0);
    }
    public CMatrix<K,T> subMatrix(int... fromTo) {
        int x0=fromTo[0];int y0=fromTo[1];  int x1=fromTo[2];int y1=fromTo[3];
        return wrap(body.subList(x0,x1).map(r->r.subList(y0,y1)));
    }
    public CMatrix<K,T> subRows(int from, int to) {
        return wrap(body.subList(from,to));
    }
    public CMatrix<K,T> subRows(int seek) {
        return seek<0?subRows(0,x+seek):subRows(seek,x);
    }
    public CMatrix<K,T> subColumns(int from, int to) {
        return wrap(body.map(r->r.subList(from,to)));
    }
    public CMatrix<K,T> subColumns(int seek) {
        return seek<0?subColumns(0,y+seek):subColumns(seek,y);
    }
    public CMatrix<K,T> subMatrixLR(int x0, int y0) {
        return subMatrix(x0,y0,x,y);
    }
    public int minSize() {
        return min(x,y);
    }
    public CMatrix<K,T> map(Function<T,T> f) {
        return wrap(body.map(r->r.map(f)));
    }
    public CMatrix<K,T> scale(T scale) {
        return wrap(rows().map(r->r.scale(scale).body()));
    }
    public CMatrix<K,T> add(CMatrix<K,T> other) {
        return wrap(rows().pair(other.rows(), (a,b)->a.add(b).body()));
    }
    public CMatrix<K,T> sub(CMatrix<K,T> other) {
        return wrap(rows().pair(other.rows(), (a,b)->a.sub(b).body()));
    }
    public CMatrix<K,T> mul(CMatrix<K,T> other) {
        TList<CList<K,T>> rows=rows().sfix();
        TList<CList<K,T>> columns=other.columns().sfix();
        return wrap(rows.map(r->columns.map(c->r.dot(c))));
    }
    public CList<K,T> mul(CList<K,T> other) {
        return new CList<>(bb,rows().map(r->r.dot(other)));
    }
    public CMatrix<K,T> transpose() {
        return wrap(body.transposeT(l->l)).sfix();
    }
    public CMatrix<K,T> fillLower(T v) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).subList(i,y).startFrom(TList.nCopies(i,v))));
    }
    public CMatrix<K,T> fillUpper(T v) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).subList(0,i+1).append(TList.nCopies(y-i-1,v))));
    }
    public CMatrix<K,T> fillDiagonal(TList<T> diag) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).replaceAt(i, diag.get(i))));
    }
    public CMatrix<K,T> fillDiagonal(T diag) {
        assertSquare();
        return fillDiagonal(TList.nCopies(x, diag));
    }
    public TList<T> getDiagonal() {
        assertSquare();
        return TList.range(0,x).map(i->body.get(i).get(i));
    }
    
    public boolean nonZeroDiagonal() {
        return getDiagonal().stream().allMatch(diag->!diag.isZero());
    }
    
    public LU<K,T> luDecompose() {
        return new LuDecompose<>(this).decompose();
    }
    public PLU<K,T> pluDecompose() {
        return new PluDecompose<>(this).decompose();
    }

    public CMatrix<K,T> i() {
        assertSquare();
        return wrap(bb.i(x));
    }
    public CMatrix<K,T> reorder(TList<Integer> order) {
        return wrap(i().body.pickUp(order));
    }
    /**
     * forward substitution for lower triangle matrix.
     * @param b
     * @return 
     */
    public CList<K,T> forwardSubstitution(CList<K,T> b) {
        TList<T> retval= TList.c();
        TList<CList<K,T>> rows=rows();
        TList<CList<K,T>> L=rows.index().map(i->rows.get(i).m(l->l.subList(0, i+1)));
        b.body().pair(L, (bn,ln)->bn.sub(ln.m(l->l.seek(-1)).dot(new CList<>(bb,retval))).div(ln.body().last())).forEach(v->retval.add(v));
        return new CList<>(bb,retval);
    }
    public CMatrix<K,T> u2l() {
        return wrap(body.map(v->v.reverse()).reverse());
    }
    /**
     * backward substitution for upper triangle matrix.
     * @param b
     * @return 
     */
    public CList<K,T> backwardSubstitution(CList<K,T> b) {
        return u2l().forwardSubstitution(b.m(l->l.reverse())).m(l->l.reverse());
    }
    public CMatrix<K,T> invLower() {
        return wrap(i().columns().map(c->forwardSubstitution(c).body())).transpose();
    }
    public CMatrix<K,T> invUpper() {
        return wrap(i().columns().map(c->backwardSubstitution(c).body())).transpose();
    }
    /**
     * inversion of Q in QR method.
     * Q means Orthogonal Metrix.
     * @return 
     */
    CMatrix<K,T> qinv() {// Q stands for Orthogonal Matrix
        return wrap(GramSchmidt.orthogonalize(columns()).map(p->p.body()));
    }
    /**
     * Q in QR method.
     * Q means Orthogonal Metrix.
     * @return 
     */
    CMatrix<K,T> q() {
        return qinv().transpose();
    }
    /**
     * R in QR method.
     * @return 
     */
    CMatrix<K,T> r() {//R  stands for R of QR method.
        return qinv().mul(this).fillLower(bb.zero());
    }

    public CMatrix<K,T> sfix() {
        return wrap(body.map(r->r.sfix()).sfix());
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof CMatrix)) {
            return false;
        }
        CMatrix<K,T> t = (CMatrix<K,T>) e;
        return t.body.equals(body);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public String toString() {
        return body.toString();
    }

}
