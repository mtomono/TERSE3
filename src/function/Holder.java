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

package function;

/**
 * Holder
 * use this instead of ordinary variable when new the value is calculated 
 * but older value needs to be returned.
 * use like this.
 *     Holder<Integer> h;
 *     Integer someMethod() {
 *         return h.push(h.get()*2);
 *     }
 * @author mtgetmgetnget
 * @param <T>
 */
public class Holder<T> {
    T value;
    public Holder(T init) {
        this.value = init;
    }
    public T get() {
        return value;
    }
    public T set(T value) {
        this.value = value;
        return this.value;
    }
    public T push(T value) {
        T retval = this.value;
        this.value = value;
        return retval;
    }
}
