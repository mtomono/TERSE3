/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math;

import collection.TList;
import function.Power;

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
    public static int gcd(int mo, int no) {
        int m = Math.abs(mo);
        int n = Math.abs(no);
        if (m < n) {
            return gcd(n, m);
        }
        int r = m % n;
        if (r == 0) {
            return n;
        }
        return gcd(n, r);
    }

    public static TList<Integer> factorization(int target) {
        TList<Integer> retval = TList.c();
        int x=target;
        for (int i=2;i*i<=x;i++) for(;x%i==0;x/=i) retval.add(i);
        if (x!=1) retval.add(x);
        return retval;
    }
    
    public static TList<Long> factorization(long target) {
        TList<Long> retval = TList.c();
        long x=target;
        for (long i=2;i*i<=x;i++) for(;x%i==0;x/=i) retval.add(i);
        if (x!=1) retval.add(x);
        return retval;
    }
    
    public static int pow(int target, int power) {
        return Power.pow(target, power, 1, (a,b)->a*b);
    }
    
    public static long pow(long target, long power) {
        return Power.pow(target, power, 1L, (a,b)->a*b);
    }
}
