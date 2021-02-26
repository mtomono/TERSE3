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

package orderedSet;

import static collection.c.a2i;
import function.Order;
import iterator.AbstractBufferedIterator;
import iterator.IteratorIterator;
import iterator.PreIterator;
import java.util.Collections;
import java.util.Iterator;

/**
 * Iterator for Negate operation
 * there were several choices concerning how to handle the edge part (i.e. to leave
 * a empty range if the original range set has a range that contains or is adjacent 
 * to the edge). decision was made based on the idea that this operation should be 
 * consistent even when it is imposed multiple times. this is negation. so if this
 * operation is imposed twice, the result should be the same as the original, that 
 * means if this operation is imposed thrice, the result set of ranges should be 
 * completely the same as the result gained when it is imposed only once. so to 
 * meet this criteria, this doesn't leave an empty range even if the original range 
 * set has a rage that contains of is adjacent to the edge of the target range.
 * @author masao
 * @param <T>
 */
public class NegateRangeIterator<T extends Comparable<? super T>> extends AbstractBufferedIterator<Range<T>> {
    PreIterator<Range<T>> base;
    Order<T> order;
    
    class demarcation<S extends Comparable<? super S>> extends Range<S> {
        public demarcation(S point, Order<S> order) {
            super(point, point, order);
        }
    };
            
    public NegateRangeIterator(Range<T> whole, Iterator<Range<T>> target) {
        this.order=whole.order;
        this.base = new PreIterator<>(new IteratorIterator<>(new WalkerRr<>(Collections.singletonList(whole).iterator(), target).intersect(), a2i(new demarcation<>(whole.end(), whole.order))), 2);
        this.base.load(new demarcation(whole.start(), order));
    }
    
    @Override
    protected void findNext() {
        while (base.hasNext()) {
            base.next();
            if (!((base.now() instanceof demarcation || base.pre(-1) instanceof demarcation) && base.pre(-1).end().equals(base.now().start()))) {
                nextFound(new Range<>(base.pre(-1).end(), base.now().start(), order));
                break;
            }
        }
    }

    
    
}
