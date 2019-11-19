/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import static solver.graph.NodeStatus.BLOCKED;
import static solver.graph.NodeStatus.NONE;

/**
 *
 * @author masao
 */
public class AStarNode<K> extends Node<K> {
    double heuristic;
    
    public AStarNode(K at, double heurestic) {
        super(at);
        this.heuristic = heurestic;
    }
    
    public double score() {
        return this.distance+this.heuristic;
    }
    
}
