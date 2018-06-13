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

package solver.path;

import collection.P;
import collection.TList;
import static function.ComparePolicy.inc;
import java.util.Map;
import java.util.Optional;

/**
 * Primitive shortest path search.
 * very the basic of shortest path search.
 * @author masao
 * @param <N>
 */
public class ShortestPathBasic<N> {
    Map<P<N,N>, Integer> graph;
    
    public ShortestPathBasic(Map<P<N, N>, Integer> graph) {
        this.graph = graph;
    }
    
    public Optional<Integer> find(N from, N to) {
        if (from.equals(to))
            return Optional.of(0);
        return findx(from,to);
    }
    
    Optional<Integer> findx(N from, N to) {
        return TList.set(graph.keySet()).filter(p->p.l().equals(from)).
                map(p->find(p.r(), to).map(c->c+graph.get(P.p(from,p.r())))).filter(m->m.isPresent()).map(m->m.get()).min(inc(n->n));
    }
}
