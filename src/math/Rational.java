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
import static java.lang.Math.abs;
import java.math.BigDecimal;

/**
 *
 * @author masao
 */
public class Rational implements FieldM<Rational> {
    public final long numerator;
    public final long denominator;
    
    public static final Rational zero = new Rational(0,1);
    public static final Rational one = new Rational(1,1);
        
    public Rational(long numerator, long denominator) {
        assert denominator!=0 : "denominator cannot be zero";
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public Rational(double body, long denominator) {
        this.numerator = new BigDecimal(body).divide(new BigDecimal(denominator)).longValue();
        this.denominator=denominator;
    }
    
    static public long gcd(long m, long n) {
        if (m<n)
            return gcd(n,m);
        long r = m%n;
        if (r==0)
            return n;
        return gcd(n,r);
    }
    
    public Rational reduce() {
        long gcd = abs(gcd(numerator, denominator));
        return new Rational(numerator/gcd, denominator/gcd);
    }
    
    public Rational normalize() {
        if (denominator<0)
            return new Rational(-numerator,-denominator);
        return this;
    }
    
    public Rational rednorm() {
        return reduce().normalize();
    }
    
    public double toDouble() {
        return numerator/(double)denominator;
    }
    
    public TList<Long> toList() {
        return TList.sof(numerator, denominator);
    }
    
    public BigDecimal toBigDecimal() {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator));
    }
    
    @Override
    public String toString() {
        return ""+numerator+"/"+denominator;
    }
    
    @Override
    public Rational add(Rational value) {
        if (denominator==value.denominator)
            return new Rational(numerator+value.numerator, denominator);
        return new Rational(numerator*value.denominator+value.numerator*denominator, denominator*value.denominator);
    }

    @Override
    public Rational sub(Rational value) {
        if (denominator==value.denominator)
            return new Rational(numerator-value.numerator, denominator);
        return new Rational(numerator*value.denominator-value.numerator*denominator, denominator*value.denominator);
    }

    @Override
    public Rational mul(Rational value) {
        return new Rational(numerator*value.numerator, denominator*value.denominator);
    }

    @Override
    public Rational div(Rational value) {
        return new Rational(numerator*value.denominator, denominator*value.numerator);
    }

    @Override
    public Rational zero() {
        return zero;
    }

    @Override
    public Rational one() {
        return one;
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
        return reduce().toList().equals(t.reduce().toList());
    }
    
}
