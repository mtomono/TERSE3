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

import collection.TList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 *
 * @author masao
 */
public class NodeGraphBuilder<K> {
    Supplier<Map<K,Node<K>>> nodesSupplier;
    MetricGraph<K> graph;
    K from;
    K to;
    NodeGraph<K> built;
    
    static public <K> NodeGraphBuilder<K> builder(MetricGraph<K> graph,K from,K to) {
        return new NodeGraphBuilder<>(graph,from,to);
    }
    static public <K> NodeGraphBuilder<K> builder(Metric<K> metric, Graph<K> graph, K from, K to) {
        return new NodeGraphBuilder<>(new MetricizedGraph<>(metric, graph), from, to);
    }
    public NodeGraphBuilder(MetricGraph<K> graph,K from,K to) {
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
        this.built=new NodeGraph(graph,nodesSupplier.get(),from,to).waitUntilEnd();
        return this;
    }
    public NodeGraphBuilder<K> earlyExit() {
        this.built=new NodeGraph(graph,nodesSupplier.get(),from,to).earlyExit();
        return this;
    }
    public NodeGraphBuilder<K> node() {
        assert built != null : "nodeGraph is not ready";
        graph.all().forEach(k->built.nodes.put(k,new Node(k)));
        return this;
    }
    public NodeGraphBuilder<K> astar(Metric<? super K> heuristic) {
        assert built != null : "nodeGraph is not ready";
        graph.all().forEach(k->built.nodes.put(k,new AStarNode(k,heuristic.measure(k,to))));
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
