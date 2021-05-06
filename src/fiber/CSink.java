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
package fiber;

import java.util.Collection;

/**
 *
 * @author masao
 */
public class CSink<T> implements Sink<T> {
    final public Collection<T> body;
    public CSink(Collection<T> body) {
        this.body=body;
    }
    @Override
    public void listen(T t) {
        body.add(t);
    }
}
