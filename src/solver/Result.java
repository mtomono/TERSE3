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
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class Result<T> {
    final TList<Integer> content;
    final T value;
    
    public Result(T init) {
        this(TList.c(), init);
    }
    
    public Result(TList<Integer> content, T value) {
        this.content = content;
        this.value = value;
    }

    public Result<T> add(int addedIndex, UnaryOperator<T> add) {
        return new Result<>(content.append(TList.wrap(addedIndex)), add.apply(value));
    }
    
    public <S> TList<S> render(TList<S> target) {
        return target.pickUp(content);
    }
    
}
