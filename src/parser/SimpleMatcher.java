/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

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

import function.Chain;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author masao
 */
public class SimpleMatcher {
    Matcher m;
    public SimpleMatcher(String pattern) {
        this.m=Pattern.compile(pattern).matcher("");
    }
    public Stream<String> extract(String line) {
        return new Chain<>(m.reset(line)).c(x->x.lookingAt()).f(x->x.results().map(r->line.substring(r.start(),r.end()))).get();
    }
}
