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

import collection.TList;

/**
 *
 * @author masao
 */
public class Source<S> {
    TList<Sink<S>> listeners;
    public Source() {
        listeners=TList.c();
    }
    public void add(Sink<S> sink) {
        listeners.add(sink);
    }
    public void addAll(Sink<S>... sinks) {
        listeners.addAll(TList.sof(sinks));
    }
    public void tellAll(S s) {
        listeners.forEach(l->l.listen(s));
    }
}
