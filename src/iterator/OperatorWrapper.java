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

import function.Holder;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * Iterator made of Operator.
 * generates value by adopting operator repeatedly.
 * @author masao
 * @param <T>
 */
public class OperatorWrapper<T> implements Iterator<T> {
    Holder<T> h;
    UnaryOperator<T> op;
    
    public OperatorWrapper(T init, UnaryOperator<T> op) {
        this.h = new Holder<>(init);
        this.op = op;
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return h.push(op.apply(h.get()));
    }
    
}
