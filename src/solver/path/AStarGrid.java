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
import java.util.Collection;
import java.util.Optional;
import static solver.path.AStarStatus.BLOCKED;
import static solver.path.AStarStatus.NONE;
import shapeCollection.Grid;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class AStarGrid extends AStar<AStarNodeGrid> {
    final static int costFromParent = 1;
    public Grid<AStarNodeGrid> space;
    public TPoint2i from;
    public TPoint2i to;
    public TList<TPoint2i> path;
    
    public AStarGrid(Grid<AStarNodeGrid> space, TPoint2i from, TPoint2i to) {
        super();
        this.space = space;
        this.from = from;
        this.to = to;
        TList<AStarNodeGrid> rawPath = search().map(a->a.result()).orElse(TList.empty());
        this.path = rawPath.map(a->a.point);
    }
    
    @Override
    public int costToGo(AStarNodeGrid astar) {
        return astar.point.to(to).manhattanLength();
    }
    
    @Override
    public int costToMove(Optional<AStarNodeGrid> from, AStarNodeGrid to) {
        return from.map(a->costFromParent).orElse(0);
    }

    @Override
    public TList<AStarNodeGrid> candidates(AStarNodeGrid astar) {
        return TPoint2i.quadrants.map(q->q.addR(astar.point)).filter(p->space.contains(p)&&space.get(p).status!=BLOCKED).map(p->space.get(p));
    }
    
    @Override
    public AStarNodeGrid getStart() {
        return space.get(from);
    }
    
    @Override
    public AStarNodeGrid getGoal() {
        return space.get(to);
    }

    @Override
    public boolean isGoal(AStarNodeGrid astar) {
        return to.equals(astar.point);
    }
        
    @Override
    public int upperBoundary() {
        return from.to(to).manhattanLength()*2;
    }
    
    public TList<TPoint2i> path() {
        return path;
    }
    
    @Override
    public String toString() {
        return space.map(a->a.status.display).toRightHandedSystem().toString();
    }
    
    public String toStringAndFind(TPoint2i from, TPoint2i to) {
        return space.map(a->a.status.display).mark(path, p->"#").toRightHandedSystem().toString();
    }
    
    static public Grid<AStarNodeGrid> space(TPoint2i areaFrom, TPoint2i areaTo, Collection<TPoint2i> blocked) {
        return new Grid<>(areaFrom, areaTo, (a,b)->new AStarNodeGrid(new TPoint2i(a,b), NONE)).mark(blocked, p->new AStarNodeGrid(p, BLOCKED));
    }

}
