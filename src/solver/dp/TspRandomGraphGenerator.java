/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

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
    static TspRandomGraphGenerator generator(int vertices) {
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
