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
public class MetricGeneralGraphBuilder<K> {
    public static <K> MetricGeneralGraphBuilder<K> builder() {
        return new MetricGeneralGraphBuilder<>();
    }
    
    public MetricGeneralGraphBuilder() {
        this.body=new MVMap<>(new HashMap<>());
    }
    
    static class FromToCost<K> {
        K from;
        K to;
        Double cost;
        
        public FromToCost(K from, K to,Double cost) {
            this.from=from;
            this.to=to;
            this.cost=cost;
        }
        public K key() {
            return from;
        }
        public P<K,Double> value() {
            return P.p(to,cost);
        }
    }

    public static <K> Map<K,TList<P<K,Double>>> graph(Object... fromToCosts) {
        MVMap<K,P<K,Double>> retval = new MVMap(new HashMap<>());
        TList.sof(fromToCosts).fold(3).map(p->new FromToCost<>((K)p.get(0),(K)p.get(1),(Double)p.get(2))).forEach(f->retval.getList(f.key()).add(f.value()));
        return retval;
    }
    
    MVMap<K,P<K,Double>> body;
    
    public MetricGeneralGraphBuilder<K> merge(Object... fromToCosts) {
        this.body.merge(MetricGeneralGraphBuilder.graph(fromToCosts));
        return this;
    }
    
    public MetricGeneralGraphBuilder<K> a(K from, K to, double cost) {
        body.getList(from).add(P.p(to, cost));
        return this;
    }
    
    public MetricGeneralGraph<K> build(Object... fromToCosts) {
        return new MetricGeneralGraph<>(body.merge(graph(fromToCosts)));
    }
    
    public MetricGeneralGraph<K> build() {
        return new MetricGeneralGraph<>(body);
    }
}
