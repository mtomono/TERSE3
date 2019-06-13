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
import java.util.List;
import shape.TPoint2i;
import shape.TPoint3i;
import shapeCollection.GridAxis;
import shapeCollection.GridCoord;
import static shapeCollection.GridCoord.gcoord;
import shapeCollection.GridX;
import static solver.path.AStarGridX.exampleWOD;
import static string.Strings.toCharacters;

/**
 *
 * @author masao
 */
public class AStarGridXTestHelper {
    public static GridX<String> toGrid(String picture) {
        TList<String> sl = TList.sof(picture.split("\n"));
        return new GridX<>(gcoord(TList.sof(0,0),TList.sof(sl.get(0).length()-1,sl.size()-1)), TList.set(toCharacters(picture)).filter(c->!c.equals('\n')).map(c->c.toString()));
    }
    
    public static GridX<String> toGridX(String picture, Integer... dim) {
        return toGridX(picture, TList.sof(dim));
    }
    public static GridX<String> toGridX(String picture, TList<Integer>dim) {
        GridCoord axis = new GridCoord(dim.map(i->new GridAxis(0,i)));
        return new GridX<>(axis,TList.set(toCharacters(picture))).map(c->c.toString());
    }
    
    public static Collection<List<Integer>> blocked(GridX<String> base) {
        return base.axis.i2a(base.filterAt(s->s.equals("X")));
    }
    
    public static TList<List<Integer>> test2DPath(GridX<String> base, TPoint2i from, TPoint2i to) {
        return new GridXBlocked(base.axis,1,1).path(from, to, blocked(base));
    }
    
    public static GridX<String> test2D(GridX<String> base, TPoint2i from, TPoint2i to) {
        return base.fix().multiCSet(new GridXBlocked(base.axis, 1,1).path(from, to, blocked(base)), p->"#");
    }
    
    public static TList<List<Integer>> test3DPath(GridX<String> base, TPoint3i from, TPoint3i to) {
        return new GridXBlocked(base.axis,1,1,3).path(from, to, blocked(base));
    }
    
    public static GridX<String> test3D(GridX<String> base, TPoint3i from, TPoint3i to) {
        return base.fix().multiCSet(new GridXBlocked(base.axis, 1,1,3).path(from, to, blocked(base)), p->"#");
    }
    
    public static GridX<String> solve(GridX<String> base, List<Integer> from, List<Integer> to) {
        return base.fix().multiCSet(new GridXBlocked(base.axis, exampleWOD).path(from, to, blocked(base)), p->"#");
    }
}
