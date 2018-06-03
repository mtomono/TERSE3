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
import java.util.function.Consumer;
import javax.vecmath.Point2i;
import javax.vecmath.Tuple2i;

/**
 *
 * @author masao
 */
public class TPoint2i extends Point2i {
    static public TPoint2i zero = new TPoint2i(0, 0);
    static public TPoint2i x1 = new TPoint2i(1, 0);
    static public TPoint2i y1 = new TPoint2i(0, 1);

    public TPoint2i() {
        super();
    }
    
    public TPoint2i(Tuple2i t) {
        super(t);
    }
    
    public TPoint2i(int x, int y) {
        super(x, y);
    }
    
    public static TPoint2i c(Tuple2i start, Tuple2i end) {
        return new TPoint2i(end).self(p->p.sub(start));
    }
    
    public TPoint2i retval(Consumer<TPoint2i> consumer) {
        TPoint2i retval = new TPoint2i(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint2i self(Consumer<TPoint2i> consumer) {
        consumer.accept(this);
        return this;
    }

    public TPoint2i to(TPoint2i to) {
        return TPoint2i.c(this, to);
    }
    
    public TPoint2i from(TPoint2i from) {
        return TPoint2i.c(from, this);
    }
    
    public TPoint2i addR(Tuple2i v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint2i addS(Tuple2i v1) {
        return self(v->v.add(v1));
    }
    
    public TPoint2i subR(Tuple2i v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint2i subS(Tuple2i v1) {
        return self(v->v.sub(v1));
    }
    
    public TPoint2i scaleR(int v1) {
        return retval(v->v.scale(v1));
    }
    
    public TPoint2i scaleS(int v1) {
        return self(v->v.scale(v1));
    }
    
    public TVector2d scaleR(double scale) {
        return new TVector2d(x,y).scaleS(scale);
    }
    
    public int det(Tuple2i other) {
        return x*other.y-other.x*y;
    }
    
    public TPoint2i signum() {
        return new TPoint2i(Integer.signum(x), Integer.signum(y));
    }
    
    public TPoint2i flip() {
        return new TPoint2i(y, x);
    }
    
    public int manhattanLength() {
        return abs(x)+abs(y);
    }
    
    /**
     * judge the quadrant.
     * only applicable to unit point.
     * 0:x+ 1:y+ 2:x- 3:y-
     * @return 
     */
    public int quadrant() {
        assert abs(x+y)==1;
        assert x*y==0;
        return 3-abs(x*2+y+1);
    }
    
    final static public TList<TPoint2i> quadrants = TList.ofStatic(
            new TPoint2i(1, 0),
            new TPoint2i(0, 1),
            new TPoint2i(-1,0),
            new TPoint2i(0,-1)
    );
    static TPoint2i quadrant(int i) {
        return quadrants.get(i);
    }
}
