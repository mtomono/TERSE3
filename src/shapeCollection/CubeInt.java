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

package shapeCollection;

import collection.TList;

/**
 * 
 * @author masao
 */
public class CubeInt {
    public TList<Integer> factor;
    public CubeInt(int... factor) {
        this(TList.sofi(factor));
    }
    public CubeInt(TList<Integer> factor) {
        this.factor=factor;
    }
    public TList<Integer> mesh(TList<Integer> point) {
        return point.pair(factor,(a,b)->a/b).sfix();
    }
    public TList<Integer> center(TList<Integer> mesh) {
        return mesh.pair(factor,(a,b)->a*b+b/2);
    }
}
