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

/**
 * basic comparison.
 * @author masao
 */
public interface CompareUtil {
    static public <T extends Comparable<? super T>> boolean eq(T a, T b) {
        return a.compareTo(b)==0;
    }
    static public <T extends Comparable<? super T>> boolean ne(T a, T b) {
        return a.compareTo(b)!=0;
    }
    static public <T extends Comparable<? super T>> boolean gt(T a, T b) {
        return a.compareTo(b)>0;
    }
    static public <T extends Comparable<? super T>> boolean ge(T a,T b) {
        return a.compareTo(b)>=0;
    }
    static public <T extends Comparable<? super T>> boolean lt(T a, T b) {
        return a.compareTo(b)<0;
    }
    static public <T extends Comparable<? super T>> boolean le(T a,T b) {
        return a.compareTo(b)<=0;
    }
}
