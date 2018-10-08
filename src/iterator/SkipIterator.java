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
import java.util.Optional;
import java.util.function.BiPredicate;

/**
 *
 * @author masao
 */
public class SkipIterator<T> extends AbstractBufferedIterator<T> {
    Iterator<T> body;
    BiPredicate<T,T> pred;
    Optional<T> pre;
    public SkipIterator(Iterator<T> body, BiPredicate<T,T> pred) {
        this.body = body;
        this.pred = pred;
        this.pre = Optional.empty();
    }
    @Override
    protected void findNext() {
        if (!body.hasNext())
            return;
        if (!pre.isPresent()) {
            pre = Optional.of(body.next());
            nextFound(pre.get());
            return;
        }
        while (body.hasNext()) {
            T candidate = body.next();
            if (pred.test(pre.get(), candidate)) {
                pre = Optional.of(candidate);
                nextFound(candidate);
                return;
            }
        }
    }    
}
