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
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 *
 * @author masao
 * @param <K>
 */
public class NodeGraph<K> {
    MetricGraph<K> graph;
    Map<K,Node<K>> nodes;
    PriorityQueue<Node<K>> queue;
    Consumer<Node<K>> exitPolicy;
    K from;
    K to;
    
    public NodeGraph(MetricGraph<K> graph, Map<K,Node<K>> nodes, K from, K to) {
        this.graph=graph;
        this.nodes=nodes;
        this.queue = new PriorityQueue<>(Comparator.comparing(n->n.score()));
        this.from=from;
        this.to=to;
        this.exitPolicy=this::waitUntilEnd;
    }
    
    public NodeGraph<K> earlyExit() {
        this.exitPolicy=this::earlyExit;
        return this;
    }
    
    public NodeGraph<K> waitUntilEnd() {
        this.exitPolicy=this::waitUntilEnd;
        return this;
    }
    
    public Node<K> get(K at) {
        assert nodes.get(at)!=null:"no such element as "+at+" in graph";
        return nodes.get(at);
    }
    
    static public class Exit extends RuntimeException {
    }
    
    public NodeGraph<K> fill() {
        return fill(this::fill);
    }

    public void fill(Node<K> from) {
        fill(fillCore(from));
    }
    
    public NodeGraph<K> fillLoop() {
        assert get(from).isNone() : "from is not reacheable";
        assert get(to).isNone() : "to is not reacheable";
        return fill(this::fillLoop);
    }
    
    public void fillLoop(Node<K> current) {
        while (true) {
            current=fillCore(current);
        }
    }

    public NodeGraph<K> fill(Consumer<Node<K>> sweep) {
        get(from).setDistance(0);
        Node<K> current=get(from).close();
        try {
            sweep.accept(current);
        } catch(Exit e) {
        }
        return this;
    }
    
    public void earlyExit(Node<K> from) {
        if (from.at.equals(to))
            throw new Exit();
    }
    
    public void waitUntilEnd(Node<K> from) {
        
    }

    public TList<P<Node<K>,Double>> candidatesAndDistances(Node<K> from) {
        return graph.next(from.at).map(p->P.p(get(p.l()), p.r()));
    }

    public Node<K> fillCore(Node<K> from) {
        exitPolicy.accept(from);
        candidatesAndDistances(from)
                .filter(p->p.l().isBetterParent(from, p.r()))
                .forEach(p->requeue(p.l().changeParent(from, p.r()).open()));
        if (queue.isEmpty())
            throw new Exit();
        return queue.poll().close();
    }
    
    public Node<K> requeue(Node<K> at) {
        queue.removeIf(n->n==at);
        queue.offer(at);
        return at;
    }
    
    public Optional<Double> findCost() {
        return get(to).isClosed()?Optional.of(get(to).distance()):Optional.empty();
    }
    
    public TList<K> findRoute() {
        if (!get(to).isClosed())
            return TList.empty();
        TList<K> retval = TList.c();
        retval.add(to);
        return findRoute(retval).reverse();
    }
    
    public TList<K> findRoute(TList<K> retval) {
        return get(retval.last()).parent().map(p->findRoute(retval.addOne(p))).orElse(retval);
    }
}
