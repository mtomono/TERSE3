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
 * Base imprementation for Walker
 * @author mtomono
 * @param <S>
 * @param <T>
 */
public abstract class AbstractWalker<S, T> implements Walker<S, T> {

    Leg<S> legL;
    Leg<T> legR;
    boolean hasNext;
    abstract public boolean leftIsBehind(S left, T right);

    public AbstractWalker(Iterator<S> iterL, Iterator<T> iterR) {
        legL = new Leg<>(iterL);
        legR = new Leg<>(iterR);
        hasNext = legL.forward() && legR.forward();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public void fore() {
        left = legL.get();
        right = legR.get();
        hasNext = step();
    }
    private S left;
    private T right;
    
    boolean step() {
        if (leftIsBehind(left, right))
            return legL.forward();
        else
            return legR.forward();
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
