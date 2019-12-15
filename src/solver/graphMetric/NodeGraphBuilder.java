/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graphMetric;

import solver.graph.BareGraph;
import solver.graph.Metric;
import collection.TList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import solver.graph.AStarNode;
import solver.graph.Node;
import solver.graph.NodeStatus;

/**
 *
 * @author masao
 */
public class NodeGraphBuilder<K> {
    Supplier<Map<K,Node<K>>> nodesSupplier;
    Metric<K> metric;
    BareGraph<K> graph;
    K from;
    K to;
    NodeGraph<K> built;
    
    static public <K> NodeGraphBuilder<K> builder(Metric<K> metric, BareGraph<K> graph,K from,K to) {
        return new NodeGraphBuilder<>(metric, graph,from,to);
    }
    public NodeGraphBuilder(Metric<K> metric, BareGraph<K> graph,K from,K to) {
        this.nodesSupplier=()->new HashMap<>();
        this.metric=metric;
        this.graph=graph;
        this.from=from;
        this.to=to;
    }
    public NodeGraphBuilder<K> mapSupplier(Supplier<Map<K,Node<K>>> nodesSupplier) {
        this.nodesSupplier = nodesSupplier;
        return this;
    }
    public NodeGraphBuilder<K> fullSearch() {
        this.built=new NodeGraph(metric,graph,nodesSupplier.get(),from,to).waitUntilEnd();
        return this;
    }
    public NodeGraphBuilder<K> earlyExit() {
        this.built=new NodeGraph(metric,graph,nodesSupplier.get(),from,to).earlyExit();
        return this;
    }
    public NodeGraphBuilder<K> node() {
        assert built != null : "nodeGraph is not ready";
        graph.all().forEach(k->built.nodes.put(k,new Node(k)));
        return this;
    }
    public NodeGraphBuilder<K> astar() {
        assert built != null : "nodeGraph is not ready";
        graph.all().forEach(k->built.nodes.put(k,new AStarNode(k,metric.measure(k,to))));
        return this;
    }
    public NodeGraphBuilder<K> block(Collection<K> blocks) {
        assert !built.nodes.isEmpty() : "nodes are not ready";
        TList.set(blocks).forEach(p->built.nodes.get(p).close());
        return this;
    }
    public NodeGraphBuilder<K> white(Collection<K> white) {
        assert !built.nodes.isEmpty() : "nodes are not ready";
        built.nodes.values().forEach(n->n.setStatus(NodeStatus.BLOCKED));
        TList.set(white).forEach(p->built.nodes.get(p).setStatus(NodeStatus.NONE));
        return this;
    }
    public NodeGraph<K> build() {
        return built;
    }
}
