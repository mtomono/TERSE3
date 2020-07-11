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
package collection.label;

import collection.TList;
import java.util.function.BiConsumer;

/**
 *
 * @author masao
 * @param <T>
 */
public class Hierarchy<T> {
    final public TList<Integer> hierarchy;
    final public BiConsumer<Hierarchy,T> process;
    final public int start;
    static public <T>Hierarchy<T> create(int start, BiConsumer<Hierarchy,T> process) {
        return new Hierarchy<>(TList.empty(),start-1,process);
    }
    public Hierarchy(TList<Integer> hierarchy, int start, BiConsumer<Hierarchy,T> process) {
        this.hierarchy=hierarchy;
        this.process=process;
        this.start=start;
    }
    public Hierarchy create(TList<Integer> hierarchy) {
        return new Hierarchy(hierarchy,start, process);
    }
    public Hierarchy up() {
        return create(hierarchy.seek(-1));
    }
    public Hierarchy down() {
        return create(hierarchy.append(start));
    }
    public Hierarchy next() {
        return create(hierarchy.seek(-1).append(hierarchy.last()+1));
    }
    public Hierarchy put(T t) {
        Hierarchy retval=next();
        process.accept(retval, t);
        return retval;
    }
    public int indent() {
        return hierarchy.size()-1;
    }
    @Override
    public String toString() {
        return hierarchy.toString();
    }
}
