/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import orderedSet.Range;

/**
 *
 * @author masao
 */
public interface Decimal<K> extends Comparable<K> {
    K zero();
    K one();
    K negate();
    K abs();
    K add(K v);
    K sub(K v);
    K mul(K v);
    K mul(int v);
    K mul(long v);
    K div(K v);
    K div(int v);
    K div(long v);
    
    public static <K extends Decimal<K>> K width(Range<K> range) {
        return range.end.sub(range.start);
    }
        
}
