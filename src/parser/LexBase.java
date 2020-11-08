/*
 Copyright 2017, 2018, 2019, 2020 Masao Tomono

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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author masao
 */
public abstract class LexBase<TOKEN> extends Source<String,TOKEN> implements Iterator<TOKEN> {
    abstract public TOKEN nextToken();
    public LexBase(String src,int pos) {
        super(src);
        this.pos=pos;
    }
    public LexBase(String src) {
        this(src,0);
    }

    @Override
    public TOKEN peek() throws ParseException {
        try {
            return this.next();
        } catch (NoSuchElementException e) {
            throw new ParseException("reached end of source");
        }
    }
    @Override
    public String sub(int... sec) {
        return src.substring(sec[0],sec[1]);
    }
    @Override
    public boolean hasNext() {
        return pos<src.length();
    }
    @Override
    public TOKEN next() {
        if (!hasNext())
            throw new NoSuchElementException("reached end of src");
        TOKEN retval=nextToken();
        return retval;
    }
}
