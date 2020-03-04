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

package iterator;

import static java.util.Collections.singleton;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * Iterator made of Operator.
 * generates value by adopting operator repeatedly.
 * Simple OperatorWrapper does not return the initial value, if you need the initial value
 * to be returned by this iterator first, then use the factory fromStart().
 * @author masao
 * @param <T>
 */
public class OperatorWrapper<T> implements Iterator<T> {
    T seed;
    UnaryOperator<T> op;
    
    static public <T> Iterator<T> fromStart(T init, UnaryOperator<T> op) {
        return new IteratorIterator<>(singleton(init).iterator(),new OperatorWrapper<>(init, op));
    }
    
    public OperatorWrapper(T init, UnaryOperator<T> op) {
        this.seed=init;
        this.op = op;
    }
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        T retval=op.apply(seed);
        seed=retval;
        return retval;
    }
    
}
