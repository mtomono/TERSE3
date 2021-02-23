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

import collection.TList;

/**
 * ContinuedFractionExpansion.
 * convenient entrance is Rational.valueof(target, threshold).
 * @author masao
 * @param <K>
 */
public class CFE<K extends Number&Comparable<K>> {
    private TList<Integer> intPart;
    private TList<ContinuedFraction> fraction;
    public CFE() {
        fraction=TList.of(cf(1,0));
        intPart=TList.c();
    }
    static ContinuedFraction cf(long n, long d) {
            return new ContinuedFraction(n,d);
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
    public CFE<K> exec(C2<K> target,K threshold) {
        ContextBuilder<K,C2<K>> b=target.b();
        C2<K> thresholdb=b.c(threshold);
        C2<K> rest=target;
        while (true) {
            next(rest.body().intValue());
            C2<K> decimal=rest.sub(b.b(intPart.last()));
            if (thresholdb.gt(target.sub(b.b(fraction.last().rationalValue())).abs()))
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
