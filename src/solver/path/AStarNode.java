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
import java.util.Optional;
import static solver.path.AStarStatus.*;

/**
 * Node for A Star.
 * @author masao
 * @param <T>
 */
public abstract class AStarNode<T extends AStarNode> {
    Optional<T> parent;
    AStarStatus status;
    int costToCome;
    int costToGo;
    
    public AStarNode() {
        this.parent=Optional.empty();
        this.status=NONE;
        costToCome=0;
        costToGo=0;
    }
    
    public AStarStatus status() {
        return status;
    }
    
    public T open(Optional<T> parent, int costToCome, int costToGo) {
        this.status = OPEN;
        this.parent = parent;
        this.costToCome = costToCome;
        this.costToGo = costToGo;
        return (T)this;
    }
    
    public int score() {
        return costToCome+costToGo;
    }
    
    public void close() {
        this.status=CLOSE;
    }
            
    public TList<T> result() {
        TList<T> retval = TList.c();
        result(retval);
        return retval;
    }
    
    public void result(TList<T> retval) {
        retval.add(parent.map(p->{p.result(retval);return (T)this;}).orElse((T)this));
    }
}
