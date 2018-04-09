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

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

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
}
