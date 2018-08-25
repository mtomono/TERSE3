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

import java.util.function.BinaryOperator;
import orderedSet.Range;

/**
 *
 * @author masao
 */
public class SegmentTree<T>  {
    TList<T> original;
    TList<T> body;
    TList<T> whole;
    BinaryOperator<T> bo;
    T e;
    
    private static int minBranchSize(int size) {
        int n=1;while(n<size) n*=2;
        return n;
    }
    
    public SegmentTree(TList<T> body, T e, BinaryOperator<T> bo) {
        this.bo = bo;
        this.e = e;
        this.original = body;
        int size = minBranchSize(body.size());
        this.body = body.append(TList.nCopies(size-body.size(), e).fix());
        this.whole = TList.nCopies(size-1, e).fix().append(this.body);
        for (int i=size-2;i>0; i--) update(whole, bo, i);
    }
    
    private static <T> void update(TList<T> whole, BinaryOperator<T> bo, int at) {
        whole.set(at, bo.apply(whole.get(2*at+1), whole.get(2*at+2)));
    }
    
    public void set(int at, T val) {
        at += (body.size()-1);
        whole.set(at, val);
        while (at > 0) {
            at = (at-1)/2;
            update(whole, bo, at);
        }
    }
    
    public T get(Range<Integer> r) {
        return get(r, new Range<>(0,body.size()), 0);
    }
    
    private static <T> Integer center(Range<Integer> s) {
        return (s.start()+s.end())/2;
    }
    
    private T get(Range<Integer> r, Range<Integer> s, int k) {
        if (!r.overlaps(s)) return e;
        if (r.contains(s)) return whole.get(k);
        T vl = get(r, new Range<>(s.start(),center(s)), 2*k+1);
        T vr = get(r, new Range<>(center(s),s.end()), 2*k+2);
        return bo.apply(vl, vr);
    }
}
