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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author masao
 * @param <K>
 */
public class MetricGeneralGraph<K> implements MetricGraph<K> {
    MVMap<K,P<K,Double>> body;
    
    public MetricGeneralGraph(Map<K,TList<P<K,Double>>> body) {
        this.body = new MVMap<>(body);
    }
    @Override
    public TList<P<K,Double>> next(K from) {
        return body.getList(from);
    }
    
    @Override
    public TList<K> all() {
        Set<K> retval = new HashSet<>(body.keySet());
        for (TList<P<K,Double>> v : body.values())
            retval.addAll(v.map(p->p.l()));
        return TList.set(retval);
    }
    
    public MetricGeneralGraph<K> addPath(K from, K to, double distance) {
        body.getList(from).add(P.p(to, distance));
        return this;
    }
}
