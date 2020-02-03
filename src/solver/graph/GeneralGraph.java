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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author masao
 * @param <K>
 */
public class GeneralGraph<K> implements Graph<K> {
    MVMap<K,K> body;
    
    public GeneralGraph(Map<K,TList<K>> body) {
        this.body = new MVMap<>(body);
    }
    
    @Override
    public MetricGeneralGraph<K> metricize(Metric<K> metric) {
        Map<K,TList<P<K,Double>>> body = new HashMap<>();
        this.body.entrySet().stream().forEach(e->body.put(e.getKey(),e.getValue().map(to->P.p(to, metric.measure(e.getKey(), to)))));
        return new MetricGeneralGraph<>(body);
    }
    
    @Override
    public TList<K> next(K from) {
        return body.getList(from);
    }
    
    @Override
    public TList<K> all() {
        Set<K> retval = new HashSet<>(body.keySet());
        for (TList<K> v : body.values())
            retval.addAll(v);
        return TList.set(retval);
    }
    
    public GeneralGraph<K> addPath(K from, K to) {
        body.getList(from).add(to);
        return this;
    }
}
