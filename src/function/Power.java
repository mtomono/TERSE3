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

import collection.TList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

/**
 *
 * @author masao
 */
public class Power {
    public static <T> T pow(T target, int power, T id, BiFunction<T,T,T> f) {
        T retval=id;
        for (int i=Integer.SIZE-1;i>=0;i--) retval=((power>>>i)&1)!=0?f.apply(f.apply(retval,retval),target):f.apply(retval,retval);
        return retval;
    }
    
    public static <T> T pow(T target, long power, T id, BiFunction<T,T,T> f) {
        T retval=id;
        for (int i=Long.SIZE-1;i>=0;i--) retval=((power>>>i)&1L)!=0?f.apply(f.apply(retval,retval),target):f.apply(retval,retval);
        return retval;
    }
    
    // below are experimental implementation,
    // simple repetition
    public static <T> T powx(T target, int power, T id, BinaryOperator<T> f) {
        return TList.nCopies(power,target).stream().reduce(id,f);
    }
    
    //implemented with TList
    public static <T> T powl(T target, int power, T id, BiFunction<T,T,T> f) {
        return TList.longBits.subList(0,Integer.SIZE).reverse().stream().map(b->(power&b)!=0?target:id).reduce(id, (a,b)->f.apply(f.apply(a,a),b));
    }
    public static <T> T powll(T target, int power, T id, BiFunction<T,T,T> f) {
        return TList.longBits.subList(0,Integer.SIZE).reverse().accum(id, (a,b)->(power&b)!=0?f.apply(f.apply(a,a),target):f.apply(a,a)).last();
    }
    
    //implemented with Stream
    public static <T> T pows(T target, int power, T id, BiFunction<T,T,T> f) {
        return IntStream.iterate(Integer.SIZE, i->i>=0, i->i-1).mapToObj(i->(power&(1<<i))!=0?target:id).reduce(id,(a,b)->f.apply(f.apply(a,a),b));
    }
}
