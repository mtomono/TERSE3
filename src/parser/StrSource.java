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

/**
 *
 * @author masao
 */
public class StrSource extends Source<String, Character> {
    
    public StrSource(String s) {
        super(s);
    }
    
    @Override
    public StrSource clone() {
        StrSource ret = new StrSource(src);
        ret.pos = pos;
        return ret;
    }
    
    @Override
    public final Character peek() throws ParseException {
        if (pos >= src.length())
            throw new ParseException("unexpectedly reached end of text.");
        return src.charAt(pos);
    }
    
    @Override
    public final String explain(String e) {
        String ret = super.explain(e);
        if (src!=null && 0<=pos && pos<src.length()) 
            ret += ": '" + src.charAt(pos) + "'";
        return ret;
    }

    @Override
    public String sub(int... sec) {
        return src.substring(sec[0], sec[1]);
    }

    @Override
    public Character get(int at) {
        return src.charAt(at);
    }
    
}
