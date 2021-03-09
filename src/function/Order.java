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

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 */
public interface Order<T> extends Comparator<T>{
    default T min(T a, T b) {return lt(a,b)?a:b;}
    default T max(T a, T b) {return gt(a,b)?a:b;}
    @Override
    public int compare(T a, T b);
    default boolean eq(T a, T b) { return compare(a, b)==0; }
    default boolean ne(T a, T b) { return compare(a, b)!=0; }
    default boolean lt(T a, T b) { return compare(a, b)<0; }
    default boolean le(T a, T b) { return compare(a, b)<=0; }
    default boolean gt(T a, T b) { return compare(a, b)>0; }
    default boolean ge(T a, T b) { return compare(a, b)>=0; }
    default <S> Order<S> map(Function<S,T> f) { return (a,b)->compare(f.apply(a),f.apply(b)); }
    default Order<T> error(BiPredicate<T,T> p) { return (a,b)->p.test(a, b)?0:compare(a,b); }
    default Order<T> error(Op<T> op, T err) { return error((a,b)->lt(op.abs(op.sub(a,b)),err)); }
    public static <T extends Comparable<? super T>> Order<T> natural() { return (a,b)->a.compareTo(b); }
}

/**
 * Order and error.
 * error means a calculation result might be shifted to up and down than what it should be.
 * so with a number system with error like double. often time its user will face a need to 
 * cancel the effect of it.
 * one of those occasion is a comparison. you cannot expect the exact value as a result because 
 * of the error in the calculation.
 * when you realize that cancellation in Order, which means a value within epsilon away from 
 * the value in question is considered to be equal by means of comparison. 
 * there is an argument that this idea will leave this loophole. 
 * a=a+ε
 * a+ε=(a+ε)+ε=a+2ε
 * a+2ε=(a+2ε)+ε=a+3ε
 * 　　:
 * thus anything would be considered as the same value.
 * it is wrong.
 * you are super-duper incredibly lucky if you see a value somewhere far away as the same thing 
 * by this way. 
 * this inaccuracy in comparison also should be considered as an error in double calculation.
 * calculation in double comes with error. but it is tremendously faster. and most of all, it works fine.
 */
