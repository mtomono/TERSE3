/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import java.util.Map;

/**
 *
 * @author masao
 */
public class EarlyExitNodeGraph<K> extends NodeGraph<K> {
    
    public EarlyExitNodeGraph(Graph<K> graph, Map<K, Node<K>> nodes, K from, K to) {
        super(graph, nodes, from, to);
    }
    
    @Override
    public Node<K> fillCore(Node<K> from) {
        if (from.at.equals(to))
            throw new Exit();
        return super.fillCore(from);
    }
}
