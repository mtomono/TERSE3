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

import collection.P;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author masao
 * @param <S>
 * @param <T>
 */
public interface Walker<S, T> extends Iterator<P<S, T>>{
    void fore();
    S left();
    T right();
    
    @Override
    public default P<S, T> next() {
        if (!hasNext())
            throw new NoSuchElementException("Walker:traversed too far");
        fore();
        return new P<>(left(), right());
    }
}
