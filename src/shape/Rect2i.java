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

package shape;

import collection.TList;
import static java.lang.Math.abs;

/**
 *
 * @author masao
 */
public class Rect2i {
    final public TPoint2i one;
    final public TPoint2i two;
    
    public Rect2i(TPoint2i one, TPoint2i two) {
        this.one = one;
        this.two = two;
    }
    
    public Rect2i(TList<TPoint2i> ps) {
        this(ps.get(0), ps.get(1));
    }
    
    static boolean containSym(int a, int b, int v) {
        if (a <= b)
            return a<=v&&v<=b;
        return b<=v&&v<=a;
    }
    
    public boolean contains(TPoint2i p) {
        return containSym(one.x, two.x, p.x)&&containSym(one.y, two.y, p.y);
    }
    
    /**
     * one->(one.x,two.y)->two->(two.x,one.y)->one
     * @return 
     */
    public TList<TList<TPoint2i>> edges() {
        return TList.of(edge0(),edge1(),edge2(),edge3());
    }
    
    public TList<TPoint2i> edge0() {
        return TList.rangeSym(one.y, two.y).map(y->new TPoint2i(one.x,y));
    }
    
    public TList<TPoint2i> edge1() {
        return TList.rangeSym(one.x, two.x).map(x->new TPoint2i(x, two.y));
    }
    
    public TList<TPoint2i> edge2() {
        return TList.rangeSym(two.y, one.y).map(y->new TPoint2i(two.x, y));
    }
    
    public TList<TPoint2i> edge3() {
        return TList.rangeSym(two.x, one.x).map(x->new TPoint2i(x, one.y));
    }
        
    public TList<TPoint2i> oneToTwo() {
        if (edge1().isEmpty())
            return edge0();
        return edge0().subList(0,abs(one.y-two.y)).append(edge1());
    }
    
    public TList<TPoint2i> twoToOne() {
        if (edge3().isEmpty())
            return edge2();
        return edge2().subList(0,abs(one.y-two.y)).append(edge3());
    }
    
    public String toString() {
        return one.toString()+"<->"+two;
    }
}
