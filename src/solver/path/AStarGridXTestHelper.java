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
import shapeCollection.GridAxis;
import shapeCollection.GridCoord;
import shapeCollection.GridX;
import static solver.path.AStarGridX.exampleWOD;
import static string.Strings.toCharacters;

/**
 *
 * @author masao
 */
public class AStarGridXTestHelper {
    final public GridX<String> body;

    public AStarGridXTestHelper(String picture, Integer... dim) {
        this(picture, TList.sof(dim));
    }
    public AStarGridXTestHelper(String picture, TList<Integer> dim) {
        GridCoord axis = new GridCoord(dim.map(i->new GridAxis(0,i)));
        this.body = new GridX<>(axis,TList.set(toCharacters(picture)).map(c->c.toString()));
    }
    
    public TList<List<Integer>> path() {
        return new GridXBlocked(body.axis, exampleWOD).path(find("S"), find("G"), blocked());
    }
    
    public GridX<String> solve() {
        return body.fix().multiCSet(path().seek(1).seek(-1), p->"#");
    }
    
    public Collection<List<Integer>> blocked() {
        return body.axis.i2a(body.filterAt(s->s.equals("X")));
    }
    
    public List<Integer> find(String x) {
        TList<List<Integer>> addresses = body.addressOf(s->s.equals(x));
        if (addresses.isEmpty()) throw new RuntimeException("no "+x+" point is designated.");
        return addresses.get(0);
    }
    
    @Override
    public String toString() {
        return body.toString();
    }
}
