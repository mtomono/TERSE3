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

package listview;

import collection.TList;
import orderedSet.Range;

/**
 * ListView.
 * ListView is a List-like structure, but has its start and end just like range.
 * Unlike list, index of the start of view is arbitrary. 
 * It has several ways to interprete as a List.
 * @author masao
 */
public class ListView<T> {
    TList<T> body;
    int start;
    
    public ListView(TList<T> body, int start) {
        this.body = body;
        this.start = start;
    }
    
    public Range<Integer> range() {
        return new Range<>(start, start+body.size());
    }
    
    public T get(int index) {
        return body.get(interprete(index));
    }
    
    public ListView<T> set(int at, T v) {
        body.set(interprete(at), v);
        return this;
    }
    
    public ListView<T> remove(int at) {
        body.remove(interprete(at));
        return this;
    }
    
    public int interprete(int index) {
        return index-start;
    }
    
    public TList<T> asList(int from, int to) {
        return body.subList(interprete(from), interprete(to));
    }
    
    public TList<T> asList(Range<Integer> range) {
        return asList(range.start(), range.end());
    }
    
    public ListView<T> subView(int from, int to) {
        return new ListView<>(asList(from,to),from);
    }
    
    public ListView<T> subView(Range<Integer> range) {
        return subView(range.start(), range.end());
    }
    
    public ListView<T> shift(int shift) {
        return new ListView<>(body, start+shift);
    }
}
