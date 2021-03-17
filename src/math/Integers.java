/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.TList;
import function.Power;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

/**
 *
 * @author masao
 */
public class Integers {
    public static long gcd(long mo, long no) {
        long m = Math.abs(mo);
        long n = Math.abs(no);
        if (m < n) {
            return gcd(n, m);
        }
        long r = m % n;
        if (r == 0) {
            return n;
        }
        return gcd(n, r);
    }

    public static TList<Long> factorization(long target) {
        TList<Long> retval = TList.c();
        long x=target;
        for (long i=2;i*i<=x;i++) for(;x%i==0;x/=i) retval.add(i);
        if (x!=1) retval.add(x);
        return retval;
    }
    
    public static long pow(long target, int power) {
        return Power.pow(target, power, 1L, (a,b)->a*b);
    }
    
    public static long pow(long target, long power) {
        return Power.pow(target, power, 1L, (a,b)->a*b);
    }
}
