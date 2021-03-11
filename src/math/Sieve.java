/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import collection.PrimitiveArrayWrap;
import collection.TList;

/**
 *
 * @author masao
 */
public class Sieve {
    int max;
    int upto;
    boolean[] flags;

    public Sieve(int max) {
        this.max = max;
        flags = new boolean[max];
        for (int i=0; i<max; i++) flags[i] = true;
        flags[0] = false;
        flags[1] = false;
        upto = (int) Math.ceil(Math.sqrt(max));
    }

    public Sieve exec() {
        for(int i=2; i<upto; i++) if(flags[i])  for(int j=i*i; j<max; j+=i) flags[j]=false;
        return this;
    }

    public TList<Integer> number() {
        return TList.set(PrimitiveArrayWrap.wrap(flags)).filterAt((x) -> x);
    }
}
