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

import static collection.c.a2i;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author masao
 */
public class IteratorIterator<T> extends AbstractBufferedIterator<T> {
    Iterator<Iterator<T>> body;
    Iterator<T> current;
    
    public IteratorIterator(Iterator<Iterator<T>> body) {
        this.body = body;
        this.current = Collections.emptyIterator();
    }
    
    public IteratorIterator(Iterator<T>... iters) {
        this(a2i(iters));
    }
    
    @Override
    protected void findNext() {
        while (!current.hasNext())
            if (body.hasNext())
                current = body.next();
            else
                return;
        nextFound(current.next());
    }
}
