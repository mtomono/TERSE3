/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author masao
 * @param <V>
 * @param <K>
 */
public abstract class SV<V extends SV<V,K>, K extends Field<K>> implements VectorSpace<V,K>{
    abstract public K value();
    abstract V create(K value);
    @Override
    public V add(V other) {
        return create(value().add(other.value()));
    }
    @Override
    public V sub(V other) {
        return create(value().sub(other.value()));
    }
    @Override
    public V scale(K other) {
        return create(value().mul(other));
    }
    
    public K scale(V other) {
        return value().div(other.value());
    }
    
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof SV)) {
            return false;
        }
        SV<V,K> t = (SV<V,K>) e;
        return value().equals(t.value());
    }
    
    @Override
    public String toString() {
        return value().toString();
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }
}
