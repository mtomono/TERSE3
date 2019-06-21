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
public class TTupleNl extends TList<Long> {
    public TTupleNl(List<Long> body) {
        super(body);
    }
    public TTupleNl c(List<Long> body) {
        return new TTupleNl(body);
    }
    public TTupleNl bop(List<Long> b, BinaryOperator<Long> f) {
        return c(pair(b, (x,y)->f.apply(x,y))); 
    }
    public TTupleNl op(UnaryOperator<Long> f) {
        return c(map(f)); 
    }
    public TTupleNl sub(TTupleNl b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNl add(TTupleNl b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNl scale(long s) {
        return op(x->x*s);
    }
    public TTupleNl mul(TTupleNl b) {
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
