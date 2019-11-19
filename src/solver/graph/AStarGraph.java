/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

/**
 *
 * @author masao
 */
public interface AStarGraph<K> extends Graph<K> {
    public double heuristic(K from, K to);
}
