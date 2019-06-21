/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class TTupleNi extends TList<Integer> {

    public TTupleNi(List<Integer> body) {
        super(body);
    }
    public TTupleNi c(List<Integer> body) {
        return new TTupleNi(body);
    }
    public TTupleNi bop(List<Integer> b, BinaryOperator<Integer> f) {
        return c(pair(b, (x,y)->f.apply(x,y))); 
    }
    public TTupleNi op(UnaryOperator<Integer> f) {
        return c(map(f)); 
    }
    public TTupleNi sub(TTupleNi b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNi add(TTupleNi b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNi scale(Integer s) {
        return op(x->x*s);
    }
    public TTupleNi mul(TTupleNi b) {
        return bop(b,(x,y)->x*y);
    }
    public TTupleNi toI() {
        return new TTupleNi(map(e->e.intValue()));
    }
    public TTupleNl toL() {
        return new TTupleNl(map(e->e.longValue()));
    }
    public TTupleNd toD() {
        return new TTupleNd(map(e->e.doubleValue()));
    }
}
