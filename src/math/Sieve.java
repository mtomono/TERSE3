/*
 Copyright 2021 Masao Tomono

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
