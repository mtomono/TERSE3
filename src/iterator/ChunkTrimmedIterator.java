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
package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 * @author masao
 * @param <T>
 */
public class ChunkTrimmedIterator<T> extends ChunkIteratorBase<T> {

    public ChunkTrimmedIterator(Iterator<T> body, Predicate<T> pred) {
        super(body,pred);
    }

    @Override
    protected void whenTestsTrue(T e) {
        nextFound(retval);
        retval=new ArrayList<>();
    }
}
