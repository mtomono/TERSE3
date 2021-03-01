/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.text.ParseException;

/**
 *
 * @author masao
 */
public interface Format<K> {
    public K f(String v) throws ParseException;
    public String toString(K v);
}
