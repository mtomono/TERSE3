/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import debug.Monitorable;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class F<L, R> extends AbstractList<Object> implements Monitorable {
    final L l;
    final Function<L,R> f;
    public F(L l, Function<L,R> f) {
        this.l=l;
        this.f=f;
    }
    public L l() {
        return l;
    }
    public R r() {
        return f.apply(l);
    }
    public P<L,R> sfix() {
        return P.p(l(),r());
    }
    @Override
    public Object get(int index) {
        if (index==0)
            return l();
        if (index==1)
            return r();
        throw new NoSuchElementException("F has only 2 elements.");
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public String monitor() {
        return "F:\n"+indent(l())+indent(r());
    }
    
}
