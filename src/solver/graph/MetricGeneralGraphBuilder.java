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

import collection.MVMap;
import collection.P;
import collection.TList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author masao
 */
public class MetricGeneralGraphBuilder<K> {
    public static <K> MetricGeneralGraphBuilder<K> builder() {
        return new MetricGeneralGraphBuilder<>();
    }
    
    public MetricGeneralGraphBuilder() {
        this.body=new MVMap<>(new HashMap<>());
    }
    
    static class FromToCost<K> {
        K from;
        K to;
        Double cost;
        
        public FromToCost(K from, K to,Double cost) {
            this.from=from;
            this.to=to;
            this.cost=cost;
        }
        public K key() {
            return from;
        }
        public P<K,Double> value() {
            return P.p(to,cost);
        }
    }

    public static <K> Map<K,TList<P<K,Double>>> graph(Object... fromToCosts) {
        MVMap<K,P<K,Double>> retval = new MVMap(new HashMap<>());
        TList.sof(fromToCosts).fold(3).map(p->new FromToCost<>((K)p.get(0),(K)p.get(1),(Double)p.get(2))).forEach(f->retval.getList(f.key()).add(f.value()));
        return retval;
    }
    
    MVMap<K,P<K,Double>> body;
    
    public MetricGeneralGraphBuilder<K> merge(Object... fromToCosts) {
        this.body.merge(MetricGeneralGraphBuilder.graph(fromToCosts));
        return this;
    }
    
    public MetricGeneralGraphBuilder<K> a(K from, K to, double cost) {
        body.getList(from).add(P.p(to, cost));
        return this;
    }
    
    public MetricGeneralGraph<K> build(Object... fromToCosts) {
        return new MetricGeneralGraph<>(body.merge(graph(fromToCosts)));
    }
    
    public MetricGeneralGraph<K> build() {
        return new MetricGeneralGraph<>(body);
    }
}
