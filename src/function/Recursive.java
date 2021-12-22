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

import collection.TList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 */
public class Recursive<T> {
    Deque<T> stack;
    public Recursive(T t) {
        this.stack=new ArrayDeque<>();
        stack.push(t);
    }
    public Recursive(Collection<? extends T> t) {
        this.stack=new ArrayDeque<>();
        stack.addAll(t);
    }
    public void exec(Function<T, List<T>> f) {
        while (!stack.isEmpty())
            TList.set(f.apply(stack.pop())).sfix().reverse().forEach(e->stack.push(e));
    }
}
