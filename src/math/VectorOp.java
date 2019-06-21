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
import java.util.List;
import java.util.function.BinaryOperator;

/**
 *
 * @author masao
 */
public class VectorOp {
    static public <T> List<T> op(List<T> a, List<T> b, BinaryOperator<T> f) {
        return TList.set(a).pair(b, (x,y)->f.apply(x,y)); 
    }
    
    static public List<Integer> subI(List<Integer>a, List<Integer>b) {
        return op(a,b,(x,y)->x-y);
    }
    static public List<Integer> addI(List<Integer>a, List<Integer>b) {
        return op(a,b,(x,y)->x+y);
    }
    static public List<Double> subD(List<Double>a, List<Double>b) {
        return op(a,b,(x,y)->x-y);
    }
    static public List<Double> addD(List<Double>a, List<Double>b) {
        return op(a,b,(x,y)->x+y);
    }
    static public List<Double> scaleD(List<Double>a, double scale) {
        return TList.set(a).map(x->x*scale);
    }
}
