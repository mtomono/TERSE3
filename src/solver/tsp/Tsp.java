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
package solver.tsp;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;

/**
 * This is a strict solver of Tsp.
 * using DP.
 * @author masao
 */
public class Tsp {
    public static final int sentinel=-1;
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
        public Tsp build() {
            return new Tsp(vertices,graph);
        }
    }
    int[][] graph;
    int start=0;
    int vertices;
    int[][] dp;
    public Tsp(int vertices,int[][] graph) {
        this.vertices=vertices;
        this.graph=graph;
        dp=new int[1<<vertices][vertices];
        initTable(dp,sentinel);
        dp[0][start]=0;
    }
    public int solve() {
        for (int S=1; S<(1<<vertices);S++)
            for(int from=0;from<vertices;from++) for(int to=0;to<vertices;to++) 
                if ((S&(1<<from))!=0) if (graph[from][to]!=sentinel&&dp[S-(1<<from)][from]!=sentinel)
                    dp[S][to]=Integer.min(dp[S][to]==sentinel?Integer.MAX_VALUE:dp[S][to],dp[S-(1<<from)][from]+graph[from][to]);
        return dp[(1<<vertices)-1][start];
    }
    public TList<TList<Integer>> dp() {
        return toTList(dp);
    }
    static public void initTable(int[][] table, int v) {
        for(int i=0;i<table.length;i++) for(int j=0;j<table[0].length;j++) table[i][j]=v;
    }
    static public TList<TList<Integer>> toTList(int[][] table) {
        return TList.sof(table).map(a->TList.set(wrap(a)));
    }
}
