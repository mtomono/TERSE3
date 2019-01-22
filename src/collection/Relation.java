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

package collection;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public class Relation<S,T> {
    public final TList<P<S,T>> plist;
    public Relation(TList<P<S,T>> plist) {
        this.plist = plist;
    }
    public TList<T> get(S from) {
        return plist.filter(p->p.l().equals(from)).map(p->p.r());
    }
    public Relation<T,S> flip() {
        return new Relation<>(plist.map(p->p.flip()));
    }
}
