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

package orderedSet;

import iterator.LocallyUniqueIterator;
import java.util.Iterator;
import walker.AbstractWalker;
import walker.SelectWalker;
import walker.Walker;
import walker.WalkerIterator;

/**
 *
 * @author masao
 */
public abstract class WalkerSetOp<S, T, U> extends AbstractWalker<S, T> {
    public abstract boolean overlaps(S left, T right);
    public abstract U intersect(S left, T right);
    
    public WalkerSetOp(Iterator<S> left, Iterator<T> right) {
        super(left, right);
    }
    
    public Walker<S, T> overlap() {
        return new SelectWalker<>(this, this::overlaps);
    }
    
    public Iterator<U> intersect() {
        return new WalkerIterator<>(overlap(), this::intersect);
    }
    
    public Iterator<S> touch() {
        return new LocallyUniqueIterator<>(new WalkerIterator<>(overlap(), (s,t)->s));
    }
}
