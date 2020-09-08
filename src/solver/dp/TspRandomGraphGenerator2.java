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
import function.Holder;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author masao
 */
public class TspRandomGraphGenerator2 {
    static TspRandomGraphGenerator2 generator2(int vertices, int width) {
        return new TspRandomGraphGenerator2(vertices, width);
    }
    int vertices;
    int width;
    public TspRandomGraphGenerator2(int vertices, int width) {
        this.vertices=vertices;
        this.width=width;
    }
    public int[][] generate() {
        int[][] retval=new int[vertices][width];
        Random r=new Random();
        for(int i=0;i<vertices;i++) retval[i]=r.ints(width,2,20).toArray();
        return retval;
    }
    public TList<TList<Integer>> generateT() {
        return TList.sof(generate()).map(a->TList.set(wrap(a)));
    }
    public TList<TList<P<Integer,Integer>>> generateSorted() {
        Holder<Integer> h=new Holder<>(0);
        return generateT().map(l->TList.range(h.get(),h.push(h.get()+1)+width).map(i->i%vertices).sfix().pair(l).sortTo((a,b)->a.r().compareTo(b.r())).sfix());
    }
    public MVMap<Integer,P<Integer,Integer>> generateMap() {
        HashMap<Integer,TList<P<Integer,Integer>>> map=new HashMap<>();
        TList.range(0,vertices).pair(generateSorted()).forEach(p->map.put(p.l(),p.r()));
        return new MVMap<>(map);
    }
}
