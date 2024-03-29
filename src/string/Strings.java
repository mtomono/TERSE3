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

package string;

import collection.TList;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import parser.BasicRegex;

/**
 *
 * @author masao
 */
public class Strings {
    public static String n(char c, int n) {
        char[] ret = new char[n];
        Arrays.fill(ret, c);
        return new String(ret);
    }
    
    public static List<Character> toCharacters(String s) {
        return toCharacters(s.toCharArray());
    }
    
    public static List<Character> toCharacters(char... c) {
        return new AbstractList<Character>() {
            @Override
            public Character get(int index) {
                return c[index];
            }

            @Override
            public int size() {
                return c.length;
            }
            
        };
    }
    
    static public String nullableToString(Object o) {
        return o!=null ? o.toString() : "null";
    }
    
    static public String concat(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        strs.stream().forEachOrdered(s->sb.append(s));
        return sb.toString();
    }
    
    static public TList<Character> asCharList(String str) {
        return TList.set(new AbstractList<Character>() {
            @Override
            public Character get(int index) {
                return str.charAt(index);
            }

            @Override
            public int size() {
                return str.length();
            }
            
        });
    }
    
    static public String wrap(String str, int len) {
        return asCharList(str).fold(len).map(l->l.toFlatString()).toWrappedString();
    }
    
    static public String escape(String str) {
        return str.replace("\\", "\\\\").replace("\n", "\\n").replace("\t", "\\t").
                   replace("\"",""+"\\\"").replace("\'","\\\'").
                   replace("\f", "\\f").replace("\b", "\\b").replace("\r", "\\r");
    }
    
    static public String doubleQuote(String str) {
        return '"'+str+'"';
    }
    
    static public String singleQuote(char str) {
        return "'"+str+"'";
    }
    
    static public String asString(String str) {
        return doubleQuote(escape(str));
    }

    static public String indent(String s) {
        return TList.sof(s.split("\n")).map(l->"\t"+l+"\n").toFlatString();
    }
}
