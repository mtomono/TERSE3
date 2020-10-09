/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.tsp;

import solver.tsp.TspRandomGraphGenerator;
import solver.tsp.TspRandomGraphGenerator2;
import collection.MVMap;
import collection.P;
import collection.TList;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TspRandomGraphGeneratorNGTest {
    
    public TspRandomGraphGeneratorNGTest() {
    }
    int vertices=200;
    @Test
    public void testGenerate() {
        int[][] n=TspRandomGraphGenerator.generator(40).generate();
        for(int i=0;i<40;i++) for(int j=0;j<40;j++) System.out.print(":"+n[i][j]);
    }
    @Test
    public void testGenerateT() {
        TList<TList<Integer>> n=TspRandomGraphGenerator.generator(40).generateT();
        for(int i=0;i<40;i++) for(int j=0;j<40;j++) System.out.print(":"+n.get(i).get(j));
    }
    @Test
    public void testGenerateT2() {
        TList<TList<Integer>> n=TspRandomGraphGenerator2.generator2(40,10).generateT();
        for(int i=0;i<40;i++) for(int j=0;j<10;j++) System.out.print(":"+n.get(i).get(j));
    }
    @Test
    public void testGenerateSorted() {
        TList<TList<P<Integer,Integer>>> n=TspRandomGraphGenerator.generator(40).generateSorted();
        for(int i=0;i<40;i++) for(int j=0;j<40;j++) System.out.print(":"+n.get(i).get(j));
    }
    @Test
    public void testGenerateSorted2() {
        TList<TList<P<Integer,Integer>>> n=TspRandomGraphGenerator2.generator2(vertices,10).generateSorted();
        for(int i=0;i<vertices;i++) for(int j=0;j<10;j++) System.out.print(":"+n.get(i).get(j));
    }
    @Test
    public void testGenerateSorted2Wrapped() {
        TList<TList<P<Integer,Integer>>> n=TspRandomGraphGenerator2.generator2(vertices,10).generateSorted();
        System.out.println(n.toWrappedString());
    }
    @Test
    public void testGenerateMap() {
        MVMap<Integer,P<Integer,Integer>> n=TspRandomGraphGenerator.generator(40).generateMap();
        for(int i=0;i<40;i++) for(int j=0;j<40;j++) System.out.print(":"+n.getList(i).get(j));
    }
}
