/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author masao
 */
public class NodeGraphBuilder<K> {
    Supplier<Map<K,Node<K>>> nodesSupplier;
    Graph<K> graph;
    K from;
    K to;
    NodeGraph<K> built;
    
    static public <K> NodeGraphBuilder<K> builder(Graph<K> graph,K from,K to) {
        return new NodeGraphBuilder<>(graph,from,to);
    }
    public NodeGraphBuilder(Graph<K> graph,K from,K to) {
        this.nodesSupplier=()->new HashMap<>();
        this.graph=graph;
        this.from=from;
        this.to=to;
    }
    public NodeGraphBuilder<K> mapSupplier(Supplier<Map<K,Node<K>>> nodesSupplier) {
        this.nodesSupplier = nodesSupplier;
        return this;
    }
    public NodeGraphBuilder<K> fullSearch() {
        this.built=new NodeGraph(graph,nodesSupplier.get(),from,to);
        return this;
    }
    public NodeGraphBuilder<K> earlyExit() {
        this.built=new EarlyExitNodeGraph(graph,nodesSupplier.get(),from,to);
        return this;
    }
    public NodeGraphBuilder<K> node() {
        assert built != null : "nodeGraph is not ready";
        graph.all().forEach(k->built.nodes.put(k,new Node(k)));
        return this;
    }
    public NodeGraphBuilder<K> astar() {
        assert built != null : "nodeGraph is not ready";
        assert graph instanceof AStarGraph : "graph is not AStarGraph";
        graph.all().forEach(k->built.nodes.put(k,new AStarNode(k,((AStarGraph)graph).heuristic(k,to))));
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
