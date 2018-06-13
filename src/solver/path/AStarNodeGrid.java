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

import shape.TPoint2i;

/**
 * Node for Grid2 A Star.
 * @author masao
 */
public class AStarNodeGrid extends AStarNode{
    final public TPoint2i point;
    
    public AStarNodeGrid(TPoint2i point, AStarStatus status) {
        this.point = point;
        this.status = status;
    }

    @Override
    public String toString() {
        return status+"@"+point;
    }

    public String toDetailedString() {
        return status+"@"+point+":"+costToCome+":"+costToGo;
    }

}
