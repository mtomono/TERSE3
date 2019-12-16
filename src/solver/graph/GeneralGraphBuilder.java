/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.graph;

import collection.MVMap;
import collection.P;
import collection.TList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author masao
 */
public class GeneralGraphBuilder<K> {
    public static <K> GeneralGraphBuilder<K> builder() {
        return new GeneralGraphBuilder<>();
    }
    
    public GeneralGraphBuilder() {
        this.body=new MVMap<>(new HashMap<>());
    }
    
    public static <K> Map<K,TList<K>> graph(K... fromTo) {
        MVMap<K,K> retval = new MVMap(new HashMap<>());
        TList.sof(fromTo).fold(2).map(p->P.p(p.get(0),p.get(1))).forEach(f->retval.getList(f.l()).add(f.r()));
        return retval;
    }
    
    MVMap<K,K> body;
    
    public GeneralGraphBuilder<K> merge(K... fromTo) {
        this.body.merge(GeneralGraphBuilder.graph(fromTo));
        return this;
    }
    
    public GeneralGraphBuilder<K> a(K from, K to) {
        body.getList(from).add(to);
        return this;
    }
    
    public GeneralGraph<K> build(K... fromTo) {
        return new GeneralGraph<>(body.merge(graph(fromTo)));
    }
    
    public GeneralGraph<K> build() {
        return new GeneralGraph<>(body);
    }
}
