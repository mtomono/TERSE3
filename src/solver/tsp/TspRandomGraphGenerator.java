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

import collection.MVMap;
import collection.P;
import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author masao
 */
public class TspRandomGraphGenerator {
    public static TspRandomGraphGenerator generator(int vertices) {
        return new TspRandomGraphGenerator(vertices);
    }
    int vertices;
    public TspRandomGraphGenerator(int vertices) {
        this.vertices=vertices;
    }
    public int[][] generate() {
        int[][] retval=new int[vertices][vertices];
        Random r=new Random();
        for(int i=0;i<vertices;i++) retval[i]=r.ints(vertices,2,20).toArray();
        return retval;
    }
    public TList<TList<Integer>> generateT() {
        return TList.sof(generate()).map(a->TList.set(wrap(a)));
    }
    public TList<TList<P<Integer,Integer>>> generateSorted() {
        return generateT().map(l->TList.range(0,vertices).pair(l).sortTo((a,b)->a.r().compareTo(b.r())));
    }
    public MVMap<Integer,P<Integer,Integer>> generateMap() {
        HashMap<Integer,TList<P<Integer,Integer>>> map=new HashMap<>();
        TList.range(0,vertices).pair(generateSorted()).forEach(p->map.put(p.l(),p.r()));
        return new MVMap<>(map);
    }
}
