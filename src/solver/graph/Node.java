/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import java.util.Optional;
import static solver.graph.NodeStatus.CLOSED;
import static solver.graph.NodeStatus.NONE;
import static solver.graph.NodeStatus.OPEN;

/**
 *
 * @author masao
 */
public class Node<K> {
    NodeStatus status;
    final public K at;
    Optional<K> parent;
    double distance;
    
    public Node(K at) {
        this.at=at;
        this.status=NONE;
        this.parent=Optional.empty();
        this.distance=0;
    }
    
    public Node<K> setStatus(NodeStatus status) {
        this.status=status;
        return this;
    }
    
    public Node<K> setDistance(double distance) {
        this.distance=distance;
        return this;
    }
    
    public Node<K> setParent(Optional<K> parent) {
        this.parent=parent;
        return this;
    }
    
    public boolean isBetterParent(Node<K> from, double lastStep) {
        return !isClosed()&&(distance>from.distance+lastStep||isNone());
    }
    
    public Node<K> changeParent(Node<K> from, double lastStep) {
        this.distance=from.distance+lastStep;
        this.parent=Optional.of(from.at);
        return this;
    }
    
    public Node<K> open() {
        this.status=OPEN;
        return this;
    }
    public Node<K> close() {
        this.status=CLOSED;
        return this;
    }
    
    public NodeStatus status() {
        return status;
    }
    public boolean isClosed() {
        return status.equals(CLOSED);
    }
    public boolean isNone() {
        return status.equals(NONE);
    }
    public double distance() {
        return distance;
    }
    
    public double score() {
        return distance;
    }
    
    public Optional<K> parent() {
        return parent;
    }
    
    @Override
    public String toString() {
        return at.toString()+":"+status+":"+parent+distance;
    }
}
