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

import static collection.PrimitiveArrayWrap.unwrapI;
import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static solver.dp.Tsp.initTable;
import static solver.dp.Tsp.sentinel;

/**
 *
 * @author masao
 */
public class TspGreedyS {
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
        public TspGreedyS build() {
            return new TspGreedyS(vertices,graph);
        }
    }
    int vertices;
    int[][] graph;
    int[] route;
    int start=0;
    public TspGreedyS(int vertices,int[][] graph) {
        this.vertices=vertices;
        this.graph=graph;
        this.route=unwrapI(TList.range(0,vertices));
    }
    public int distance(int i,int j) {
        return graph[route[i]][route[j]];
    }
    public void swap(int i, int j) {
        int buf=route[i];
        route[i]=route[j];
        route[j]=buf;
    }
    public TList<Integer> solve() {
        TList.range(0,vertices-1).forEach(i->swap(i+1,min(i)));
        return TList.set(wrap(route));
    }
    public int min(int i) {
        int min=distance(i,i+1);
        int minat=i+1;
        for(int j=i+2;j<vertices;j++) {
            if (distance(i,j)<min) {
                min=distance(i,j);
                minat=j;
            }
        }
        return minat;
    }
}
