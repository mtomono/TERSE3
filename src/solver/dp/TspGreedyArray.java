/*
 Copyright 2017, 2018, 2019, 2020 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package solver.dp;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static solver.dp.Tsp.initTable;
import static solver.dp.Tsp.sentinel;

/**
 *
 * @author masao
 */
public class TspGreedyArray {
    static public Builder builder(int vertices) {
        return new Builder(vertices);
    }
    static public class Builder {
        int[][] graph;
        int vertices;
        public Builder(int vertices) {
            this.vertices=vertices;
            graph=new int[vertices][vertices];
            initTable(graph,sentinel);
        }
        public Builder e(TList<Integer> graph) {
            graph.fold(3).forEach(l->this.graph[l.get(0)][l.get(1)]=l.get(2));
            return this;
        }
        public Builder e(int... graph) {
            return e(TList.set(wrap(graph)));
        }
        public TspGreedyArray build() {
            return new TspGreedyArray(vertices,graph);
        }
    }
    int[][] graph;
    int vertices;
    int start=0;
    int[] route;
    public TspGreedyArray(int vertices,int[][] graph) {
        this.vertices=vertices;
        this.graph=graph;
        this.route=new int[vertices];
        for (int i=0;i<vertices;i++) route[i]=i;
    }
    public int distance(int i, int j) {
        return graph[route[i]][route[j]];
    }
    public TList<Integer> solve() {
        for (int i=0;i<vertices-1;i++) {
            int min=distance(i,i+1);
            int minat=i+1;
            for (int j=i+1;j<vertices;j++) 
                if (distance(i,j)<min) {
                    min=distance(i,j);
                    minat=j;
                }
            int next=route[i+1];
            route[i+1]=route[minat];
            route[minat]=next;
        }
        return TList.set(wrap(route));
    }
}
