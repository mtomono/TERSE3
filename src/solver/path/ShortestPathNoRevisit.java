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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static solver.path.ShortestPathNoRevisit.NodeStatus.*;

/**
 * Shortest path search without revisiting.
 * search that do not revisit node in recursion.
 * @author masao
 * @param <N>
 */
public class ShortestPathNoRevisit<N> extends ShortestPathBasic<N>{
    public enum NodeStatus {
        NONE,
        OPEN,
        CLOSED;
    }
    Map<N, NodeStatus> status;
    
    public ShortestPathNoRevisit(Map<P<N, N>,Integer> graph) {
        super(graph);
        this.status = new HashMap<>();
        graph.keySet().forEach(p->{status.put(p.l(),NONE);status.put(p.r(),NONE);});
    }
    
    @Override
    Optional<Integer> findx(N from, N to) {
        if (status.get(from).equals(OPEN)) //to eliminate revisits while recursion, do not RE-open any node.
            return Optional.empty();
        status.put(from, OPEN); 
        Optional<Integer> retval = super.findx(from,to);
        status.put(from, CLOSED); //so after recursion, the state should be changed. items at status CLOSED are ones already calculated at least once
        return retval;
    }
}
