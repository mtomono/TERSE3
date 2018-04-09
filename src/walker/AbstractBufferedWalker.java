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

import java.util.NoSuchElementException;

/**
 * Base implementation for SelectWalker
 * @author masao
 * @param <S>
 * @param <T>
 */
public abstract class AbstractBufferedWalker<S, T> implements Walker<S, T> {
    boolean has = false;
    S left;
    T right;
    
    abstract protected void findNext();
    
    public void nextFound(S left, T right) {
        this.left = left;
        this.right = right;
        this.has = true;
    }
    
    public void prepare() {
        if (!has)
            findNext();
    }
    
    @Override
    public boolean hasNext() {
        prepare();
        return has;
    }

    @Override
    public void fore() {
        prepare();
        if (!has) 
            throw new NoSuchElementException("AbstractWalkerWrapper.next() : gone beyond boundary (actual class is " + this.getClass().getName() + ")");
        has = false;
    }

    @Override
    public S left() {
        return left;
    }

    @Override
    public T right() {
        return right;
    }    
}
