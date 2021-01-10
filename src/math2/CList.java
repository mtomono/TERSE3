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
package math2;

import collection.TList;
import debug.Te;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 * @param <K>
 */
public class CList<T,K extends Number> {
    C.Builder<K> b;
    TList<T> body;
    public CList(TList<T> body, C.Builder<K> context) {
        this.body=body;
        this.b=context;
    }
    
    public C<K> average(Function<T,K> f) {
        return sigma(f).div(b.b(body.size()));
    }

    public C<K> sigma(Function<T,K> f) {
        return body.stream().map(f.andThen(v->b.b(v))).reduce(b.zero(),(a,b)->a.add(b));
    }
    
    public C<K> pai(Function<T,K> f) {
        return body.stream().map(f.andThen(v->b.b(v))).reduce(b.one(),(a,b)->a.mul(b));
    }
    
    public TList<C<K>> add(Function<T,K> f) {
        Te.e(b.op);
        return body.map(f.andThen(v->b.b(v))).accumFromStart((a,b)->a.add(b));
    }

    public TList<C<K>> mul(Function<T,K> f) {
        return body.map(f.andThen(v->b.b(v))).accumFromStart((a,b)->a.mul(b));
    }
    public TList<T> toT() {
        return body;
    }
}
