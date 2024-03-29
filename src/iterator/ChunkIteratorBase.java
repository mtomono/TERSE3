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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author masao
 */
abstract public class ChunkIteratorBase<T> extends AbstractBufferedIterator<List<T>>{
    Iterator<T> body;
    Predicate<? super T> pred;
    List<T> retval;
    boolean finished;

    public ChunkIteratorBase(Iterator<T> body, Predicate<? super T> pred) {
        this.body = body;
        this.pred = pred;
        this.retval = new ArrayList<>();
        this.finished = !body.hasNext();
    }

    abstract protected void whenTestsTrue(T e);
    
    @Override
    protected void findNext() {
        if (finished)
            return;
        while (body.hasNext()) {
            T e = body.next();
            if (pred.test(e)) {
                whenTestsTrue(e);
                return;
            }
            retval.add(e);
        }
        nextFound(retval);
        finished=true;
    }
}
