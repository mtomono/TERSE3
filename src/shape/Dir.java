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
import static java.lang.Math.signum;
import java.util.Comparator;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public enum Dir {
    n(r->r.nw(), r->r.ne(), (a,b)->(int)signum(a.x-b.x)),
    e(r->r.ne(), r->r.se(), (a,b)->(int)signum(b.y-a.y)),
    s(r->r.se(), r->r.sw(), (a,b)->(int)signum(b.x-a.x)),
    w(r->r.sw(), r->r.nw(), (a,b)->(int)signum(a.y-b.y));
    
    Dir(Function<Rect2d, TPoint2d> prev, Function<Rect2d, TPoint2d> next, Comparator<TPoint2d> fore) {
        this.prev = prev;
        this.next = next;
        this.fore = fore;
    }
    
    public Function<Rect2d, TPoint2d> prev;
    public Function<Rect2d, TPoint2d> next;
    public Comparator<TPoint2d> fore;
    
    public Segment2d edge(Rect2d r) {
        return new Segment2d(prev.apply(r), next.apply(r));
    }
    
    public double edgeLength(Rect2d r) {
        return prev.apply(r).distanceL1(next.apply(r));
    }
    
    static public TList<Dir> dirs() {
        return TList.sof(Dir.values());
    }
}
