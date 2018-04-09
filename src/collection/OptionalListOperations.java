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

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toCollection;

/**
 *
 * @author mtomono
 */
public class OptionalListOperations {
    static public <T> boolean hasAny(List<Optional<T>> list) {
        return list.stream().anyMatch(e->e.isPresent());
    }
    
    static public <T> boolean noEmpty(List<Optional<T>> list) {
        return list.stream().allMatch(e->e.isPresent());
    }
    
    static public <T> List<Optional<T>> optionize(List<T> list, Supplier<List<Optional<T>>> sup) {
        return list.stream().map(e->Optional.ofNullable(e)).collect(toCollection(sup));
    }
    
    static public <T> List<T> deoptionize(List<Optional<T>> list, Supplier<T> forEmpty, Supplier<List<T>> sup) {
        return list.stream().map(oe->oe.orElseGet(forEmpty)).collect(toCollection(sup));
    }
}
