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
import static java.lang.Long.compare;
import java.math.BigDecimal;

/**
 *
 * @author masao
 */
public class KRational implements Decimal<KRational> {
    public final long numerator;
    public final long denominator;
    
    public static final KRational ZERO = new KRational(0,1);
    public static final KRational ONE = new KRational(1,1);
    
    public static KRational r(int v) {
        return new KRational(v,1);
    }
    public static KRational r(long v) {
        return new KRational(v,1);
    }
    public static KRational r(String v) {
        String[] n=v.split("/");
        return n.length==1?r(Long.parseLong(v)):new KRational(Long.parseLong(n[0]),Long.parseLong(n[1]));
    }
    public KRational(long numerator, long denominator) {
        assert denominator!=0 : "denominator cannot be zero";
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    public KRational(double body, long denominator) {
        this.numerator = new BigDecimal(body).divide(new BigDecimal(denominator)).longValue();
        this.denominator=denominator;
    }
    
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
    
    @Override
    public KRational abs() {
        return new KRational(java.lang.Math.abs(numerator),java.lang.Math.abs(denominator));
    }
    
    @Override
    public KRational negate() {
        return new KRational(-numerator,denominator);
    }
    
    @Override
    public int compareTo(KRational other) {
        return compare(numerator*other.denominator, other.numerator*denominator);
    }
    
    public KRational reduce() {
        if (numerator==0)
            return r(0);
        long gcd = java.lang.Math.abs(gcd(numerator, denominator));
        return new KRational(numerator/gcd, denominator/gcd);
    }
    
    public KRational normalize() {
        if (denominator<0)
            return new KRational(-numerator,-denominator);
        return this;
    }
    
    public KRational rednorm() {
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
        return ""+numerator+(denominator==1?"":"/"+denominator);
    }
    
    @Override
    public KRational add(KRational value) {
        if (denominator==value.denominator)
            return new KRational(numerator+value.numerator, denominator);
        return new KRational(numerator*value.denominator+value.numerator*denominator, denominator*value.denominator);
    }

    @Override
    public KRational sub(KRational value) {
        if (denominator==value.denominator)
            return new KRational(numerator-value.numerator, denominator);
        return new KRational(numerator*value.denominator-value.numerator*denominator, denominator*value.denominator);
    }

    @Override
    public KRational mul(KRational value) {
        return new KRational(numerator*value.numerator, denominator*value.denominator);
    }
    
    @Override
    public KRational mul(int value) {
        return new KRational(numerator*value, denominator);
    }

    @Override
    public KRational mul(long value) {
        return new KRational(numerator*value, denominator);
    }

    @Override
    public KRational div(KRational value) {
        return new KRational(numerator*value.denominator, denominator*value.numerator).rednorm();
    }

    @Override
    public KRational div(int value) {
        return new KRational(numerator, denominator*value);
    }

    @Override
    public KRational div(long value) {
        return new KRational(numerator, denominator*value);
    }

    @Override
    public KRational zero() {
        return ZERO;
    }

    @Override
    public KRational one() {
        return ONE;
    }
    
    @Override
    public boolean isInteger() {
        return numerator/denominator*denominator==numerator;
    }
    
    @Override
    public int intPart() {
        return (int)longPart();
    }
    
    @Override
    public long longPart() {
        return numerator/denominator;
    }    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof KRational)) {
            return false;
        }
        KRational t = (KRational) e;
        return rednorm().toList().equals(t.rednorm().toList());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.numerator ^ (this.numerator >>> 32));
        hash = 89 * hash + (int) (this.denominator ^ (this.denominator >>> 32));
        return hash;
    }
    
}
