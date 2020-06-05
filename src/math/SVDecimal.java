/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author masao
 * @param <K>
 */
public class SVDecimal<K extends Decimal<K>> extends SV<SVDecimal<K>,K> implements Comparable<SVDecimal<K>> {
    final K body;
    public SVDecimal(K body) {
        this.body=body;
    }

    @Override
    public K value() {
        return body;
    }

    @Override
    public SVDecimal<K> create(K value) {
        return new SVDecimal<>(value);
    }
    
    public SVDecimal<K> abs() {
        return create(value().abs());
    }
    
    public SVDecimal<K> negate() {
        return create(value().negate());
    }
    
    @Override
    public int compareTo(SVDecimal<K> other) {
        return value().compareTo(other.value());
    }
}
