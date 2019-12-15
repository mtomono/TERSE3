/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.P;
import collection.TList;

/**
 *
 * @author masao
 */
public interface MetricGraph<K> {
    public TList<P<K,Double>> next(K from);
    public TList<K> all();
    public double heuristic(K from, K to);
}
