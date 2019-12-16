/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.TList;

/**
 *
 * @author masao
 */
public class CompositeGraph<K> implements Graph<K> {
    final public TList<Graph<K>> body;
    
    public CompositeGraph(TList<Graph<K>> body) {
        this.body = body;
    }
    
    public CompositeGraph(Graph<K>... body) {
        this(TList.sof(body));
    }

    @Override
    public TList<K> next(K from) {
        return body.map(g->g.next(from)).sfix().flatMap(l->l).sfix();
    }

    @Override
    public TList<K> all() {
        return body.map(g->g.all()).sfix().flatMap(l->l).sfix();
    }
}
