/*
 Copyright 2017, 2018, 2019 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package solver.graph;

import collection.P;
import collection.TList;
import static java.lang.Math.abs;
import java.util.List;
import java.util.function.Function;
import math.VectorOp;
import shape.TVector3d;
import shapeCollection.GridCoord;

/**
 *
 * @author masao
 * @param <K>
 */
public class MetricGridGraphOld<K extends List<Integer>> implements MetricGraph<K> {
    public final GridCoord gcoord;
    public final TVector3d weight;
    public final TList<K> dirs;
    Function<List<Integer>,K> translator;
    
    public MetricGridGraphOld(GridCoord gcoord, TList<K> dirs, TVector3d weight, Function<List<Integer>,K>translator) {
        this.gcoord = gcoord;
        this.dirs = dirs;
        this.translator=translator;
        this.weight = weight;
    }

    public double cost(List<Integer> k) {
        return VectorOp.dot(weight, TList.set(k).map(i->abs(i)));
    }
    
    @Override
    public TList<P<K, Double>> next(K from) {
        return dirs.map(d->P.p(translator.apply(VectorOp.addI(from, d)),cost(d))).filter(p->gcoord.contains(p.l())).sfix();
    }

    @Override
    public TList<K> all() {
        return gcoord.addresses().map(translator);
    }
    
}
