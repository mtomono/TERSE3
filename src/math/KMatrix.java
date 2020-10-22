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
import collection.TransparentTranspose;
import static java.lang.Integer.min;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <K>
 */
public class KMatrix<K extends Decimal<K>> {
    final public MathContext<K> context;
    final public TList<TList<K>> body;
    final public int x;
    final public int y;
    public KMatrix(TList<TList<K>> body, MathContext<K> context) {
        this.body=body;
        this.context=context;
        assert body.map(r->r.size()).isUniform() : "all the raws have to have the same size";
        this.x=body.isEmpty()?0:body.get(0).size();
        this.y=body.size();
    }
    public boolean isSquare() {
        return x==y;
    }
    public void assertSquare() {
        assert isSquare() : "this method is incompatible for a matrix that is not square";
    }
    public TList<KVector<K>> rows() {
        return body.map(r->context.vector(r));
    }
    public TList<KVector<K>> columns() {
        return TransparentTranspose.transpose(body).map(r->context.vector(r)); //if you use body.transpose() instead of TransparentTranspose.transpose(), you will get some exceptions caused by trying to chnage a mapped list which doesn't have reverse map.
    }
    public KMatrix<K> subMatrix(int... fromTo) {
        int x0=fromTo[0];int y0=fromTo[1];  int x1=fromTo[2];int y1=fromTo[3];
        return context.matrix(body.subList(y0,y1).map(r->r.subList(x0,x1)));
    }
    public KMatrix<K> subMatrixUR(int x0, int y0) {
        return subMatrix(x0,y0,x,y);
    }
    public int minSize() {
        return min(x,y);
    }
    public KMatrix<K> mapR(Function<K,K> f) {
        return context.matrix(body.map(r->r.map(f)));
    }
    public KMatrix<K> mapS(Function<K,K> f) {
        body.forEach(r->r.reset(r.map(f)));
        return this;
    }
    public KMatrix<K> scaleR(K scale) {
        return context.matrix(rows().map(r->r.scaleR(scale).body));
    }
    public KMatrix<K> scaleS(K scale) {
        rows().map(r->r.scaleS(scale)).forEach(r->{});
        return this;
    }
    public KMatrix<K> invR(K scale) {
        return context.matrix(rows().map(r->r.invR(scale).body));
    }
    public KMatrix<K> invS(K scale) {
        rows().map(r->r.invS(scale)).forEach(r->{});
        return this;
    }
    public KMatrix<K> addR(KMatrix<K> other) {
        return context.matrix(rows().pair(other.rows(), (a,b)->a.addR(b).body));
    }
    public KMatrix<K> addS(KMatrix<K> other) {
        rows().pair(other.rows(), (a,b)->a.addS(b)).forEach(r->{});
        return this;
    }
    public KMatrix<K> subR(KMatrix<K> other) {
        return context.matrix(rows().pair(other.rows(), (a,b)->a.subR(b).body));
    }
    public KMatrix<K> subS(KMatrix<K> other) {
        rows().pair(other.rows(), (a,b)->a.subS(b)).forEach(r->{});
        return this;
    }
    public KMatrix<K> mul(KMatrix<K> other) {
        TList<KVector<K>> rows=rows().sfix();
        TList<KVector<K>> columns=other.columns().sfix();
        return context.matrix(rows.map(r->columns.map(c->r.dot(c))));
    }
    public KVector<K> mul(KVector<K> other) {
        return new KVector<>(rows().map(r->r.dot(other)),context);
    }
    public KMatrix<K> transpose() {
        return context.matrix(body.transposeT(l->l)).sfix();
    }
    public KMatrix<K> fillLower(K v) {
        assertSquare();
        return context.matrix(TList.range(0,x).map(i->body.get(i).subList(i,x).startFrom(TList.nCopies(i,v))));
    }
    public KMatrix<K> fillUpper(K v) {
        assertSquare();
        return context.matrix(TList.range(0,x).map(i->body.get(i).subList(0,i+1).append(TList.nCopies(x-i-1,v))));
    }
    public KMatrix<K> fillDiagonal(TList<K> diag) {
        assertSquare();
        return context.matrix(TList.range(0,x).map(i->body.get(i).replaceAt(i, diag.get(i))));
    }
    public KMatrix<K> fillDiagonal(K diag) {
        assertSquare();
        return fillDiagonal(TList.nCopies(x, diag));
    }
    public TList<K> getDiagonal() {
        assertSquare();
        return TList.range(0,x).map(i->body.get(i).get(i));
    }
    
    public boolean nonZeroDiagonal() {
        return getDiagonal().stream().allMatch(d->!d.isZero());
    }
    
    public LU<K> luDecompose() {
        return new LuDecompose<>(this).decompose();
    }
    public PLU<K> pluDecompose() {
        return new PluDecompose<>(this).decompose();
    }
    public KMatrix<K> I() {
        return context.I(body.size());
    }
    public KMatrix<K> pinv(TList<Integer> order) {
        return context.matrix(I().body.pickUp(order));
    }
    public KMatrix<K> swap(int c, ArrayInt order) {
        if (body.size()<=1)
            return this;
        int maxRow=TList.range(c,body.size()).max(i->body.get(i).get(c).abs()).get();
        body.swap(c,maxRow);
        if (body.get(c).get(c).isZero())
            throw new NonsingularMatrixException("diagonal element was 0 even after pivoting, meaning this matrix is not singular : notified from KMatrix.swap()");
            // keep this exception message simple, meaning not to include any variable, 
            //because this exception will be used to detect nonsingular matrix and 
            //in that case certain level of performance is needed for throwing this exception.
        order.swap(c, maxRow);
        return this;
    }
    public TList<KMatrix<K>> doolittleFormat() {
        return TList.sof(doolittleLower(),doolittleUpper());
    }
    public KMatrix<K> doolittleLower() {
        return fillUpper(context.zero()).fillDiagonal(context.one());
    }
    public KMatrix<K> doolittleUpper() {
        return fillLower(context.zero());
    }
    public KMatrix<K> doolittleSubMatrix() {
        if (body.get(0).get(0).isZero())
            throw new PivotingMightBeRequiredException("lu decomposition encountered 0 diagonal element:"+ toString() +": notified from KMatrix.doolittleStep");
        KVector<K>        lcolumn    =subMatrix(0,1, 1,y).columns().get(0).invS(body.get(0).get(0));
        KVector<K>        eliminator =subMatrix(1,0, x,1).rows().get(0);
        TList<KVector<K>> eliminated =subMatrix(1,1,x,y).rows();
        lcolumn.body.pair(eliminated,(l,u)->u.subS(eliminator.scale(l))).forEach(r->{});
        return this;
    }
    /**
     * forward substitution for lower triangle matrix.
     * @param b
     * @return 
     */
    public KVector<K> forwardSubstitution(KVector<K> b) {
        TList<K> retval= TList.c();
        TList<KVector<K>> rows=rows();
        TList<KVector<K>> L=rows.index().map(i->rows.get(i).subVector(0, i+1));
        b.body.pair(L, (bn,ln)->bn.sub(ln.seek(-1).dot(new KVector<>(retval,context))).div(ln.body.last())).forEach(v->retval.add(v));
        return new KVector<>(retval,context);
    }
    public KMatrix<K> u2l() {
        return new KMatrix<>(body.map(v->v.reverse()).reverse(),context);
    }
    /**
     * backward substitution for upper triangle matrix.
     * @param b
     * @return 
     */
    public KVector<K> backwardSubstitution(KVector<K> b) {
        return u2l().forwardSubstitution(b.reverse()).reverse();
    }
    public KMatrix<K> invLower() {
        return context.matrix(context.I(body.size()).columns().map(c->forwardSubstitution(c).body)).transpose();
    }
    public KMatrix<K> invUpper() {
        return context.matrix(context.I(body.size()).columns().map(c->backwardSubstitution(c).body)).transpose();
    }
    public KMatrix<K> sfix() {
        return context.matrix(body.map(r->r.sfix()).sfix());
    }
    public boolean same(KMatrix<K> other) {
        return body.flatMap(l->l).pair(other.body.flatMap(l->l), (a,b)->a.same(b)).forAll(b->b);
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KMatrix)) {
            return false;
        }
        KMatrix<K> t = (KMatrix<K>) e;
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
