/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author masao
 * @param <B>
 * @param <K>
 */
public interface DecimalBuilder<K extends Decimal<K>> {
    K zero();
    K one();
    K k(int i);
    K k(long l);
}
