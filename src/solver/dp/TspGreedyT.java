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

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
import static collection.PrimitiveArrayWrap.unwrapI;
import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static solver.dp.Tsp.initTable;
import static solver.dp.Tsp.sentinel;

/**
 *
 * @author masao
 */
public class TspGreedyT {
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
        public TspGreedyT build() {
            return new TspGreedyT(vertices,graph);
        }
    }
    int vertices;
    int start=0;
    ArrayInt route;
    ArrayInt2 grapha;
    public TspGreedyT(int vertices,int[][] graph) {
        this.vertices=vertices;
        this.grapha=new ArrayInt2(graph);
        this.route=arrayInt(unwrapI(TList.range(0,vertices)));
    }
    public int distance(int i,int j) {
        return grapha.get(i,j);
    }
    public TList<Integer> solve() {
        route.index().seek(-1).forEach(i->route.seek(i+1).swap(0,route.seek(i+1).minIndex(j->distance(i,j))));
        return route.asList();
    }
}
