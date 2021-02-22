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
package collection;

import function.Wrapper;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <V>
 * @param <W>
 */
public interface TListWrapper<V,W extends TListWrapper<V,W>> extends Wrapper<TList<V>,W> {
    default V get(int i) {
        return body().get(i);
    }
    default W reset(Function<W,W> f) {
        body().reset(f.apply(self()).body());
        return self();
    }
}
