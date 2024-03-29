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

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author masao
 * @param <T>
 */
public class MaskIterator<T> extends AbstractBufferedIterator<T> {
    BufferedIterator<T> body;
    BufferedIterator<T> mask;
    Comparator<T> c;
        
    /**
     * 
     * @param body required to be sorted in order
     * @param mask required to be sorted in order
     * @param c 
     */
    public MaskIterator(Iterator<T> body, Iterator<T> mask, Comparator<T> c) {
        this.body = new BufferedIterator(body);
        this.mask = new BufferedIterator(mask);
        this.c = c;
    }
        
    @Override
    public void findNext() {
        while (true) {
            if (!body.hasNext()) return;
            if (!mask.hasNext()) {nextFound(body.next());return;}
            if (c.compare(body.peek(), mask.peek())<0) {nextFound(body.next());return;}
            if (c.compare(body.peek(), mask.peek())>0) mask.next();
            else if (c.compare(body.peek(), mask.peek())==0) body.next();
        }
    }    
}
