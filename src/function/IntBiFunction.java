/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface IntBiFunction<T> {
    public T apply(int a,int b);
}
