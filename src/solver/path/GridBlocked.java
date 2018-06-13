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
import java.util.function.BiFunction;
import java.util.function.Predicate;
import static solver.path.AStarStatus.*;
import shapeCollection.Grid;
import shape.TPoint2i;

/**
 *
 * @author masao
 * @param <T>
 */
public class GridBlocked<T> {
    protected Grid<T> base;
    protected Predicate<T> blocked;
    
    static public GridBlocked<String> c(TPoint2i from, TPoint2i to, Collection<TPoint2i> blocked) {
        return c(from,to,blocked," ","X");
    }
    
    static public <T> GridBlocked<T> c(TPoint2i from, TPoint2i to, Collection<TPoint2i> blocked, T safe, T block) {
        return new GridBlocked<>(Grid.c(from, to, blocked, safe, block), e->e.equals(block));
    }
    
    public GridBlocked(Grid<T> base, Predicate<T> blockedPred) {
        this.base = base;
        this.blocked = blockedPred;
    }
    
    public Grid<T> grid() {
        return base;
    }
    
    Grid<AStarNodeGrid> astarSpace() {
        return new Grid<>(base.from, base.to, (a,b)->new AStarNodeGrid(new TPoint2i(a,b), NONE)).mark(base.pfilter(blocked), p->new AStarNodeGrid(p, BLOCKED));
    }
    
    public TList<TPoint2i> blocked() {
        return base.pfilter(blocked);
    }
    
    public boolean isBlocked(int x, int y) {
        return blocked.test(base.get(x,y));
    }
    
    public boolean isBlocked(TPoint2i p) {
        return isBlocked(p.x, p.y);
    }
    
    public TList<TPoint2i> path(TPoint2i from, TPoint2i to) {
        return new AStarGrid(astarSpace(), from, to).path();
    }
    
    public Grid<T> pathOnGrid(TPoint2i from, TPoint2i to, BiFunction<T, TPoint2i, T> f) {
        return base.copy().mark(path(from,to), p->f.apply(base.get(p), p));
    }
    
    public Grid<String> blockToStringGrid() {
        return new Grid<>(base.from, base.to, (a,b)->" ").mark(blocked(), p->"X");
    }
    
    public String blockToString() {
        return blockToStringGrid().toRightHandedSystem().toString();
    }
    
    public String pathToString(TPoint2i from, TPoint2i to) {
        return blockToStringGrid().mark(path(from,to), p->"o").toRightHandedSystem().toString();
    }
}
