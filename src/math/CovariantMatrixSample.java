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

import collection.TList;
import math2.C;

/**
 *
 * @author masao
 */
public class CovariantMatrixSample extends CovariantMatrix{
    
    @Override
    public double covariant(TList<Double> x, TList<Double> y) {
        double ax = x.toC(d->d,C.d).average().body();
        double ay = x==y?ax:y.toC(d->d,C.d).average().body();
        return x.pair(y, (tx,ty)->(tx-ax)*(ty-ay)).toC(d->d,C.d).sampleAverage().body();
    }
}
