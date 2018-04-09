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

package viewlist;

import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author mtomono
 */
abstract public class CView<T> extends AbstractList<T> {
    List<T> body;
    
    abstract public int at(int index);
    
    public CView(List<T> body) {
        this.body = body;
    }
    
    @Override
    public T get(int index) {
        return body.get(at(index));
    }

    @Override
    public int size() {
        return body.size();
    }
    
    @Override
    public T set(int i, T e) {
        return body.set(at(i), e);
    }
    
    @Override
    public T remove(int i) {
        return body.remove(i);
    }
    
    @Override
    public void add(int i, T e) {
        body.add(at(i), e);
    }
}
