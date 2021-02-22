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

import collection.ArrayInt;
import collection.TList;
import collection.TListWrapper;
import collection.TransparentTranspose;
import debug.Te;
import static java.lang.Integer.min;
import java.util.Objects;
import java.util.function.Function;


/**
 *
 * @author masao
 * @param <K>
 */
public class CMatrix<K extends Comparable<K>> implements TListWrapper<TList<C<K>>,CMatrix<K>>{
    final public MathBuilder<K> b;
    final public TList<TList<C<K>>> body;
    final public int x;
    final public int y;
    public CMatrix(MathBuilder<K> b, TList<TList<C<K>>> body) {
        this.body=body;
        this.b=b;
        assert body.map(r->r.size()).isUniform() : "all the raws have to have the same size";
        this.x=body.isEmpty()?0:body.get(0).size();
        this.y=body.size();
    }
    @Override
    public TList<TList<C<K>>> body() {
        return body;
    }
    @Override
    public CMatrix<K> wrap(TList<TList<C<K>>> body) {
        return new CMatrix<>(b,body);
    }
    @Override
    public CMatrix<K> self() {
        return this;
    }
    public boolean isSquare() {
        return x==y;
    }
    public void assertSquare() {
        assert isSquare() : "this method is incompatible for a matrix that is not square";
    }
    public TList<CList<K>> rows() {
        return body.map(r->new CList<>(b.context, r));
    }
    public TList<CList<K>> columns() {
        return TransparentTranspose.transpose(body).map(r->new CList<>(b.context,r)); //if you use body.transpose() instead of TransparentTranspose.transpose(), you will get some exceptions caused by trying to chnage a mapped list which doesn't have reverse map.
    }
    public CMatrix<K> subMatrix(int... fromTo) {
        int x0=fromTo[0];int y0=fromTo[1];  int x1=fromTo[2];int y1=fromTo[3];
        return wrap(body.subList(y0,y1).map(r->r.subList(x0,x1)));
    }
    public CMatrix<K> subMatrixUR(int x0, int y0) {
        return subMatrix(x0,y0,x,y);
    }
    public int minSize() {
        return min(x,y);
    }
    public CMatrix<K> map(Function<C<K>,C<K>> f) {
        return wrap(body.map(r->r.map(f)));
    }
    public CMatrix<K> scale(C<K> scale) {
        return wrap(rows().map(r->r.scale(scale).body()));
    }
    public CMatrix<K> add(CMatrix<K> other) {
        return wrap(rows().pair(other.rows(), (a,b)->a.add(b).body()));
    }
    public CMatrix<K> sub(CMatrix<K> other) {
        return wrap(rows().pair(other.rows(), (a,b)->a.sub(b).body()));
    }
    public CMatrix<K> mul(CMatrix<K> other) {
        TList<CList<K>> rows=rows().sfix();
        TList<CList<K>> columns=other.columns().sfix();
        return wrap(rows.map(r->columns.map(c->r.dot(c))));
    }
    public CList<K> mul(CList<K> other) {
        return new CList<>(b.context,rows().map(r->r.dot(other)));
    }
    public CMatrix<K> transpose() {
        return wrap(body.transposeT(l->l)).sfix();
    }
    public CMatrix<K> fillLower(C<K> v) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).subList(i,x).startFrom(TList.nCopies(i,v))));
    }
    public CMatrix<K> fillUpper(C<K> v) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).subList(0,i+1).append(TList.nCopies(x-i-1,v))));
    }
    public CMatrix<K> fillDiagonal(TList<C<K>> diag) {
        assertSquare();
        return wrap(TList.range(0,x).map(i->body.get(i).replaceAt(i, diag.get(i))));
    }
    public CMatrix<K> fillDiagonal(C<K> diag) {
        assertSquare();
        return fillDiagonal(TList.nCopies(x, diag));
    }
    public TList<C<K>> getDiagonal() {
        assertSquare();
        return TList.range(0,x).map(i->body.get(i).get(i));
    }
    
    public boolean nonZeroDiagonal() {
        return getDiagonal().stream().allMatch(d->b.compare.ne(d,b.context.zero()));
    }
    
    public LU<K> luDecompose() {
        return new LuDecompose<>(this).decompose();
    }
    public PLU<K> pluDecompose() {
        return new PluDecompose<>(this).decompose();
    }
    public CMatrix<K> i() {
        assertSquare();
        return b.I(x);
    }
    public CMatrix<K> pinv(TList<Integer> order) {
        return wrap(i().body.pickUp(order));
    }
    public CMatrix<K> swap(int c, ArrayInt order) {
        if (body.size()<=1)
            return this;
        int maxRow=TList.range(c,body.size()).max(i->body.get(i).get(c).abs().body()).get();
        body.swap(c,maxRow);
        if (b.compare.eq(body.get(c).get(c),b.context.zero()))
            throw new NonsingularMatrixException("diagonal element was 0 even after pivoting, meaning this matrix is not singular : notified from CMatrix.swap()");
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        order.swap(c, maxRow);
        return this;
    }
    public TList<CMatrix<K>> doolittleFormat() {
        return TList.sof(doolittleLower(),doolittleUpper());
    }
    public CMatrix<K> doolittleLower() {
        return fillUpper(b.context.zero()).fillDiagonal(b.context.one());
    }
    public CMatrix<K> doolittleUpper() {
        return fillLower(b.context.zero());
    }
    public CMatrix<K> doolittleSubMatrix() {
        if (b.compare.eq(body.get(0).get(0),b.context.zero()))
            throw new PivotingMightBeRequiredException("lu decomposition encountered 0 diagonal element:"+ toString() +": notified from CMatrix.doolittleStep");
        CList<K>        lcolumn    =subMatrix(0,1, 1,y).columns().get(0).reset(cl->cl.scale(body.get(0).get(0).inv()));
        CList<K>        eliminator =subMatrix(1,0, x,1).rows().get(0);
        TList<CList<K>> eliminated =subMatrix(1,1,x,y).rows();
        lcolumn.body.pair(eliminated,(l,u)->u.reset(cl->cl.sub(eliminator.scale(l)))).forEach(r->{}); 
        return this;
    }
    /**
     * forward substitution for lower triangle matrix.
     * @param bb
     * @return 
     */
    public CList<K> forwardSubstitution(CList<K> bb) {
        TList<C<K>> retval= TList.c();
        TList<CList<K>> rows=rows();
        TList<CList<K>> L=rows.index().map(i->rows.get(i).m(l->l.subList(0, i+1)));
        bb.body.pair(L, (bn,ln)->bn.sub(ln.m(l->l.seek(-1)).dot(new CList<>(b.context,retval))).div(ln.body.last())).forEach(v->retval.add(v));
        return new CList<>(b.context,retval);
    }
    public CMatrix<K> u2l() {
        return wrap(body.map(v->v.reverse()).reverse());
    }
    /**
     * backward substitution for upper triangle matrix.
     * @param b
     * @return 
     */
    public CList<K> backwardSubstitution(CList<K> b) {
        return u2l().forwardSubstitution(b.m(l->l.reverse())).m(l->l.reverse());
    }
    public CMatrix<K> invLower() {
        return wrap(i().columns().map(c->forwardSubstitution(c).body)).transpose();
    }
    public CMatrix<K> invUpper() {
        return wrap(i().columns().map(c->backwardSubstitution(c).body)).transpose();
    }
    public CMatrix<K> sfix() {
        return wrap(body.map(r->r.sfix()).sfix());
    }
    public boolean same(CMatrix<K> other) {
        return body.flatMap(l->l).pair(other.body.flatMap(l->l), (x,y)->b.compare.eq(x,y)).forAll(b->b);
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof CMatrix)) {
            return false;
        }
        CMatrix<K> t = (CMatrix<K>) e;
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
