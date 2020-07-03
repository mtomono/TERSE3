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

import collection.TList;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 * @param <K>
 */
public class KList<T,K extends Decimal<K>> {
    DecimalField<K> db;
    TList<T> body;
    public KList(TList<T> body, DecimalField<K> db) {
        this.body=body;
        this.db=db;
    }
    
    public K average(Function<T,K> f) {
        return sigma(f).div(body.size());
    }

    public K sigma(Function<T,K> f) {
        return body.stream().map(f).reduce(db.zero(),(a,b)->a.add(b));
    }
    
    public K pai(Function<T,K> f) {
        return body.stream().map(f).reduce(db.one(),(a,b)->a.mul(b));
    }
    
    public TList<K> add(Function<T,K> f) {
        return body.map(f).accum(db.zero(), (a,b)->a.add(b));
    }

    public TList<K> mul(Function<T,K> f) {
        return body.map(f).accum(db.one(), (a,b)->a.mul(b));
    }
    public TList<T> toT() {
        return body;
    }
}
