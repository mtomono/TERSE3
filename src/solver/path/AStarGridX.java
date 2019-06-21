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

package solver.path;

import collection.TList;
import static java.lang.Integer.max;
import static java.lang.Math.abs;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import math.VectorOp;
import static math.VectorOp.addI;
import static math.VectorOp.subI;
import static solver.path.AStarStatus.BLOCKED;
import static solver.path.AStarStatus.NONE;
import shape.TPoint2i;
import shapeCollection.GridCoord;
import shapeCollection.GridX;

/**
 *
 * @author masao
 */
public class AStarGridX extends AStar<AStarNodeGridX> {
    public GridX<AStarNodeGridX> space;
    public TList<List<Integer>> allDirs;
    public List<Integer> from;
    public List<Integer> to;
    public TList<List<Integer>> path;
    public Function<List<Integer>,Integer> weightOnDirection;
    public static Function<List<Integer>,Integer> exampleWOD = l->TList.sof(1,1).append(TList.nCopies(max(0,l.size()-2),3)).pair(l,(a,b)->abs(b)*abs(a)).sumI(i->i);
    public static Function<List<Integer>,Integer> wodFixed(Integer... weight) {
        TList<Integer> weightT = TList.sof(weight);
        return l->weightT.pair(l, (a,b)->abs(b)*abs(a)).sumI(i->i);
    }

    public AStarGridX(GridX<AStarNodeGridX> space, List<Integer> from, List<Integer> to, Function<List<Integer>, Integer> weightOnDirection, Function<GridCoord, TList<List<Integer>>> searchOrderOfDirs) {
        super();
        assert weightOnDirection!=null : "weightOnDirection is null";
        this.weightOnDirection = weightOnDirection;
        this.space = space;
        this.allDirs = searchOrderOfDirs.apply(space.axis);
        this.from = from;
        this.to = TList.set(to);
        TList<AStarNodeGridX> rawPath = search().map(a->a.result()).orElse(TList.empty());
        this.path = rawPath.map(a->a.point);
    }
        
    @Override
    public int costToGo(AStarNodeGridX astar) {
        return weightOnDirection.apply(subI(to, astar.point));
    }
    
    @Override
    public int costToMove(Optional<AStarNodeGridX> from, AStarNodeGridX to) {
        return from.map(a->weightOnDirection.apply(subI(to.point, a.point))).orElse(0);
    }

    @Override
    public TList<AStarNodeGridX> candidates(AStarNodeGridX astar) {
        return allDirs.map(q->addI(astar.point,q)).filter(p->space.contains(p)&&space.get(p).status!=BLOCKED).map(p->space.get(p));
    }
    
    @Override
    public AStarNodeGridX getStart() {
        return space.get(from);
    }
    
    @Override
    public AStarNodeGridX getGoal() {
        return space.get(to);
    }

    @Override
    public boolean isGoal(AStarNodeGridX astar) {
        return astar.point.equals(to);
    }
        
    @Override
    public int upperBoundary() {
        return weightOnDirection.apply(VectorOp.subI(to, from))*2;
    }
    
    public TList<List<Integer>> path() {
        return path;
    }
    
    @Override
    public String toString() {
        return space.map(a->a.status.display).toString();
    }
    
    public String toStringAndFind(TPoint2i from, TPoint2i to) {
        return space.map(a->a.status.display).multiCSet(path, p->"#").toString();
    }
    
    static public GridX<AStarNodeGridX> space(GridCoord gcoord, Collection<List<Integer>> blocked) {
        return new GridX<>(gcoord, addr->new AStarNodeGridX(addr, NONE)).fix().multiCSet(blocked, p->new AStarNodeGridX(p,BLOCKED));
    }

}
