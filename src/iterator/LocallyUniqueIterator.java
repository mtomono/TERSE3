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

/**
 *
 * @author masao
 */
public class LocallyUniqueIterator<T> extends AbstractBufferedIterator<T> {
    Iterator<T> base;
    boolean first;
    
    public LocallyUniqueIterator(Iterator<T> base) {
        this.base = base;
        first = true;
    }
    
    @Override
    protected void findNext() {
        while (base.hasNext()) {
            T next = base.next();
            if (first||!buffer.equals(next)) {
                nextFound(next);
                first=false;
                return;
            }
        }
    }
    
}
