/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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

import java.util.function.Function;

/**
 *
 * @author masao
 */
public interface Wrapper<B, W extends Wrapper<B,W>> {
    B body();
    W self();
    W wrap(B body);
    /**
     * manipulate the body.
     * @param f
     * @return 
     */
    default W m(Function<B,B> f) {
        return wrap(f.apply(body()));
    }
    public static <B,W extends Wrapper<B,W>> boolean equalsByBody(W th, Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Wrapper)) {
            return false;
        }
        W t = (W) e;
        return th.body().equals(t.body());
    }

}
