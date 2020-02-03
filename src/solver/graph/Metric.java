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
import static java.lang.Math.sqrt;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Metric<K> {
    public double measure(K from, K to);
    default public <S> Metric<S> morph(Function<S,K> morph) {
        return (f,t)->measure(morph.apply(f),morph.apply(t));
    }
    static public <K extends Number> Metric<List<K>> l2() {
        return (f,t)->sqrt(TList.set(t).pair(f,(x,y)->x.doubleValue()-y.doubleValue()).map(d->d*d).sumD(i->i));
    }
    static public <K extends Number> Metric<List<K>> l1() {
        return (f,t)->TList.set(t).pair(f,(x,y)->abs(x.doubleValue()-y.doubleValue())).sumD(i->i);
    }
    static public <N extends Number> Function<List<N>,List<Double>> weight(TList<? extends Number> weight) {
        return l->weight.pair(l,(a,b)->a.doubleValue()*b.doubleValue());
    }
    
    static public <N extends Number> Function<List<N>,List<Double>> costv(double costv) {
        return weight(TList.sof(1,1,costv));
    }
}
