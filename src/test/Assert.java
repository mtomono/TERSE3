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

package test;

import java.util.Iterator;

/**
 *
 * @author masao
 */
public class Assert {
    /**
     * compare two iterable and check whether they contain identical objects.
     * @param <T>
     * @param result
     * @param expected 
     */
    final static public <T> void assertIdentical(Iterable<T> result, Iterable<T> expected) {
        Iterator iterr = result.iterator();
        Iterator itere = expected.iterator();
        int i = 0;
        while (iterr.hasNext()) {
            assert itere.hasNext():"result is longer than expected";
            assert iterr.next()==itere.next():"result["+i+"]!=expected["+i+"]";
        }
        assert !itere.hasNext():"expected is longer than result";
    }
}
