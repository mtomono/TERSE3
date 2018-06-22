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

package collection;

import java.util.AbstractList;
import java.util.List;
import java.util.Optional;
import java.util.RandomAccess;

/**
 *
 * @author masao
 * @param <T>
 */
public class Filler<T> extends AbstractList<Optional<T>> implements RandomAccess {
    int size;
    Optional<T> retval;
    
    static public <T> List<Optional<T>> filler(int length) {
        return new Filler<>(length);
    }
    
    static public <T> List<List<Optional<T>>> filler(int length, int height) {
        final List<Optional<T>> row = new Filler(length);
        return new AbstractList<List<Optional<T>>>() {
            @Override
            public List<Optional<T>> get(int index) {
                return row;
            }

            @Override
            public int size() {
                return height;
            }
        };
    }
    
    public Filler(int size) {
        this.size = size;
        this.retval = Optional.empty();
    }
    
    @Override
    public Optional<T> get(int index) {
        return retval;
    }

    @Override
    public int size() {
        return size;
    }
    
}
