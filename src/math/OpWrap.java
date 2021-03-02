/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class OpWrap<K> implements Op<K> {
    final public Op<K> op;
    final public Function<K,K> f;
    public OpWrap(Op<K> op, Function<K,K> f) {
        this.op=op;
        this.f=f;
    }
    @Override
    public K b(int v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(long v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(float v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(double v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(String v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(BigDecimal v) {
        return f.apply(op.b(v));
    }

    @Override
    public K b(Rational v) {
        return f.apply(op.b(v));
    }

    @Override
    public K add(K v0, K v1) {
        return f.apply(op.add(v0,v1));
    }

    @Override
    public K sub(K v0, K v1) {
        return f.apply(op.sub(v0,v1));
    }

    @Override
    public K mul(K v0, K v1) {
        return f.apply(op.mul(v0,v1));
    }

    @Override
    public K div(K v0, K v1) {
        return f.apply(op.div(v0,v1));
    }

    @Override
    public K negate(K v0) {
        return f.apply(op.negate(v0));
    }

    @Override
    public K abs(K v0) {
        return f.apply(op.abs(v0));
    }

    @Override
    public K zero() {
        return f.apply(op.zero());
    }

    @Override
    public K one() {
        return f.apply(op.one());
    }
    
}
