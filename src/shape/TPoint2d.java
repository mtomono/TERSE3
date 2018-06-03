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
import java.util.function.Consumer;
import javax.vecmath.*;

/**
 *
 * @author masao
 */
public class TPoint2d extends Point2d {
    static public TPoint2d zero = new TPoint2d(0, 0);

    public TPoint2d() {
        super();
    }
    
    public TPoint2d(Tuple2d t) {
        super(t);
    }
    
    public TPoint2d(Tuple2i t) {
        super(t.x,t.y);
    }
    
    public TPoint2d(double x, double y) {
        super(x, y);
    }
    
    public TPoint2d retval(Consumer<TPoint2d> consumer) {
        TPoint2d retval = new TPoint2d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TPoint2d self(Consumer<TPoint2d> consumer) {
        consumer.accept(this);
        return this;
    }

    public TPoint2d subR(Tuple2d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TPoint2d subS(Tuple2d v1) {
        return self(v->v.sub(v1));
    }
    
    public TPoint2d addR(Tuple2d v1) {
        return retval(v->v.add(v1));
    }
    
    public TPoint2d addS(Tuple2d v1) {
        return self(v->v.add(v1));
    }

    public TPoint2d addR(double x, double y) {
        return retval(v->v.add(x, y));
    }
    
    public TPoint2d addS(double x, double y) {
        return self(v->v.add(x, y));
    }
    
    public void add(double x, double y) {
        setX(this.x+x);
        setY(this.y+y);
    }
    
    public TPoint2d subR(double x, double y) {
        return retval(v->v.sub(x, y));
    }
    
    public TPoint2d subS(double x, double y) {
        return self(v->v.sub(x, y));
    }
    
    public void sub(double x, double y) {
        setX(this.x-x);
        setY(this.y-y);
    }

    public TPoint2d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TPoint2d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TPoint2d moveR(Vector2d move) {
        return retval(p->p.add(move));
    }
    
    public TPoint2d moveS(Vector2d move) {
        return self(p->p.add(move));
    }
    
    public TPoint2d interpolateR(TPoint2d to, double rate) {
        return retval(p->p.interpolate(to, rate));
    }
    
    public TPoint2d interpolateS(TPoint2d to, double rate) {
        return self(p->p.interpolate(to, rate));
    }
    
    public TVector2d to(Point2d to) {
        return TVector2d.c(this, to);
    }
    
    public TVector2d from(Point2d from) {
        return TVector2d.c(from, this);
    }
    
    public TPoint2d setXR(double v1) {
        return retval(v->v.setX(v1));
    }
    
    public TPoint2d setXS(double v1) {
        return self(v->v.setX(v1));
    }
    
    public TPoint2d setYR(double v1) {
        return retval(v->v.setY(v1));
    }
    
    public TPoint2d setYS(double v1) {
        return self(v->v.setY(v1));
    }
    public TPoint3d expand() {
        return expand(0);
    }
    
    public TPoint3d expand(double z) {
        return new TPoint3d(x, y, z);
    }
    
    public TPoint2d flip() {
        return new TPoint2d(y, x);
    }
    
    static public TPoint2d average(TList<? extends Tuple2d> ps) {
        return new TPoint2d(ps.averageD(p->p.x), ps.averageD(p->p.y));
    }
}
