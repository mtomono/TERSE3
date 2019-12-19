/*
 Copyright 2017, 2018, 2019 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package solver.graph;

import collection.TList;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.List;
import math.VectorOp;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Metric<K> {
    public double measure(K from, K to);
    static public <K extends Number> Metric<List<K>> l2() {
        return (f,t)->sqrt(TList.set(t).pair(f,(x,y)->(pow(x.doubleValue()-y.doubleValue(),2))).sumD(i->i));
    }
    static public <K extends Number> Metric<List<K>> l1() {
        return (f,t)->TList.set(t).pair(f,(x,y)->abs(x.doubleValue()-y.doubleValue())).sumD(i->i);
    }
    static public <K extends Number> Metric<List<K>> weighted(Metric<List<Double>> base, List<? extends Number> weight) {
        TList<? extends Number> w = TList.set(weight);
        return (f,t)->base.measure(w.pair(f,(a,b)->a.doubleValue()*b.doubleValue()),w.pair(t,(a,b)->a.doubleValue()*b.doubleValue()));
    }
}
