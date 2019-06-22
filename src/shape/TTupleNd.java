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
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class TTupleNd  extends TList<Double> {

    public TTupleNd(List<Double> body) {
        super(body);
    }
    public TTupleNd c(List<Double> body) {
        return new TTupleNd(body);
    }
    public TTupleNd bop(List<Double> b, BinaryOperator<Double> f) {
        return c(pair(b, (x,y)->f.apply(x,y))); 
    }
    public TTupleNd op(UnaryOperator<Double> f) {
        return c(map(f)); 
    }
    public TTupleNd sub(TTupleNd b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNd add(TTupleNd b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNd scale(double s) {
        return op(x->x*s);
    }
    public TTupleNd mul(TTupleNd b) {
        return bop(b,(x,y)->x*y);
    }
    public TTupleNi i() {
        return new TTupleNi(map(e->e.intValue()));
    }
    public TTupleNl l() {
        return new TTupleNl(map(e->e.longValue()));
    }
    public TTupleNd d() {
        return new TTupleNd(map(e->e.doubleValue()));
    }
    public TTupleNd fix() {
        return c(fix());
    }
    public TTupleNd sfix() {
        return c(sfix());
    }
    public TTupleNl round() {
        return new TTupleNl(map(Math::round));
    }
}