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
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;
import static solver.path.AStarStatus.*;

/**
 *
 * @author masao
 * @param <T>
 */
public abstract class AStar<T extends AStarNode> {
    LinkedList<T> qXXX;
    PriorityQueue<T> queue;

    abstract public int costToGo(T astar);
    abstract public TList<T> candidates(T astar);
    abstract public T getStart();
    abstract public T getGoal();
    abstract public boolean isGoal(T astar);
    abstract public int upperBoundary();
    abstract public int costToMove(Optional<T> from, T to);
    
    public AStar() {
        this.queue = new PriorityQueue<>(Comparator.comparing(n->n.score()));
    }
        
    int costToCome(T target, Optional<T> parent) {
        return parent.map(a->a.costToCome).orElse(0)+costToMove(parent, target);
    }
    
    T openIfBetter(T astar, Optional<T> parent) {
        return astar.status==NONE||astar.costToCome > costToCome(astar,parent)+costToGo(astar)?
                open(astar,parent):astar;
    }
        
    T open(T target, Optional<T> parent) {
        if (target.status==OPEN) queue.remove(target);
        target.open(parent, costToCome(target,parent), costToGo(target));
        queue.offer(target);
        return target;
    }
    
    public Optional<T> search() {
        open(getStart(),Optional.empty());
        return searchx();
    }
        
    Optional<T> searchx() {
        while (true) {
            if (queue.isEmpty())
                return Optional.empty();
            T target = queue.poll();
            target.close();
            if (isGoal(target))
                return Optional.of(target);
            TList<T> candidates = candidates(target).fix();
            Optional<T> parent = Optional.of(target);
            candidates.forEach(a->openIfBetter(a,parent));
        }
    }
}
