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
 *
 * @author masao
 */
public class SkipList<T> extends AbstractList<T> {
    TList<T> body;
    int length;
    public SkipList(TList<T> body, int length) {
        this.body = body;
        this.length = length;
    }
    @Override
    public T get(int index) {
        return body.get(index*length);
    }

    @Override
    public int size() {
        return body.size()/length + (body.size()%length==0?0:1);
    }
}
