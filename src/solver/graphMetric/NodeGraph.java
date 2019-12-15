/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graphMetric;

import collection.P;
import collection.TList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Consumer;
import solver.graph.Node;

/**
 *
 * @author masao
 * @param <K>
 */
public class NodeGraph<K> {
    Metric<K> metric;
    Graph<K> graph;
    Map<K,Node<K>> nodes;
    PriorityQueue<Node<K>> queue;
    Consumer<Node<K>> exitPolicy;
    K from;
    K to;
    
    public NodeGraph(Metric<K> metric, Graph<K> graph, Map<K,Node<K>> nodes, K from, K to) {
        this.metric=metric;
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
        assert nodes.get(at)!=null:"no such element in graph";
        return nodes.get(at);
    }
    
    static public class Exit extends RuntimeException {
    }
    
    public NodeGraph<K> fill() {
        return fill(this::fill);
    }

    public NodeGraph<K> fillLoop() {
        assert get(from).isNone() : "from is not reacheable";
        assert get(to).isNone() : "to is not reacheable";
        return fill(this::fillLoop);
    }
    
    public void fill(Node<K> from) {
        fill(fillCore(from));
    }
    
    public void fillLoop(Node<K> current) {
        while (true) {
            current=fillCore(current);
        }
    }

    public Node<K> requeue(Node<K> at) {
        queue.removeIf(n->n==at);
        queue.offer(at);
        return at;
    }
    
    public double cost(Node<K> at, Node<K> from) {
        return from.distance()+metric.measure(from.at,at.at);
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

    public Node<K> fillCore(Node<K> from) {
        exitPolicy.accept(from);
        graph.next(from.at)
                .map(p->get(p)).map(p->P.p(p, cost(p,from)))
                .filter(p->p.l().isBetterParent(from, p.r()))
                .forEach(p->requeue(p.l().changeParent(from, p.r()).open()));
        if (queue.isEmpty())
            throw new Exit();
        return queue.poll().close();
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
