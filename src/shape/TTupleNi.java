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
public class TTupleNi extends TList<Integer> {

    public TTupleNi(List<Integer> body) {
        super(body);
    }
    public TTupleNi c(List<Integer> body) {
        return new TTupleNi(body);
    }
    public TTupleNi bop(List<Integer> b, BinaryOperator<Integer> f) {
        return c(pair(b, (x,y)->f.apply(x,y))); 
    }
    public TTupleNi op(UnaryOperator<Integer> f) {
        return c(map(f)); 
    }
    public TTupleNi sub(TTupleNi b) {
        return bop(b,(x,y)->x-y);
    }
    public TTupleNi add(TTupleNi b) {
        return bop(b,(x,y)->x+y);
    }
    public TTupleNi scale(Integer s) {
        return op(x->x*s);
    }
    public TTupleNi mul(TTupleNi b) {
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
    public TTupleNi fix() {
        return c(super.fix());
    }
    public TTupleNi sfix() {
        return c(super.sfix());
    }
}
