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

import java.util.ListIterator;
import java.util.function.Predicate;

/**
 * an iterator which stops when the terminator returns true.
 * @author mtomono
 */
public class YABeforeIterator<T> extends AbstractBufferedIterator<T> {
    Predicate<? super T> terminator;
    ListIterator<T> body;
    
    public YABeforeIterator(ListIterator<T> body, Predicate<? super T> terminator) {
        this.body = body;
        this.terminator = terminator;
    }
    
    @Override
    protected void findNext() {
        if (!body.hasNext())
            return;
        T current = body.next();
        if (!terminator.test(current)) {
            nextFound(current);
        } else {
            body.previous();
        }
    }
}
