/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math2;

import collection.TList;

/**
 * ContinuedFractionExpansion.
 * @author masao
 * @param <K>
 */
public class CFE<K extends Number&Comparable<K>> {
    TList<Integer> intPart;
    TList<ContinuedFraction> fraction;
    static ContinuedFraction cf(long n, long d) {
        return new ContinuedFraction(n,d);
    }
    public CFE() {
        fraction=TList.of(cf(1,0));
        intPart=TList.c();
    }
    public ContinuedFraction next(int ck) {
        intPart.add(ck);
        if (fraction.size()==1) {
            fraction.add(cf(ck,1));
            return fraction.last();
        }
        ContinuedFraction retval=cf(fraction.last().n*ck+fraction.last(1).n,fraction.last().d*ck+fraction.last(1).d);
        fraction.add(retval);
        return retval;
    }
    public CFE<K> exec(C<K> target,K threshold) {
        C.Builder<K> b=target.b;
        C<K> rest=target;
        while (true) {
            next(rest.v.intValue());
            C<K> decimal=rest.sub(rest.b.b(intPart.last()));
            if (target.sub(b.b(fraction.last().rationalValue())).abs().v.compareTo(threshold)<0)
                return this;
            rest=decimal.inv();
        }
    }
    public TList<Integer> intPart() {
        return intPart.map(i->i);
    }
    public TList<Rational> fraction() {
        return fraction.seek(1).map(cf->cf.rationalValue());
    }
    static public class ContinuedFraction {
        final public long n;
        final public long d;
        public ContinuedFraction(long numerator, long denominator) {
            this.n=numerator;
            this.d=denominator;
        }
        public Rational rationalValue() {
            return new Rational(n,d);
        }
        public String toString() {
            return ""+n+"/"+d;
        }
    }
}
