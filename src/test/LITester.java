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

package test;

import static collection.c.a2l;
import static collection.c.i2l;
import java.util.ListIterator;
import static test.TestUtils.ppr;
import static test.TestUtils.pr;

/**
 *
 * @author masao
 */
public class LITester<T> implements ListIterator<T> {
    ListIterator<T> body;
    ListIterator<T> exemplar;
    
    public LITester(ListIterator<T> body, ListIterator<T> exemplar) {
        this.body = body;
        this.exemplar = exemplar;
    }
    
    public boolean assertTrue(boolean test, String message) {
        if (!test)
            throw new RuntimeException(message + "¥n" + this.toString());
        return test;
    }
    
    public void conform() {
        assertTrue(body.hasNext() == exemplar.hasNext(), "hasNext");
        assertTrue(body.hasPrevious() == exemplar.hasPrevious(), "hasPrevious");
        assertTrue(body.nextIndex() == exemplar.nextIndex(), "nextIndex");
        assertTrue(body.previousIndex() == exemplar.previousIndex(), "previousIndex");
    }
    
    public void rewind() {
        while (body.hasPrevious())
            body.previous();
        while (exemplar.hasPrevious())
            exemplar.previous();
    }
    
    /**
     * report all the items in iterators
     * @return 
     */
    public String report() {
        return i2l(body).toString() + "¥n" + i2l(exemplar).toString();
    }
    
    /**
     * report next item in iterators
     * @return 
     */
    public String reportNow() {
        return a2l(body.next(), exemplar.next()).toString();
    }
    
    @Override
    public String toString() {
        return a2l(body.hasNext(), body.nextIndex(), body.hasPrevious(), body.previousIndex()).toString() + "¥m"
                +a2l(exemplar.hasNext(), exemplar.nextIndex(), exemplar.hasPrevious(), exemplar.previousIndex());
    }
    
    public String nextBoth() {
        return a2l(body.next(), exemplar.next()).toString();
    }
    
    public String previousBoth() {
        return a2l(body.previous(), exemplar.previous()).toString();
    }

    @Override
    public boolean hasNext() {
        boolean retval = body.hasNext();
        exemplar.hasNext();
        conform();ppr("hasNext matched");
        return retval;
    }

    @Override
    public T next() {
        T retval = body.next();
        assertTrue(exemplar.next().equals(retval), "next");
        conform();ppr("next matched");
        return retval;
    }

    @Override
    public boolean hasPrevious() {
        boolean retval = body.hasPrevious();
        exemplar.hasPrevious();
        conform();ppr("hasPrevious matched");
        return retval;
    }

    @Override
    public T previous() {
        T retval = body.previous();
        assertTrue(exemplar.previous().equals(retval), "previous");
        conform();ppr("previous matched");
        return retval;
    }

    @Override
    public int nextIndex() {
        int retval = body.nextIndex();
        exemplar.nextIndex();
        conform();ppr("nextIndex matched");
        return retval;
    }

    @Override
    public int previousIndex() {
        int retval = body.previousIndex();
        exemplar.previousIndex();
        conform();ppr("prevIndex matched");
        return retval;
    }

    @Override
    public void remove() {
        try {
            exemplar.remove();
        } catch (IllegalStateException e) {
            try {
                body.remove();
                throw new RuntimeException("remove exemplar.IllegalStateException");
            } catch (IllegalStateException ex) {
                ppr("remove IllegalStateException matched");
                return;
            }
        }
        body.remove();
        conform();ppr("remove matched");
    }

    @Override
    public void set(T e) {
        try {
            exemplar.set(e);
        } catch (IllegalStateException exx) {
            try {
                body.set(e);
                throw new RuntimeException("set exemplar.IllegalStateException");
            } catch (IllegalStateException ex) {
                ppr("set IllegalStateException matched");
                return;
            }
        }
        body.set(e);
        conform();ppr("set matched");
    }

    @Override
    public void add(T e) {
        exemplar.add(e);
        body.add(e);
        conform();ppr("add matched");
    }
    
    public static void test(LITester<Integer> iter) {
        try {
            iter.next();
            iter.next();
            iter.previous();
            iter.next();
            iter.remove();
            iter.add(8);
            iter.add(9);
            iter.remove();
            iter.remove();
            iter.set(10);
            iter.add(10);
            iter.set(11);
            iter.next();
            iter.set(12);
            iter.remove();
            iter.set(13);
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.previous();
            iter.previous();
            iter.add(20);
            iter.remove();
        } catch (Throwable t) {
            pr(iter);
            pr(iter.reportNow());
            iter.rewind();
            pr(iter.report());
            throw t;
        }
    }
    
    public static void test2(LITester<Integer> iter) {
        try {
            iter.next();
            iter.next();
            iter.previous();
            iter.next();
            iter.set(10);
            iter.set(11);
            iter.next();
            iter.set(12);
            iter.set(13);
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.next();
            iter.previous();
            iter.previous();
            iter.previous();
        } catch (Throwable t) {
            pr(iter);
            pr(iter.reportNow());
            iter.rewind();
            pr(iter.report());
            throw t;
        }
        
    }

}
