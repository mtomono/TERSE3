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
public interface IntBiPredicate {
    public boolean test(int a, int b);
    static public IntBiPredicate lt=(a,b)->a<b;
    static public IntBiPredicate gt=(a,b)->a>b;
    static public IntBiPredicate le=(a,b)->a<=b;
    static public IntBiPredicate ge=(a,b)->a>=b;
    static public IntBiPredicate eq=(a,b)->a==b;
    static public IntBiPredicate ne=(a,b)->a!=b;
}
