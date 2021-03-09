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

import function.Op;
import collection.TList;
import function.Wrapper;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author masao
 * @param <K>
 * @param <CONTEXT>
 */
public interface ContextBuilder<K, CONTEXT extends Context<K,CONTEXT>> extends Wrapper<Op<K>,ContextBuilder<K,CONTEXT>>{
    public Format<K> format();
    public ContextBuilder<K,CONTEXT> format(Format<K> format);
    public CONTEXT c(K v);
    default CONTEXT b(int v) {return c(body().b(v));}
    default CONTEXT b(long v) {return c(body().b(v));}
    default CONTEXT b(float v) {return c(body().b(v));}
    default CONTEXT b(double v) {return c(body().b(v));}
    default CONTEXT b(String v) {return c(body().b(v));}
    default CONTEXT f(String v) throws ParseException {return c(format().f(v));}
    default String toString(CONTEXT v) {return format().toString(v.body());}
    default CONTEXT b(BigDecimal n) {return c(body().b(n));}
    default CONTEXT b(Rational n) {return c(body().b(n));}
    default CONTEXT one() {return c(body().one());}
    default CONTEXT zero() {return c(body().zero());}
    default TList<TList<CONTEXT>> i(int n) {
        return TList.range(0,n).map(i->TList.nCopies(n, zero()).sfix().cset(i, one()));
    }
}
