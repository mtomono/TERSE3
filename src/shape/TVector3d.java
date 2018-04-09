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
import javax.vecmath.Matrix3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author masao
 */
public class TVector3d extends Vector3d {
    public TVector3d() {
        super();
    }
    
    public TVector3d(Tuple3d t) {
        super(t);
    }
    
    static public TVector3d c(Tuple3d start, Tuple3d end) {
        return new TVector3d(end).subS(start);
    }
    
    public TVector3d(double x, double y, double z) {
        super(x, y, z);
    }
    
    public TVector3d retval(Consumer<TVector3d> consumer) {
        TVector3d retval = new TVector3d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TVector3d self(Consumer<TVector3d> consumer) {
        consumer.accept(this);
        return this;
    }
    
    public TVector3d subR(Tuple3d v1) {
        return retval(v->v.sub(v1));
    }
    
    public TVector3d subS(Tuple3d v1) {
        return self(v->v.sub(v1));
    }
    
    public void sub(double x, double y, double z) {
        setX(this.x-x);
        setY(this.y-y);
        setZ(this.z-z);
    }
    
    public TVector3d subR(double x, double y, double z) {
        return retval(v->v.sub(x, y, z));
    }
    
    public TVector3d subS(double x, double y, double z) {
        return self(v->v.sub(x, y, z));
    }
    
    public TVector3d addR(Tuple3d v1) {
        return retval(v->v.add(v1));
    }
    
    public TVector3d addS(Tuple3d v1) {
        return self(v->v.add(v1));
    }
    
    public void add(double x, double y, double z) {
        setX(this.x+x);
        setY(this.y+y);
        setZ(this.z+z);
    }
    
    public TVector3d addR(double x, double y, double z) {
        return retval(v->v.add(x, y, z));
    }
    
    public TVector3d addS(double x, double y, double z) {
        return self(v->v.add(x, y, z));
    }
    
    public TVector3d scaleR(double s) {
        return retval(v->v.scale(s));
    }
    
    public TVector3d scaleS(double s) {
        return self(v->v.scale(s));
    }
    
    public TVector3d sizeR(double l) {
        return retval(v->v.scale(l/v.length()));
    }
    
    public TVector3d sizeS(double l) {
        return self(v->v.scale(l/v.length()));
    }
    
    public TVector3d negateR() {
        return retval(v->v.negate());
    }
    
    public TVector3d negateS() {
        return self(v->v.negate());
    }

    public Angle3d angleO(Vector3d end) {
        return new Angle3d(this, end);
    }
    
    public TVector3d cross(Vector3d v1) {
        return new TVector3d().retval(v->v.cross(this, v1));
    }
    
    public TVector3d normalizeR() {
        return retval(v->v.normalize());
    }

    public TVector3d normalizeS() {
        return self(v->v.normalize());
    }
    
    public TVector3d transformR(Matrix3d m) {
        return retval(v->m.transform(v));
    }

    public TVector3d transformS(Matrix3d m) {
        return self(v->m.transform(v));
    }
    
    public TVector2d shrink() {
        return new TVector2d(x, y);
    }
}
