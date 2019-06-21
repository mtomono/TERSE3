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
    
    public TTupleNi bop(List<Integer> b, BinaryOperator<Integer> f) {
        return new TTupleNi(pair(b, (x,y)->f.apply(x,y))); 
    }
    
    public TTupleNi op(UnaryOperator<Integer> f) {
        return new TTupleNi(map(f)); 
    }
    
    public TTupleNi sub(List<Integer> b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNi add(List<Integer> b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNi scale(int s) {
        return op(x->x*s);
    }
}
