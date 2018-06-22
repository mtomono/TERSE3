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
import java.util.*;
import static solver.path.ShortestPathD.NodeStatus.*;

/**
 * shortest path with dijkstra algorithm
 * @author masao
 * @param <N>
 */
public class ShortestPathD<N> {
    public enum NodeStatus {
        NONE,
        OPEN,
        CLOSED;
    }
    public class Node {
        final public N at;
        public NodeStatus status;
        public int distance;
        public Node(N at) {
            this.at = at;
            status = NONE;
            distance = 0;
        }
        
        Node status(NodeStatus s) {
            this.status = s;
            return this;
        }
        
        Node distance(int d) {
            this.distance = d;
            return this;
        }
    }
    Map<P<N,N>, Integer> graph;
    PriorityQueue<Node> queue;
    Map<N, Node> nodes;
    
    public ShortestPathD(Map<P<N, N>, Integer> graph) {
        this.graph = graph;
        this.queue = new PriorityQueue<>(Comparator.comparing(n->n.distance));
        this.nodes = new HashMap<>();
        graph.keySet().forEach(p->{nodes.put(p.l(),new Node(p.l()));nodes.put(p.r(),new Node(p.r()));});
    }
    
    Node open(N at) {
        return nodes.get(at).status(OPEN);
    }
    Node close(N at) {
        return nodes.get(at).status(CLOSED);
    }
    NodeStatus status(N at) {
        return nodes.get(at).status;
    }
    boolean isClosed(N at) {
        return status(at).equals(CLOSED);
    }
    boolean isNone(N at) {
        return status(at).equals(NONE);
    }
    int distance(N at) {
        return nodes.get(at).distance;
    }
    Node distance(N at, int distance) {
        return nodes.get(at).distance(distance);
    }
    Node requeue(N at) {
        queue.removeIf(n->n.at.equals(at));
        queue.offer(nodes.get(at));
        return nodes.get(at);
    }
    Node renewWhenCloser(N at, Integer distance) {
        if (distance(at)>distance||isNone(at)) {
            distance(at, distance);
            open(at);
            requeue(at);
        }
        return nodes.get(at);
    }
    
    public Optional<Integer> find(N from, N to) {
        distance(from,0);
        fillMap(close(from));
        return isClosed(to)?Optional.of(nodes.get(to).distance):Optional.empty();
    }

    public void fillMap(Node from) {
        TList.set(graph.keySet()).filter(p->p.l().equals(from.at)&&!isClosed(p.r())).
                forEach(p->renewWhenCloser(p.r(),distance(from.at)+graph.get(p)));
        if (queue.isEmpty())
            return;
        fillMap(close(queue.poll().at));
    }
}
