/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;

/**
 *
 * @author masao
 */
public class Factorization {
    public static TList<Long> exec(long target) {
        TList<Long> retval = TList.c();
        long x=target;
        for (long i=2;i*i<=x;i++) for(;x%i==0;x/=i) retval.add(i);
        if (x!=1) retval.add(x);
        return retval;
    }
}
