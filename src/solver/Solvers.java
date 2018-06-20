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
import java.util.function.ToIntFunction;
import static shape.ShapeUtil.p2i;
import shape.TPoint2i;

/**
 *
 * @author masao
 */
public class Solvers {
    public static <T> TList<TPoint2i> extract2i(TList<T> body, ToIntFunction<T> volume, ToIntFunction<T> value) {
        return body.map((T x)->p2i(volume.applyAsInt(x), value.applyAsInt(x))).fix();
    }
    
}
