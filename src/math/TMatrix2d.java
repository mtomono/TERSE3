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

package math;

import static collection.PrimitiveArrayWrap.wrap;
import collection.TList;
import static function.ComparePolicy.inc;
import static java.lang.Math.abs;
import static shape.ShapeUtil.vector2;
import shape.TVector2d;

/**
 *
 * @author masao
 */
public class TMatrix2d {
    TList<Double> body;
    final static public TMatrix2d id = new TMatrix2d(1,0,0,1);
    
    public TMatrix2d(TList<Double> body) {
        this.body = body;
    }
    
    public TMatrix2d(double... e) {
        this(TList.set(wrap(e)));
    }
    
    public double m00() {
        return body.get(0);
    }
    
    public double m01() {
        return body.get(1);
    }
    
    public double m10() {
        return body.get(2);
    }
    
    public double m11() {
        return body.get(3);
    }
    
    public double det() {
        return m00()*m11()-m01()*m10();
    }
    
    public double tr() {
        return m00()+m11();
    }
    
    public TMatrix2d addR(TMatrix2d m) {
        return new TMatrix2d(body.pair(m.body, (a,b)->a+b).sfix());
    }
    
    public TMatrix2d scaleR(double d) {
        return new TMatrix2d(body.map(e->e*d).sfix());
    }
    
    public TList<TVector2d> toColumnVectors() {
        return TList.sof(new TVector2d(m00(),m01()),new TVector2d(m10(),m11()));
    }
    
    public TVector2d transform(TVector2d t) {
        return vector2(toColumnVectors().map(v->t.dot(v)));
    }
    
    /**
     * eigenValue for 2x2 matrix.
     * solve det(A-λI)=0 : means,
     * (m00-λ)*(m11-λ)-(m01*m10)=0
     * λ^2-(m00+m11)*λ+m00*m11-m01*m10=0;
     * @return 
     */
    public TList<Double> eigenValues() {
        double det = Quadratic.det(1, -tr(), det());
        assert det >=0;
        return Quadratic.solveWithDet(1, -tr(), det);
    }
    
    public TVector2d eigenVector(double e) {
        TMatrix2d eigen = addR(id.scaleR(-e));
        return vector2(-eigen.m01(), eigen.m00());
    }

    public TVector2d maxEigenVector() {
        return eigenVector(eigenValues().max(inc(x->abs(x))).get());
    }
}
