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

/**
 *
 * @author masao
 * @param <T>
 */
public class ReverseListIterator<T> extends ReverseIterator<T> {
    int size;
    
    public ReverseListIterator(ListIterator<T> body, int size) {
        super(body);
        this.size = size;
    }
    
    @Override
    public void remove() {
        body.remove();
        size--;
    }

    @Override
    public void set(T e) {
        body.set(e);
    }

    @Override
    public void add(T e) {
        body.add(e);
        body.add(e);
        body.previous();
        body.previous();
        body.remove();
        size++;
    }
    
    @Override
    public int nextIndex() {
        return size - body.previousIndex() - 1;
    }
    
    @Override
    public int previousIndex() {
        return size - body.nextIndex() - 1;
    }
}
