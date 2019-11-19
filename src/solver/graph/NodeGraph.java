/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

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
    Graph<K> graph;
    Map<K,Node<K>> nodes;
    PriorityQueue<Node<K>> queue;
    K from;
    K to;
    
    public NodeGraph(Graph<K> graph, Map<K,Node<K>> nodes, K from, K to) {
        this.graph=graph;
        this.nodes=nodes;
        this.queue = new PriorityQueue<>(Comparator.comparing(n->n.score()));
        this.from=from;
        this.to=to;
    }
    
    public Node<K> get(K at) {
        return nodes.get(at);
    }
    
    static public class Exit extends RuntimeException {
    }
    
    public NodeGraph<K> fill() {
        return fill(this::fill);
    }

    public NodeGraph<K> fillLoop() {
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
        
    public Node<K> changeParentWhenCloser(Node<K> at, Node<K> from, double fromToAt) {
        if (!(at.distance()>cost(at,from,fromToAt)||at.isNone()))
            return at;
        at.distance = cost(at, from, fromToAt);
        at.parent = Optional.of(from.at);
        requeue(at.open());
        return at;
    }
    
    public double cost(Node<K> at, Node<K> from, double fromToAt) {
        return from.distance+fromToAt;
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

    public Node<K> fillCore(Node<K> from) {
        graph.next(from.at)
                .filter(p->!get(p.l()).isClosed())
                .forEach(p->changeParentWhenCloser(get(p.l()),from,p.r()));
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
        return get(retval.last()).parent.map(p->findRoute(retval.addOne(p))).orElse(retval);
    }
}
