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
public class TMatrix3d extends Matrix3d {
    
    public TMatrix3d() {
        super();
    }
    
    public TMatrix3d(Matrix3d body) {
        super(body);
    }
    
    public TMatrix3d(double x00, double x01, double x02, double x10, double x11, double x12, double x20, double x21, double x22) {
        super(x00, x01, x02, x10, x11, x12, x20, x21, x22);
    }
    
    public TMatrix3d retval(Consumer<TMatrix3d> consumer) {
        TMatrix3d retval = new TMatrix3d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TMatrix3d self(Consumer<TMatrix3d> consumer) {
        consumer.accept(this);
        return this;
    }
    
    public TMatrix3d addR(Matrix3d m1) {
        return retval(m->m.add(m1));
    }
    
    public TMatrix3d addS(Matrix3d m1) {
        return self(m->m.add(m1));
    }
    
    public TMatrix3d subR(Matrix3d m1) {
        return retval(m->m.sub(m1));
    }
    
    public TMatrix3d subS(Matrix3d m1) {
        return self(m->m.sub(m1));
    }
    
    public TMatrix3d transposeR() {
        return retval(m->m.transpose());
    }
    
    public TMatrix3d transposeS() {
        return self(m->m.transpose());
    }
    
    public TMatrix3d invertR() {
        return retval(m->m.invert());
    }
    
    public TMatrix3d invertS() {
        return self(m->m.invert());
    }
    
    public TMatrix3d mulR(Matrix3d m1) {
        return retval(m->m.mul(m1));
    }
    
    public TMatrix3d mulS(Matrix3d m1) {
        return self(m->m.mul(m1));
    }
    
    public TMatrix3d mulR(double s) {
        return retval(m->m.mul(s));
    }

    public TMatrix3d mulS(double s) {
        return self(m->m.mul(s));
    }

    public static TMatrix3d coordinateTransform(Vector3d x, Vector3d y, Vector3d z) {
        TMatrix3d retval = new TMatrix3d();
        retval.setColumn(0, x);
        retval.setColumn(1, y);
        retval.setColumn(2, z);
        return retval;
    }

    public TVector3d transformToVector(Tuple3d t) {
        return new TVector3d(t).self(v->transform(v));
    }
    
    public TPoint3d transformToPoint(Tuple3d t) {
        return new TPoint3d(t).self(v->transform(v));
    }
}
