/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import static collection.TransparentTranspose.transpose;
import static java.lang.Integer.min;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class KMatrix<K extends Decimal<K>> {
    static public KMatrix<KRational> matrix(Integer[][][] matrix) {
        return matrix(TList.sof(matrix).map(a->TList.sof(a).map(r->new KRational(r[0],r[1])).sfix()).sfix());
    }
    static public <K extends Decimal<K>> KMatrix<K> matrix(Integer[][] matrix, Function<Integer,K> f) {
        return new KMatrix<>(TList.sof(matrix).map(a->TList.sof(a).map(f).sfix()).sfix());
    }
    static public <K extends Decimal<K>> KMatrix<K> matrix(TList<TList<K>> body) {
        return new KMatrix<>(body);
    }
    TList<TList<K>> body;
    int x;
    int y;
    public KMatrix(TList<TList<K>> body) {
        this.body=body;
        this.x=body.isEmpty()?0:body.get(0).size();
        this.y=body.size();
    }
    public KMatrix<K> subMatrix(int... fromTo) {
        int x0=fromTo[0];int y0=fromTo[1];  int x1=fromTo[2];int y1=fromTo[3];
        return new KMatrix<>(body.subList(y0,y1)
                .map(r->r.subList(x0,x1)));
    }
    public TList<KVector<K>> rows() {
        return body.map(r->new KVector<>(r));
    }
    public TList<KVector<K>> columns() {
        return transpose(body).map(r->new KVector<>(r));
    }
    public KMatrix<K> eliminate() {
        for (int i=0;i<min(x,y)-1;i++)
            subMatrix(i,i,x,y).eliminateStep();
        return this;
    }
    public KMatrix<K> eliminateStep() {
        KVector<K> lcolumn=   subMatrix(0,1, 1,y).columns().get(0).invS(body.get(0).get(0));
        KVector<K> eliminator=subMatrix(1,0, x,1).rows().get(0);
        TList<KVector<K>> ee= subMatrix(1,1,x,y).rows();
        lcolumn.body.pair(ee,(l,u)->u.subS(eliminator.scale(l))).forEach(r->{});
        return this;
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
