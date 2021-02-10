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
public class CompareUtil {
    static public <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b)<0?a:b;
    }
    static public <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b)>0?a:b;
    }
}
