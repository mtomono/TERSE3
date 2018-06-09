/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderedSet;

import java.util.Comparator;

/**
 *
 * @author masao
 */
public class Comparators {
    static public <T> Comparator<T> c(Comparator<T> c) {
        return c;
    }
}
