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
import collection.TTreeMap;
import java.util.Map;
import java.util.Optional;
import orderedSet.Comparators;

/**
 * Shortest path search with memo.
 * @author masao
 * @param <N>
 */
public class ShortestPathMemo<N extends Comparable<N>> extends ShortestPathNoRevisit<N> {
    Map<P<N,N>, Optional<Integer>> memo;
    public ShortestPathMemo(Map<P<N, N>, Integer> graph) {
        super(graph);
        this.memo = new TTreeMap<>(Comparators.<P<N,N>>sof(p->p.l(),p->p.r()).compile());
    }
    
    @Override
    public Optional<Integer> find(N from, N to) {
        return memo.computeIfAbsent(P.p(from,to), p->super.find(p.l(),p.r()));
    }
}
