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

package walker;

import java.util.function.BiPredicate;


/**
 * SelectWalker
 * SelectWalker is a Walker which is like SelectIterator for Iterator.
 * @author masao
 */
public class SelectWalker<S, T> extends AbstractBufferedWalker<S, T> {
    Walker<S, T> base;
    BiPredicate<S, T> condition;
    
    public SelectWalker(Walker<S, T> base, BiPredicate<S, T> condition) {
        this.base = base;
        this.condition = condition;
    }
    
    @Override
    protected void findNext() {
        while (base.hasNext()) {
            base.fore();
            if (condition.test(base.left(), base.right())) {
                nextFound(base.left(), base.right());
                break;
            }
        }
    }
    
}
