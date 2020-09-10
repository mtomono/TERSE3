/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import static collection.PrimitiveArrayWrap.wrap;
import debug.Te;
import java.util.function.IntUnaryOperator;

/**
 *
 * @author masao
 */
public class ArrayInt {
    int[] body;
    int from;
    int to;
    int length;
    static public ArrayInt arrayInt(int... body) {
        return new ArrayInt(body);
    }
    public ArrayInt(int from, int to, int[] body) {
        this.from=from;
        this.to=to;
        this.body=body;
        this.length=to-from;
    }
    public ArrayInt(int[] body) {
        this(0,body.length,body);
    }
    public int get(int i) {
        return body[i];
    }
    public int set(int i, int v) {
        int retval=get(i);
        body[i]=v;
        return retval;
    }
    public int length() {
        return length;
    }
    public TList<Integer> asList() {
        return TList.set(wrap(body));
    }
    public ArrayInt subArray(int from, int to) {
        return new ArrayInt(this.from+from,this.from+to,body);
    }
    public IntUnaryOperator asMap() {
        return i->get(i);
    }
    public ArrayInt swap(int i,int j) {
        set(j,set(i,get(j)));
        return this;
    }
    public int min(int from, int to, IntUnaryOperator op) {
        int min=op.applyAsInt(get(from));
        int retval=0;
        for (int i=from+1;i<to;i++)
            if (op.applyAsInt(get(i))<min) {
                min=op.applyAsInt(get(i));
                retval=i;
            }
        return retval;
    }
    public int min(int seek, IntUnaryOperator op) {
        return seek>0?min(seek,length(),op):min(0,length()+seek,op);
    }
    public int min(IntUnaryOperator op) {
        return min(0,length(),op);
    }
}
