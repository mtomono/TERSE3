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

import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class UntilIterator<T> extends AbstractBufferedIterator<T> {
    Predicate<? super T> terminator;
    Iterator<T> body;
    boolean finished;
    
    public UntilIterator(Iterator<T> body, Predicate<? super T> terminator) {
        this.body = body;
        this.terminator = terminator;
        this.finished = false;
    }
    
    @Override
    protected void findNext() {
        if (finished||!body.hasNext())
            return;
        T current = body.next();
        if (terminator.test(current))
            finished = true;
        nextFound(current);
    }
}
