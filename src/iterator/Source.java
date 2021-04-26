/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Source<T> {
    public TIterator<T> in();
}
