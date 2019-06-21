/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import static arithmetic.Arithmetic.add;
import static arithmetic.Arithmetic.mul;
import static arithmetic.Arithmetic.sub;
import collection.TList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 * @param <T>
 */
public class TTupleN<T extends Number> extends TList<T> {

    public TTupleN(List<T> body) {
        super(body);
    }
    public TTupleN<T> bop(List<T> b, BinaryOperator<T> f) {
        return new TTupleN<>(pair(b, (x,y)->f.apply(x,y))); 
    }
    
    public TTupleN<T> op(UnaryOperator<T> f) {
        return new TTupleN<>(map(f)); 
    }
    
    public TTupleN<T> add(TTupleN<T> target) {
        return bop(target, (a,b)->(T)add.o(a,b));
    }
    
    public TTupleN<T> sub(TTupleN<T> target) {
        return bop(target, (a,b)->(T)sub.o(a,b));
    }
    
    public TTupleN<T> mul(TTupleN<T> target) {
        return bop(target, (a,b)->(T)mul.o(a,b));
    }
    
    public TTupleN<T> scale(T target) {
        return op(x->(T)mul.o(target, x));
    }
}
