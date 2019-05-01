/*
 Copyright 2017, 2018, 2019 Masao Tomono

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
public class ExceptionWrapper<S,T> implements Function<S,T> {
    ThrowableFunction<S,T> f;
    public ExceptionWrapper(ThrowableFunction<S,T> f) {
        this.f = f;
    }
    @Override
    public T apply(S s) {
        try {
            return f.apply(s);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
