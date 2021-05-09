/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.Set;

/**
 *
 * @author masao
 */
public class SetUtils {
    public static <T> Set<T> add(Set<T> one, Set<T> two) {
        one.addAll(two);
        return one;
    }
    public static <T> Set<T> sub(Set<T> one, Set<T> two) {
        one.removeAll(two);
        return one;
    }
}
