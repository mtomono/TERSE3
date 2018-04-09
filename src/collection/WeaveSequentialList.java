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

import iterator.WeaveListIterator;
import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author masao
 */
public class WeaveSequentialList<T> extends AbstractSequentialList<T> {
    List<List<T>> body;
    
    public static <T> boolean weavable(List<List<T>> body) {
        if (body.isEmpty())
            return true;
        Iterator<List<T>> iter = body.iterator();
        while (iter.hasNext()) {
            if (iter.next().size() != body.get(0).size())
                break;
        }
        while (iter.hasNext()) {
            if (iter.next().size() != body.get(0).size()-1)
                return false;
        }
        return true;
    }
    
    public WeaveSequentialList(List<List<T>> body) {
        assert weavable(body);
        this.body = body;
    }
    
    @Override
    public ListIterator<T> listIterator(int index) {
        return WeaveListIterator.create(body, index);
    }

    @Override
    public int size() {
        return body.stream().mapToInt(l->l.size()).sum();
    }
    
}
