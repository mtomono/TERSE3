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

import collection.RingBuffer;
import java.util.function.Predicate;

/**
 *
 * @author mtomono
 */
public class ViewHolder<T> {
    View<T> view;
    RingBuffer<T> buffer;
    
    public static <T> ViewHolder<T> fore(int length) {
        return fore(new RingBuffer<>(length));
    }
    
    public static <T> ViewHolder<T> fore(RingBuffer buffer) {
        return new ViewHolder<>(View.fore(buffer, buffer.size()), buffer);
    }
    
    public static <T> ViewHolder<T> pre(int length) {
        return pre(new RingBuffer<>(length));
    }

    public static <T> ViewHolder<T> pre(RingBuffer buffer) {
        return new ViewHolder<>(View.pre(buffer, buffer.size()), buffer);
    }

    public ViewHolder(View<T> view, RingBuffer<T> buffer) {
        this.view = view;
        this.buffer = buffer;
    }
    
    public boolean t(int index, Predicate<T> t) {
        return view.t(index, t);
    }
    
    public T g(int index) {
        return view.g(index);
    }
    
    public View<T> peek() {
        return view;
    }
    
    public T push(T v) {
        return buffer.push(v);
    }
    
    public T pop(T v) {
        return buffer.pop(v);
    }
}
