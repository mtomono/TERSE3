/*
 Copyright 2017, 2018, 2019 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
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
        return graph(TList.sof(fromTo));
    }
    public static <K> Map<K,TList<K>> graph(TList<K> fromTo) {
        MVMap<K,K> retval = new MVMap(new HashMap<>());
        fromTo.fold(2).map(p->P.p(p.get(0),p.get(1))).forEach(f->retval.getList(f.l()).add(f.r()));
        return retval;
    }
    
    MVMap<K,K> body;
    
    public GeneralGraphBuilder<K> merge(K... fromTo) {
        return merge(TList.sof(fromTo));
    }
    public GeneralGraphBuilder<K> merge(TList<K> fromTo) {
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
