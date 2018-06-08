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

package solver;

import collection.TList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import shapeCollection.Grid;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class RectScan {
    Grid<Integer> rect;
    
    public RectScan(TPoint2i from, TPoint2i to) {
        this.rect = new Grid<>(from, to, (a,b)->0);
    }
    
    public Grid<Integer> zero() {
        return new Grid<>(rect.from, rect.to, (a,b)->0);
    }
    
    public TList<Integer> wallOfX() {
        return TList.nCopies(rect.y.size(), 1);
    }
    
    public TList<Integer> wallOfY() {
        return TList.nCopies(rect.x.size(), 1);
    }
    
    public RectScan mark(Collection<TPoint2i> mark) {
        mark.stream().forEach(p->rect.set(p, 1));
        return this;
    }
    
    Grid<Integer> cloneRect() {
        try {
            return rect.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RectScan.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("no reach");
    }
    
    TList<Grid<Integer>> rotate() {
        return TList.ofStatic(
            rect.pushHeadX(wallOfX()),
            rect.pushTailX(wallOfX()),
            rect.pushHeadY(wallOfY()),
            rect.pushTailY(wallOfY())
        );
    }
    
    public Grid<Integer> check() {
        return rect.pair(rect.pushHeadX(wallOfX()), (a,b)->a|b).
                    pair(rect.pushTailX(wallOfX()), (a,b)->a|(b<<1)).
                    pair(rect.pushHeadY(wallOfY()), (a,b)->a|(b<<2)).
                    pair(rect.pushTailY(wallOfY()), (a,b)->a|(b<<3));
    }
}
