/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapeCollection;

import collection.TList;
import java.util.List;

/**
 *
 * @author masao
 */
public abstract class GridAbstract<T> implements GridX<T> {
    

    public GridAbstract() {
    }

    @Override
    public T get(Integer... address) {
        return get(TList.sof(address));
    }

    /**
     * c(hained)set.set method for chained method.
     * @param v
     * @param address
     * @return
     */
    @Override
    public GridX<T> cset(T v, List<Integer> address) {
        set(v, address);
        return this;
    }

    @Override
    public GridX<T> cset(T v, Integer... address) {
        return cset(v, TList.sof(address));
    }

    @Override
    public T set(T v, Integer... address) {
        return set(v, TList.sof(address));
    }
    
}
