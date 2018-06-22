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

import static collection.c.a2l;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author masao
 * @param <T>
 */
public class WeaveIterator<T> extends AbstractBufferedIterator<T> {
    Iterable<Iterator<T>> body;
    Iterator<Iterator<T>> iter;
    
    static public <T> Iterator<T> delimiter(List<T> target, T delim) {
        return new WeaveIterator(target.iterator(), Collections.nCopies(target.size()-1, delim).iterator());
    }
    
    static public <T> Iterator<T> weave(Iterable<Iterator<T>> body) {
        return new WeaveIterator<>(body);
    }
    
    static public <T> Iterator<T> weave(Iterator<T>... body) {
        return new WeaveIterator<>(body);
    }
    
    public WeaveIterator(Iterable<Iterator<T>> body) {
        this.body = body;
        this.iter = body.iterator();
    }
    
    public WeaveIterator(Iterator<T>... body) {
        this(a2l(body));
    }
    
    @Override
    protected void findNext() {
        if (!iter.hasNext())
            iter = new SelectIterator<>(body.iterator(), i->i.hasNext());
        if (iter.hasNext())
            nextFound(iter.next().next());
    }
    
}
