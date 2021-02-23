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
package function;

import java.util.function.Function;

/**
 *
 * @author masao
 */
public class CompareUtil {
    static public <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b)<0?a:b;
    }
    static public <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b)>0?a:b;
    }
    static public <T extends Comparable<T>> boolean eq(T a, T b) {
        return a.compareTo(b)==0;
    }
    static public <T extends Comparable<T>> boolean ne(T a, T b) {
        return a.compareTo(b)!=0;
    }
    static public <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b)>0;
    }
    static public <T extends Comparable<T>> boolean ge(T a,T b) {
        return a.compareTo(b)>=0;
    }
    static public <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b)<0;
    }
    static public <T extends Comparable<T>> boolean le(T a,T b) {
        return a.compareTo(b)<=0;
    }
    static public <S,T extends Comparable<T>> map<S,T> map(Function<S,T> f) {
        return new map(f);
    }
    static public class map<S,T extends Comparable<T>> implements ToComparable<S,T> {
        Function<S,T> f;
        map(Function<S,T> f) {
            this.f=f;
        }
        public Function<S,T> toComparable() {
            return f;
        }
    }
}
