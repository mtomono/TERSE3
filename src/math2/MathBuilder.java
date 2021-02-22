/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.TList;
import function.CompareUtil;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <K>
 */
public class MathBuilder<K extends Comparable<K>> {
    C.Builder<K> context;
    CompareUtil.map<C<K>, K> compare;

    protected MathBuilder(C.Builder<K> context) {
        this.context = context;
        this.compare = CompareUtil.map((c) -> c.body());
    }

    public CMatrix<K> m(TList<TList<C<K>>> body) {
        return new CMatrix<>(this, body);
    }

    public CMatrix<K> m(Integer[][] matrix) {
        return MathBuilder.this.m(TList.sof(matrix).map((a) -> TList.sof(a).map((i) -> context.b(i)).sfix()).sfix());
    }

    public CMatrix<K> m(String source) {
        return MathBuilder.this.m(TList.sof(source.split(";")).map((r) -> TList.sof(r.split(",")).map((s) -> context.b(s)).sfix()).sfix());
    }
    
    public CMatrix<K> I(int n) {
        return new CMatrix<>(this,TList.range(0,n).map(i->TList.nCopies(n, context.zero()).sfix().cset(i, context.one())));
    }

    public CList<K> l(K... v) {
        return TList.sof(v).toC(x->x,context);
    }
    public CList<K> l(String v) {
        return MathBuilder.this.l(TList.sof(v.split(",")).map(s->context.b(s)).sfix());
    }
    public CList<K> l(TList<C<K>> v) {
        return new CList<>(context,v);
    }
}
