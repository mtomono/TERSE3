/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package test;

import java.util.HashMap;
import java.util.Objects;

/**
 * Identifiable. 
 * instances are all managed and identified by its number.
 * the same is true for Integer objects as long as the instances are in the 
 * range between -127 to 128 because they are all cached. but depending on that 
 * fact could lead programmer to use instance outside that range as an 
 * identifiable object though it is not guaranteed unknowingly.
 * if you want to move on to this object from Integer, use of macro could be 
 * effective. 
 * @author masao
 */
public class I {
    Integer body;
    static HashMap<Integer, I> cache = new HashMap<>();
    
    I(Integer body) {
        this.body = body;
        cache.put(body, this);
    }
    
    static public I i(Integer i) {
        return cache.containsKey(i) ? cache.get(i) : new I(i);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof I))
            return false;
        I t = (I)e;
        return t.body.equals(this.body);
    }
    
    public String toString() {
        return "I" + body.toString();
    }
}
