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
public interface BareGraph<K> {
    public TList<K> next(K from);
    public TList<K> all();
}
