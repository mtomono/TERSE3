/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.P;
import collection.TList;
import static math.Integers.pow;

/**
 *
 * @author masao
 */
public class FractionPowered {
    Rational coordinate;
    int base;
    Rational power;
    
    public static FractionPowered create(Rational base,Rational power) {
        return create(Rational.ONE,base,power);
    }
    
    public static FractionPowered create(Rational coordinate, Rational base, Rational power) {
        Rational s=base.simplify();
        Integer numerator=TList.nCopies((int)power.denominator-1, base.numerator).stream().reduce(base.numerator,(a,b)->a*base.denominator);
        return new FractionPowered(coordinate.mul(new Rational(1,base.denominator)),numerator,power);
    }
    
    public static FractionPowered create(int v, int powerInv) {
        return new FractionPowered(Rational.ONE,v,new Rational(1,powerInv));
    }
    
    public FractionPowered(Rational coordinate,int base,Rational power) {
        this.coordinate=coordinate;
        this.power=power;
        this.base=base;
    }
    
    public FractionPowered simplify() {
        P<TList<Integer>, TList<Integer>> factor=Integers.factorization(base).compress();
        TList<Integer> removed=factor.r().map(i->(int)((i*power.numerator)/power.denominator));
        int c=factor.l().decompress(removed).toC(l->l, C.i).pai().body();

        TList<Integer> remains=factor.r().map(i->(int)((i*power.numerator)%power.denominator));
        TList<Boolean> nonzero=remains.map(i->i>0).sfix();
        Rational p=new Rational(remains.filterWith(nonzero).minval(i->i).orElse(0),power.denominator);
        int b=factor.l().filterWith(nonzero).decompress(remains.filterWith(nonzero).map(i->(int)(i-p.numerator+1))).toC(l->l, C.i).pai().body();
        return new FractionPowered(coordinate.mul(new Rational(c,1)).simplify(),b, p.simplify());
    }
    
    public FractionPowered mul(FractionPowered v) {
        int gcd=Integers.gcd(power.denominator,v.power.denominator);
        int b=pow(pow(base,power.numerator),v.power.denominator/gcd)*pow(pow(v.base,v.power.numerator),power.denominator/gcd);
        return new FractionPowered(coordinate.mul(v.coordinate),b,new Rational(1,power.denominator*v.power.denominator/gcd));
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
