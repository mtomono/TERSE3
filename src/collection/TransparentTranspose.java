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

package collection;

import java.util.AbstractList;

/**
 * Transpose which is transparent in the way it can accept changes to it.
 * TList#transpose() depends on the mechanism of map. so the result of it cannot be changed.
 * it is quite inconvenient when you want to change a list of list in transposed way.
 * this class is the one to deal with that situation.
 * @author masao
 */
public class TransparentTranspose<T> extends AbstractList<TList<T>> {
    TList<TList<T>> body;
    public static <T> TList<TList<T>> transpose(TList<TList<T>> target) {
        return TList.set(new TransparentTranspose<>(target));
    }
    TransparentTranspose(TList<TList<T>> body) {
        this.body=body;
    }
    @Override
    public TList<T> get(int index) {
        return TList.set(new Column(index));
    }
    @Override
    public int size() {
        return body.isEmpty()?0:body.get(0).size();
    }
    class Column extends AbstractList<T> {
        int i;
        public Column(int i) {
            this.i=i;
        }
        @Override
        public T get(int index) {
            return body.get(index).get(i);
        }
        public T set(int index, T o) {
            return body.get(index).set(i, o);
        }
        @Override
        public int size() {
            return body.size();
        }
    }
}
