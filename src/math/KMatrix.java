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
import static collection.TransparentTranspose.transpose;
import debug.Te;
import static java.lang.Integer.min;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <K>
 */
public class KMatrix<K extends Decimal<K>> {
    MathContext<K> context;
    TList<TList<K>> body;
    int x;
    int y;
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
        return transpose(body).map(r->context.vector(r));
    }
    public KMatrix<K> subMatrix(int... fromTo) {
        int x0=fromTo[0];int y0=fromTo[1];  int x1=fromTo[2];int y1=fromTo[3];
        return context.matrix(body.subList(y0,y1).map(r->r.subList(x0,x1)));
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
    public TList<KMatrix<K>> lu() {
        assertSquare();
        KMatrix<K> doolittle=sfix().doolittle();
        return TList.sof(
                doolittle.fillUpper(context.zero()).fillDiagonal(context.one()),
                doolittle.fillLower(context.zero())
        );
    }
    public KMatrix<K> doolittle() {
        TList.range(0,min(x,y)-1).forEach(i->subMatrix(i,i,x,y).doolittleStep());
        return this;
    }
    public KMatrix<K> doolittleStep() {
        KVector<K>        lcolumn    =subMatrix(0,1, 1,y).columns().get(0).invS(body.get(0).get(0));
        KVector<K>        eliminator =subMatrix(1,0, x,1).rows().get(0);
        TList<KVector<K>> eliminated =subMatrix(1,1,x,y).rows();
        lcolumn.body.pair(eliminated,(l,u)->u.subS(eliminator.scale(l))).forEach(r->{});
        return this;
    }
    public KVector<K> forwardSubstitution(KVector<K> b) {
        TList<K> retval= TList.c();
        TList<KVector<K>> rows=rows();
        TList<KVector<K>> L=rows.index().map(i->rows.get(i).subVector(0, i+1));
        b.body.pair(L, (bn,ln)->bn.sub(ln.seek(-1).dot(new KVector<>(retval,context))).div(ln.body.last())).forEach(v->retval.add(v));
        return new KVector<>(retval,context);
    }
    public KVector<K> backwardSubstitution(KVector<K> b) {
        TList<K> retval= TList.c();
        TList<KVector<K>> rows=rows();
        TList<KVector<K>> L=Te.e(rows.index().map(i->rows.get(i).subVector(i,rows.size()).reverse()).reverse(),l->l.toWrappedString());
        b.reverse().body.pair(L, (bn,ln)->bn.sub(ln.seek(-1).dot(new KVector<>(retval,context))).div(ln.body.last())).forEach(v->retval.add(v));
        return new KVector<>(retval.reverse(),context);
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
