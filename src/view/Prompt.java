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

package view;

import java.io.PrintStream;
import java.util.function.Function;
import parser.ParseException;
import parser.Parser;
import parser.StrSource;

/**
 *
 * @author masao
 * @param <S>
 */
public class Prompt<S> {
    Parser<String, Character, S> p;
    Function<S, Object> s;
    
    public Prompt(Parser<String, Character, S> p, Function<S, Object> s) {
        this.p = p;
        this.s = s;
    }
    
    public boolean execIfMatch(String input, PrintStream pstr) {
        try {
            S ss = p.parse(new StrSource(input));
            String reply = s.apply(ss).toString();
            pstr.println(reply);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
