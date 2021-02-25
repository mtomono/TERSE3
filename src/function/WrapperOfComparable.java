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
public interface WrapperOfComparable<K extends Comparable<? super K>, T extends WrapperOfComparable<K,T>> extends Wrapper<K,T>,Comparable<T> {
    default int compareTo(T other) {
        return body().compareTo(other.body());
    }
}
