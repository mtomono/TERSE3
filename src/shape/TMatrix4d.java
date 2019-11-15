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
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

/**
 *
 * @author masao
 */
public class TMatrix4d extends Matrix4d {
    
    public TMatrix4d() {
        super();
    }
    
    public TMatrix4d(Matrix4d body) {
        super(body);
    }
    
    public TMatrix4d(double x00, double x01, double x02, double x03, 
                     double x10, double x11, double x12, double x13, 
                     double x20, double x21, double x22, double x23,
                     double x30, double x31, double x32, double x33) {
        super(x00, x01, x02, x03, 
              x10, x11, x12, x13,
              x20, x21, x22, x23,
              x30, x31, x32, x33);
    }
    
    public TMatrix4d retval(Consumer<TMatrix4d> consumer) {
        TMatrix4d retval = new TMatrix4d(this);
        consumer.accept(retval);
        return retval;
    }
    
    public TMatrix4d self(Consumer<TMatrix4d> consumer) {
        consumer.accept(this);
        return this;
    }
    
    public TMatrix3d linear() {
        return new TMatrix3d(m00,m01,m02,
                             m10,m11,m12,
                             m20,m21,m22);
    }
    
    public TVector3d translate() {
        return new TVector3d(m03,m13,m23);
    }
    
    public TMatrix4d addR(Matrix4d m1) {
        return retval(m->m.add(m1));
    }
    
    public TMatrix4d addS(Matrix4d m1) {
        return self(m->m.add(m1));
    }
    
    public TMatrix4d subR(Matrix4d m1) {
        return retval(m->m.sub(m1));
    }
    
    public TMatrix4d subS(Matrix4d m1) {
        return self(m->m.sub(m1));
    }
    
    public TMatrix4d transposeR() {
        return retval(m->m.transpose());
    }
    
    public TMatrix4d transposeS() {
        return self(m->m.transpose());
    }
    
    public TMatrix4d invertR() {
        return retval(m->m.invert());
    }
    
    public TMatrix4d invertS() {
        return self(m->m.invert());
    }
    
    public TMatrix4d invertAffine() {
        return TMatrix4d.affineTransform(linear().invertS(),linear().invertS().transformToVector(translate()).scaleS(-1));
    }
    
    public TMatrix4d mulR(Matrix4d m1) {
        return retval(m->m.mul(m1));
    }
    
    public TMatrix4d mulS(Matrix4d m1) {
        return self(m->m.mul(m1));
    }
    
    public TMatrix4d mulR(double s) {
        return retval(m->m.mul(s));
    }

    public TMatrix4d mulS(double s) {
        return self(m->m.mul(s));
    }

    public static TMatrix4d coordinateTransform(Vector4d x, Vector4d y, Vector4d z, Vector4d w) {
        TMatrix4d retval = new TMatrix4d();
        retval.setColumn(0, x);
        retval.setColumn(1, y);
        retval.setColumn(2, z);
        retval.setColumn(3, w);
        return retval;
    }
    
    public static TMatrix4d affineTransform(Matrix3d linear, Vector3d translation) {
        TMatrix4d retval = new TMatrix4d();
        retval.setColumn(0, linear.m00,linear.m01,linear.m02,0);
        retval.setColumn(1, linear.m10,linear.m11,linear.m12,0);
        retval.setColumn(2, linear.m20,linear.m21,linear.m22,0);
        retval.setColumn(3, translation.x,translation.y,translation.z,1);
        return retval;
    }
    
    public static TMatrix4d affineTransform(Vector3d x, Vector3d y, Vector3d z, Vector3d translation) {
        TMatrix4d retval = new TMatrix4d();
        retval.setColumn(0, x.x,x.y,x.z,0);
        retval.setColumn(1, y.x,y.y,y.z,0);
        retval.setColumn(2, z.x,z.y,z.z,0);
        retval.setColumn(3, translation.x,translation.y,translation.z,1);
        return retval;
    }

    public TPoint3d affine(Tuple3d t) {
        return new TPoint3d(t).self(v->transform(v));
    }
    
    public TList<Double> toList() {
        return TList.sof(m00,m01,m02,m03,
                         m10,m11,m12,m13,
                         m20,m21,m22,m23,
                         m30,m31,m32,m33);
    }
}