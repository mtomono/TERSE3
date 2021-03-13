/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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

import collection.P;
import collection.TList;
import static java.lang.Long.compare;

/**
 * Rational number.
 * @author masao
 */
public class Rational extends Number implements Comparable<Rational> {
    static public long gcd(long mo, long no) {
        long m=java.lang.Math.abs(mo);
        long n=java.lang.Math.abs(no);
        if (m<n)
            return gcd(n,m);
        long r = m%n;
        if (r==0)
            return n;
        return gcd(n,r);
    }

    public static final Rational ZERO = new Rational(0,1);
    public static final Rational ONE = new Rational(1,1);
    
    public final long numerator;
    public final long denominator;
    
    static public Rational parseRational(String s) {
        String[] n=s.split("/");
        return n.length==1?new Rational(Long.parseLong(s),1):new Rational(Long.parseLong(n[0]),Long.parseLong(n[1]));
    }
    
    static public <K extends Number&Comparable<K>> Rational valueOf(C2<K> target, K threshold) {
        return new CFE<K>().exec(target, threshold).fraction().last();
    }

    public Rational(long numerator, long denominator) {
        assert denominator!=0 : "denominator cannot be zero";
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Rational reduce() {
        if (numerator==0)
            return ZERO;
        long gcd = java.lang.Math.abs(gcd(numerator, denominator));
        return new Rational(numerator/gcd, denominator/gcd);
    }
    
    public Rational normalize() {
        if (denominator<0)
            return new Rational(-numerator,-denominator);
        return this;
    }
    
    public Rational simplify() {
        return reduce().normalize();
    }
    
    public Rational fractionPart() {
        return new Rational(numerator-longValue()*denominator,denominator);
    }
        
    public Rational add(Rational value) {
        if (denominator==value.denominator)
            return new Rational(numerator+value.numerator, denominator);
        return new Rational(numerator*value.denominator+value.numerator*denominator, denominator*value.denominator);
    }

    public Rational sub(Rational value) {
        if (denominator==value.denominator)
            return new Rational(numerator-value.numerator, denominator);
        return new Rational(numerator*value.denominator-value.numerator*denominator, denominator*value.denominator);
    }

    public Rational mul(Rational value) {
        return new Rational(numerator*value.numerator, denominator*value.denominator);
    }
    
    public Rational div(Rational value) {
        return new Rational(numerator*value.denominator, denominator*value.numerator).simplify();
    }

    public Rational abs() {
        return new Rational(java.lang.Math.abs(numerator),java.lang.Math.abs(denominator));
    }
    
    public Rational negate() {
        return new Rational(-numerator,denominator);
    }
    
    @Override
    public int intValue() {
        return (int)longValue();
    }

    @Override
    public long longValue() {
        return numerator/denominator;
    }

    @Override
    public float floatValue() {
        return numerator/(float)denominator;
    }

    @Override
    public double doubleValue() {
        return numerator/(double)denominator;
    }
    
    public TList<Long> toList() {
        return TList.sof(numerator, denominator);
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Rational)) {
            return false;
        }
        Rational t = (Rational) e;
        return simplify().toList().equals(t.simplify().toList());
    }

    @Override
    public int hashCode() {
        return simplify().hashCodeBase();
    }
    
    public int hashCodeBase() {
        int hash = 3;
        hash = 89 * hash + (int) (this.numerator ^ (this.numerator >>> 32));
        hash = 89 * hash + (int) (this.denominator ^ (this.denominator >>> 32));
        return hash;
    }
    
    @Override
    public String toString() {
        return ""+numerator+(denominator==1?"":"/"+denominator);
    }

    @Override
    public int compareTo(Rational o) {
        return compare(numerator*o.denominator, o.numerator*denominator);
    }
}
