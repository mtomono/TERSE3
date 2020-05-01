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

/**
 *
 * @author masao
 * @param <K>
 */
public class KVector<K extends Field<K>> implements VectorSpace<KVector<K>,K> {
    public final K body;
    
    public KVector(K body) {
        this.body=body;
    }

    @Override
    public KVector<K> add(KVector<K> other) {
        return new KVector<>(body.add(other.body));
    }

    @Override
    public KVector<K> sub(KVector<K> other) {
        return new KVector<>(body.sub(other.body));
    }
    
    @Override
    public KVector<K> scale(K other) {
        return new KVector<>(body.mul(other));
    }
}
