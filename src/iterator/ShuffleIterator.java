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
package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author masao
 */
public class ShuffleIterator<T> implements Iterator<T>{
    Random r;
    List<T> rest;
    
    public ShuffleIterator(List<T> rest) {
        this.rest=new ArrayList<>(rest);
        r=new Random();
    }

    @Override
    public boolean hasNext() {
        return !rest.isEmpty();
    }

    @Override
    public T next() {
        return rest.remove(r.nextInt(rest.size()));
    }
}
