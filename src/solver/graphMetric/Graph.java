/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graphMetric;

import collection.P;
import collection.TList;

/**
 *
 * @author masao
 */
public interface Graph<K> {
    public TList<K> next(K from);
    public TList<K> all();
}
