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

package parser;

import java.util.List;

/**
 *
 * @author masao
 * @param <T>
 */
public class ListSource<T> extends Source<List<T>, T> {
    
    public ListSource(List<T> s) {
        super(s);
    }
    
    @Override
    public final T peek() throws ParseException {
        if (pos >= src.size())
            throw new ParseException("unexpectedly reached end of text.");
        return src.get(pos);
    }
    
    @Override
    public final String explain(String e) {
        String ret = super.explain(e);
        if (src!=null && 0<=pos && pos<src.size()) 
            ret += ": '" + src.get(pos) + "'";
        return ret;
    }

    @Override
    public List<T> sub(int... sec) {
        return src.subList(sec[0], sec[1]);
    }
}
