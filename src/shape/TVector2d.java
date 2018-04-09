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

import java.util.function.Consumer;
import javax.vecmath.*;

/**
 *
 * @author masao
 */
public class TVector2d extends Vector2d {
    public TVector2d() {
        super();
    }
    
    public TVector2d(Tuple2d t) {
        super(t);
    }
    
    public TVector2d(double x, double y) {
        super(x, y);
    }
    
    public static TVector2d c(Tuple2d start, Tuple2d end) {
        return new TVector2d(end).self(p->p.sub(start));
    }
    
    public TVector2d retval(Consumer<TVector2d> consumer) {
        TVector2d retval = new TVector2d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TVector2d self(Consumer<TVector2d> consumer) {
        consumer.accept(this);
        return this;
    }

    public TVector2d subR(Tuple2d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TVector2d subS(Tuple2d v1) {
        return self(v->v.sub(v1));
    }
    
    public TVector2d addR(Tuple2d v1) {
        return retval(v->v.add(v1));
    }
    
    public TVector2d addS(Tuple2d v1) {
        return self(v->v.add(v1));
    }
    
    public TVector2d addR(double x, double y) {
        return retval(v->v.add(x, y));
    }
    
    public TVector2d addS(double x, double y) {
        return self(v->v.add(x, y));
    }
    
    public void add(double x, double y) {
        setX(this.x+x);
        setY(this.y+y);
    }
    
    public TVector2d subR(double x, double y) {
        return retval(v->v.sub(x, y));
    }
    
    public TVector2d subS(double x, double y) {
        return self(v->v.sub(x, y));
    }
    
    public void sub(double x, double y) {
        setX(this.x-x);
        setY(this.y-y);
    }
    
    public TVector2d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TVector2d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TVector2d sizeR(double l) {
        return retval(v->v.scale(l/v.length()));
    }
    
    public TVector2d sizeS(double l) {
        return self(v->v.scale(l/v.length()));
    }
    
    public TVector2d negateR() {
        return retval(v->v.negate());
    }
    
    public TVector2d negateS() {
        return self(v->v.negate());
    }

    public Angle2d angleO(Vector2d end) {
        return new Angle2d(this, end);
    }
    
    public double det(Vector2d v1) {
        return x*v1.y-v1.x*y;
    }
    
    public TVector2d normalizeR() {
        return retval(v->v.normalize());
    }

    public TVector2d normalizeS() {
        return self(v->v.normalize());
    }
    
    public TVector3d expand() {
        return expand(0);
    }
    
    public TVector3d expand(double z) {
        return new TVector3d(x, y, z);
    }
    
}
