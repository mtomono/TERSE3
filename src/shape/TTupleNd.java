/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import collection.TList;
import static java.lang.Math.round;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class TTupleNd  extends TList<Double> {

    public TTupleNd(List<Double> body) {
        super(body);
    }
    public TTupleNd c(List<Double> body) {
        return new TTupleNd(body);
    }
    public TTupleNd bop(List<Double> b, BinaryOperator<Double> f) {
        return c(pair(b, (x,y)->f.apply(x,y))); 
    }
    public TTupleNd op(UnaryOperator<Double> f) {
        return c(map(f)); 
    }
    public TTupleNd sub(TTupleNd b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNd add(TTupleNd b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNd scale(double s) {
        return op(x->x*s);
    }
    public TTupleNd mul(TTupleNd b) {
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
    public TTupleNl round() {
        return new TTupleNl(map(Math::round));
    }
}