/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import function.Wrapper;

/**
 *
 * @author masao
 */
public interface TListWrapper<V,W extends TListWrapper<V,W>> extends Wrapper<TList<V>,W> {
    default V get(int i) {
        return body().get(i);
    }
}
