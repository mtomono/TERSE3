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

import java.util.Objects;

/**
 *
 * @author masao
 * @param <S>
 * @param <T>
 */
public abstract class Source<S, T> {
    public int pos;
    public S src;
    abstract public Source<S, T> clone();
    abstract public T peek() throws ParseException;
    abstract public S sub(int... sec);
    abstract public T get(int at) throws ParseException;
    abstract public S rest();
    abstract public void forward(int at);

    public Source(S src) {
        this.pos=0;
        this.src = src;
    }

    public Source<S,T> reset(S src) {
        pos=0;
        this.src=src;
        return this;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Source)) {
            return false;
        }
        Source<S, T> t = (Source<S, T>) obj;
        return src.equals(t.src) && pos == t.pos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.pos;
        hash = 59 * hash + Objects.hashCode(this.src);
        return hash;
    }

    public void seek() {
        pos++;
    }
    public void revert(Source<S, T> s) throws ParseException{
        if (!src.equals(s.src))
            throw new ParseException("Tried to revert to a different source.");
        pos = s.pos;
    }

    public String explain(String e) {
        return "["+pos+"]"+e;
    }
}
