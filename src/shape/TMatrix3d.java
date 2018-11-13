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

import collection.P;
import collection.TList;
import static function.ComparePolicy.inc;
import java.util.function.Consumer;
import javax.vecmath.Matrix3d;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import static shape.ShapeUtil.err;
import static shape.ShapeUtil.vector3;

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
    
    public TList<Double> toList() {
        return TList.sof(m00,m01,m02,m10,m11,m12,m20,m21,m22);
    }
    
    /**
     * eigen vector of eigen value of maximum absolute value.
     * application of the power method.
     * @param start
     * @return 
     */
    public TVector3d maxEigenVectorOld(TVector3d start) {
        TVector3d current = start.normalizeR();
        TVector3d post;
        TVector3d next;
        int count = 0;
        while (true) {
            post = current;
            next = transformToVector(current);
            current = next.normalizeR();
            if (current.epsilonEquals(post, err))
                break;
            count++;
            assert count < 1000 : "too slow to converge";
        }
        assert count != 0 : "initial value hit any eigen vector; there's no guarantee this is the eigenvector of the biggest eigenvalue";
        if (count==0) throw new RuntimeException("initial value hit any eigen vector; there's no guarantee this is the eigenvector of the biggest eigenvalue");
        double eigenValue = next.dot(post)/post.dot(post);
        System.out.println(count +":"+eigenValue);
        return next;
    }

    /**
     * eigen vector of eigen value of maximum absolute value.
     * application of the power method.
     * @param start
     * @return 
     * @throws shape.EigenValuesAreTooCloseException 
     */
    public P<P<TVector3d,Double>,Integer> maxEigenVectorCandidate(TVector3d start) {
        TVector3d current = start.normalizeR();
        TVector3d post;
        TVector3d next;
        int count = 0;
        while (true) {
            post = current;
            next = transformToVector(current);
            current = next.normalizeR();
            if (current.epsilonEquals(post, err))
                break;
            count++;
            if (count > 1000) throw new EigenValuesAreTooCloseException.Runtime();
            assert count < 1000 : "too slow to converge";
        }
        double eigenValue = next.dot(post)/post.dot(post);
        return P.p(current, eigenValue).addP(count);
    }

    /**
     * eigen vector of eigen value of maximum absolute value.
     * application of the power method.
     * @param start
     * @return 
     * @throws shape.EigenValuesAreTooCloseException 
     */
    public P<TVector3d,Double> maxEigen() {
        TList<TVector3d> initials = TList.sof(TVector3d.x1, TVector3d.y1, TVector3d.z1, vector3(1,1,1));
        return initials.map(i->maxEigenVectorCandidate(i)).max(inc(p->p.l().r())).get().l();
    }
    
    public TVector3d maxEigenVector() {
        return maxEigen().l();
    }
}