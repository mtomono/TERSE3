/*
 Copyright 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package function;

import math.C2;
import math.Context;
import math.ContextBuilder;

/**
 *
 * @author masao
 * @param <K>
 * @param <T>
 */
public class CHolder<K,T extends Context<K,T>> extends Holder<T> {
    ContextBuilder<K,T> b;
    public CHolder(T init) {
        super(init);
        this.b=init.b();
    }
    public T xup(T v) {
        return push(get().add(v));
    }
    public T xup() {
        return xup(b.one());
    }
    public T upx(T v) {
        return setget(get().add(v));
    }
    public T upx() {
        return upx(b.one());
    }
    public T xdown(T v) {
        return push(get().sub(v));
    }
    public T xdown() {
        return xdown(b.one());
    }
    public T downx(T v) {
        return setget(get().sub(v));
    }
    public T downx() {
        return downx(b.one());
    }
}
