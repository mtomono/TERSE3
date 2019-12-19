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

import collection.P;
import collection.TList;
import solver.graph.MetricGraph;

/**
 *
 * @author masao
 * @param <K>
 */
public class MetricCompositeGraph<K> implements MetricGraph<K> {
    final public TList<MetricGraph<K>> body;
    
    public MetricCompositeGraph(TList<MetricGraph<K>> body) {
        this.body = body;
    }
    
    public MetricCompositeGraph(MetricGraph<K>... body) {
        this(TList.sof(body));
    }

    @Override
    public TList<P<K, Double>> next(K from) {
        return body.map(g->g.next(from)).sfix().flatMap(l->l).sfix();
    }

    @Override
    public TList<K> all() {
        return body.map(g->g.all()).sfix().flatMap(l->l).sfix();
    }
}
