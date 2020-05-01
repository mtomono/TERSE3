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

import java.util.Map;

/**
 *
 * @author masao
 * @param <K>
 * @param <B>
 */
abstract public class SparseVectorSpace<B,K extends Field<K>> implements VectorSpaceWithBasis<SparseVectorSpace<B,K>,B,K> {
    abstract public Map<B,K> getMap();
    abstract public SparseVectorSpace<B,K> clone();

    @Override
    public SparseVectorSpace<B, K> add(SparseVectorSpace<B, K> other) {
        SparseVectorSpace<B,K> retval = clone();
        Map<B,K> body = getMap();
        Map<B,K> target = other.getMap();
        target.forEach((b,k)->body.put(b,body.get(b)==null?k:body.get(b).add(k)));
        return retval;
    }

    @Override
    public SparseVectorSpace<B, K> sub(SparseVectorSpace<B, K> other) {
        SparseVectorSpace<B,K> retval = clone();
        Map<B,K> body = getMap();
        Map<B,K> target = other.getMap();
        target.forEach((b,k)->body.put(b,body.get(b)==null?k.zero().sub(k):body.get(b).sub(k)));
        return retval;
    }

    @Override
    public SparseVectorSpace<B, K> scale(K other) {
        SparseVectorSpace<B,K> retval = clone();
        Map<B,K> body = getMap();
        body.forEach((b,k)->body.put(b,k.mul(other)));
        return retval;
    }
}
