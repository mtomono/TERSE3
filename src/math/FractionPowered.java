/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.P;
import collection.TList;

/**
 *
 * @author masao
 */
public class FractionPowered {
    
    public static FractionPowered simple(long v, int powerInv) {
        return create(v, new Rational(1,powerInv));
    }
    static int mod(int a, int b) {
        return a%b;
    }
    static int shiftMod(int a, int b) {
        return (a-1)%b+1;
    }
    /**
     * rationalize when num&den are both sqrt'ed.
     * @return l=rational remains / r=sqrt residue.
     */
    public static FractionPowered create(Rational v, Rational p) {
        Rational s=v.simplify();
        Long numerator=TList.nCopies((int)p.denominator-1, v.numerator).stream().reduce(v.numerator,(a,b)->a*v.denominator);
        return simple(new Rational(1,v.denominator),numerator,p);
    }
    public static FractionPowered create(long v,Rational p) {
        return simple(Rational.ONE,v,p);
    }
    public static FractionPowered simple(Rational c,long v,Rational p) {
        P<TList<Long>, TList<Integer>> factor=Factorization.exec(v).compress();
        TList<Integer> removed=factor.r().map(i->(int)((i*p.numerator)/p.denominator));
        TList<Integer> remains=factor.r().map(i->(int)((i*p.numerator)%p.denominator));
        TList<Boolean> nonzero=remains.map(i->i>0).sfix();
        Rational power=new Rational(remains.filterWith(nonzero).minval(i->i).orElse(0),p.denominator);
        long base=factor.l().filterWith(nonzero).decompress(remains.filterWith(nonzero).map(i->(int)(i-power.numerator+1))).toC(l->l, C.l).pai().body();
        long coordinate=factor.l().decompress(removed).toC(l->l, C.l).pai().body();
        return new FractionPowered(new Rational(coordinate,1).mul(c), base, power);
    }
    final public Rational power;
    final public long base;
    final public Rational coordinate;
    public FractionPowered(Rational coordinate, long base, Rational power) {
        this.power=power;
        this.base=base;
        this.coordinate=coordinate;
    }
    public FractionPowered sqrt() {// damn! very limited partial soolution like 二重根号 is only accessible! .
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof FractionPowered)) {
            return false;
        }
        FractionPowered t = (FractionPowered) e;
        return coordinate.equals(t.coordinate)&&base==(t.base)&&power.equals(t.power);
    }
    public String toString() {
        return ""+coordinate+"*("+base+")^("+power+")";
    }
}
