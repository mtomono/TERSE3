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
 * iterator which selects value
 * @author mtomono
 * @param <T>
 */
public class SelectIterator<T> extends AbstractBufferedIterator<T> {
    Predicate<? super T> intension;
    Iterator<? extends T> body;
    public SelectIterator(Iterator<? extends T> body, Predicate<? super T> intension) {
        this.body = body;
        this.intension = intension;
    }
    @Override
    protected void findNext() {
        while (body.hasNext()) {
            T candidate = body.next();
            if (intension.test(candidate)) {
                nextFound(candidate);
                return;
            }
        }
    }
}
