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

package viewlist;

import java.util.List;

/**
 *
 * @author mtomono
 * @param <T>
 */
public class ReverseView<T> extends CView<T>{
    public ReverseView(List<T> body) {
        super(body);
    }
    
    @Override
    public int at(int index) {
        return size() - 1 - index;
    }
    
}
