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

import static collection.PrimitiveArrayWrap.unwrapD;
import collection.TList;
import javax.vecmath.Matrix3d;
import shape.TMatrix3d;

/**
 *
 * @author masao
 */
public class CovariantMatrix {
    
    public double covariant(TList<Double> x, TList<Double> y) {
        double ax = x.averageD(d->d);
        double ay = x==y?ax:y.averageD(d->d);
        return x.pair(y, (tx,ty)->(tx-ax)*(ty-ay)).averageD(d->d);
    }
    
    public TMatrix3d matrix3d(TList<TList<Double>> a) {
        return new TMatrix3d(new Matrix3d(unwrapD(crossFromVector(a))));
    }
    
    public TMatrix2d matrix2d(TList<TList<Double>> a) {
        return new TMatrix2d(crossFromVector(a));
    }
    
    public TList<Double> cross(TList<TList<Double>> a) {
        return a.cross(a, (x,y)->covariant(x,y));
    }
    
    public TList<Double> crossFromVector(TList<TList<Double>> v) {
        return cross(v.transposeT(l->l));
    }
    
    public TList<TList<Double>> matrix(TList<TList<Double>> a) {
        return a.matrix(a, (x,y)->covariant(x,y));
    }
    
    public TList<TList<Double>> matrixFromVector(TList<TList<Double>> v) {
        return matrix(v.transposeT(l->l));
    }
}
