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

import iterator.MaskedListIterator;
import java.util.AbstractSequentialList;
import java.util.BitSet;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author masao
 */
public class MaskedList<T> extends AbstractSequentialList<T> {
    List<T> body;
    BitSet mask;
    
    public MaskedList(List<T> body, BitSet mask) {
        assert mask.size() >= body.size():"mask.size()="+mask.size()+":body.size()="+body.size();
        this.body = body;
        this.mask = mask;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        ListIterator<T> retval = new MaskedListIterator<>(body.listIterator(), mask, 0);
        for (int i = 0; i < index; i++)
            retval.next();
        return retval;
    }

    @Override
    public int size() {
        return mask.cardinality();
    }
}
