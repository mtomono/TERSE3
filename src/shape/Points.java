/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import collection.TListWrapper;
import function.Transformable;
import java.awt.Rectangle;
import java.util.Optional;
import math.C;
import math.CList;

/**
 *
 * @author masao
 * @param <K>
 */
public class Points<K extends Comparable<K>> implements TListWrapper<CList<K,C<K>>,Points<K>>,Transformable<Points<K>>{
    final TList<CList<K,C<K>>> body;
    public Points(TList<CList<K,C<K>>> body) {
        this.body=body;
    }
    static public <K extends Comparable<K>> Points<K> c(C.Builder<K> b, int dimension, K... v) {
        return new Points<>(TList.sof(v).fold(dimension).map(l->l.toC(t->t,b)));
    }
    static public <K extends Comparable<K>> Points<K> rectangle(CList<K,C<K>> lo, CList<K,C<K>> size) {
        return new Points<>(TList.sof(lo,lo.add(size)));
    }
    static public Points<Integer> rectangle(Rectangle r) {
        return rectangle(TList.sof(r.x,r.y).toC(v->v, C.i),TList.sof(r.width,r.height).toC(v->v, C.i));
    }
    @Override
    public Points<K> self() {
        return this;
    }
    @Override
    public TList<CList<K,C<K>>> body() {
        return body;
    }
    @Override
    public Points<K> wrap(TList<CList<K,C<K>>> body) {
        return new Points<>(body);
    }
    public static <K extends Comparable<K>> Points<K> merge(TList<Points<K>> ps) {
        return new Points<>(ps.flatMapc(p->p.body));
    }
    public Points<K> merge(Points<K> o) {
        return new Points<>(body.append(o.body));
    }
    public Points<K> diff() {
        return new Points(body.diff((a,b)->b.sub(a)));
    }
    public Optional<CList<K,C<K>>> min() {
        return body.getOpt(0).map(x->new CList<>(x.b, body.transposeT(c->c.body()).map(l->l.min(v->v.body()).get())));
    }
    public Optional<CList<K,C<K>>> max() {
        return body.getOpt(0).map(x->new CList<>(x.b, body.transposeT(c->c.body()).map(l->l.max(v->v.body()).get())));
    }
    public Optional<Points<K>> rect() {
        return min().flatMap(min->max().map(max->new Points<>(TList.sof(min,max))));
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Points)) {
            return false;
        }
        Points t = (Points) e;
        return body.equals(t.body);
    }
    @Override
    public String toString() {
        return body.toString();
    }
}
