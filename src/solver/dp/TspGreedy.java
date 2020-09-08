/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.MVOrderedMap;
import collection.P;
import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import java.util.HashMap;
import static solver.dp.Tsp.initTable;
import static solver.dp.Tsp.sentinel;

/**
 *
 * @author masao
 */
public class TspGreedy {
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
        public TspGreedy build() {
            return new TspGreedy(vertices,graph);
        }
    }
    int[][] graph;
    int vertices;
    int start=0;
    public TspGreedy(int vertices,int[][] graph) {
        this.vertices=vertices;
        this.graph=graph;
    }
    public int[] solve() {
        int[] route=new int[vertices];
        for (int i=0;i<vertices;i++) route[i]=i;
        for (int i=0;i<vertices-1;i++) {
            int min=graph[route[i]][route[i+1]];
            int minat=i+1;
            for (int j=i+1;j<vertices;j++) 
                if (graph[route[i]][route[j]]<min) {
                    min=graph[route[i]][route[j]];
                    minat=j;
                }
            int next=route[i+1];
            route[i+1]=route[minat];
            route[minat]=next;
        }
        return route;
    }
}
