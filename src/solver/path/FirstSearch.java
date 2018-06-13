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

import collection.TList;
import java.util.LinkedList;
import java.util.Optional;
import static solver.path.AStarStatus.*;

/**
 *
 * @author masao
 * @param <T>
 */
public abstract class FirstSearch<T extends AStarNode> {
    LinkedList<T> queue;

    abstract public int costToGo(T astar);
    abstract public TList<T> candidates(T astar);
    abstract public T getStart();
    abstract public boolean isGoal(T astar);
    abstract public int upperBoundary();
    abstract public int costToMove(Optional<T> from, T to);
    
    public FirstSearch() {
        this.queue = new LinkedList<>();
    }
        
    public Optional<T> search() {
        return searchx(open(getStart(),Optional.empty()));
    }
        
    T open(T target, Optional<T> parent) {
        target.open(parent, costToMove(parent, target)+parent.map(a->a.costToCome).orElse(0), costToGo(target));
        return target;
    }
    
    Optional<T> searchx(T parent) {
        if (parent.status!=OPEN)
            return Optional.empty();
        parent.close();
        TList<T> candidates = candidates(parent).filter(a->a.status==NONE).
                map(a->open(a,Optional.of(parent))).fix().sortTo((a,b)->a.score()-b.score());
        Optional<T> retval = candidates.stream().filter(a->isGoal(a)).findAny();
        if (retval.isPresent())
            return retval;
        candidates.forEach(a->queue.offer(a));
        if (parent.costToCome < upperBoundary() && !candidates.isEmpty())
            return candidates.stream().map(a->searchx(a)).filter(a->a.isPresent()).findFirst().flatMap(o->o);
        return searchx(queue.poll());
    }

}
