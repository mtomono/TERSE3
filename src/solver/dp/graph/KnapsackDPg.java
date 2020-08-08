package solver.dp.graph;


import java.util.List;
import shapeCollection.GridOverList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author masao
 */
public class KnapsackDPg<T,R> {
    GridOverList<R> grid;
    @FunctionalInterface
    public interface Path {
        List<Integer> next(List<Integer> from);
    }
}
