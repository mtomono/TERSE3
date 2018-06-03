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

package shape;

import collection.TList;
import static java.lang.Math.pow;
import java.util.Optional;

/**
 * .
 * when a dimention is not divided into two, let the upper part be the longer one.
 * @author masao
 */
public class SpatialTree<T> {
    int boxNumInOneBox(int dimention) {
        return (int)pow(2, dimention);
    }
    int quadrant(int fromx, int fromy, int tox, int toy, int x, int y) {
        return 0;
    }
}
