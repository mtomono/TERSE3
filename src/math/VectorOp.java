/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 *
 * @author masao
 */
public class VectorOp {
    static public <T> List<T> op(List<T> a, List<T> b, BinaryOperator<T> f) {
        return TList.set(a).pair(b, (x,y)->f.apply(x,y)); 
    }
    
    static public List<Integer> subI(List<Integer>a, List<Integer>b) {
        return op(a,b,(x,y)->x-y);
    }
    static public List<Integer> addI(List<Integer>a, List<Integer>b) {
        return op(a,b,(x,y)->x+y);
    }
    static public List<Double> subD(List<Double>a, List<Double>b) {
        return op(a,b,(x,y)->x-y);
    }
    static public List<Double> addD(List<Double>a, List<Double>b) {
        return op(a,b,(x,y)->x+y);
    }
    static public List<Double> scaleD(List<Double>a, double scale) {
        return TList.set(a).map(x->x*scale);
    }
}
