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

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <K>
 */
@FunctionalInterface
public interface Metric<K> {
    public double measure(K from, K to);
    /**
     * morph the metric.
     * NOTICE:
     * this is adding a preprocess for the measure().
     * because measure() itself is not modifiable in a generic meaning.
     * @param <S>
     * @param morph
     * @return 
     */
    default public <S> Metric<S> morph(Function<S,K> morph) {
        return (f,t)->measure(morph.apply(f),morph.apply(t));
    }
}
