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
package math;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author masao
 */
public class SparseVectorSpaceHash<T,K extends Field<K>> extends SparseVectorSpace<T,K> {
    Map<T,K> body;
    public SparseVectorSpaceHash(Map<T,K> body) {
        this.body = body;
    }
    @Override
    public Map<T, K> getMap() {
        return body;
    }

    @Override
    public SparseVectorSpaceHash<T,K> clone() {
        return new SparseVectorSpaceHash<>(new HashMap<>(body));
    }
    
}
