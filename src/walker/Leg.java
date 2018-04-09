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

package walker;

import java.util.Iterator;

/**
 * Iterator with buffer
 * @author mtomono
 */
public class Leg<T> implements Iterator<T> {

    Iterator<T> iter;
    private T current;

    public Leg(Iterator<T> iter) {
        this.iter = iter;
    }
    
    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }
    
    @Override
    public T next() {
        return iter.next();
    }

    /**
     * Proceed iterator one step.
     * @return true if successfully proceed
     */
    boolean forward() {
        if (!iter.hasNext())
            return false;
        current = iter.next();
        return true;
    }

    /**
     * @return the current
     */
    public T get() {
        return current;
    }

}
