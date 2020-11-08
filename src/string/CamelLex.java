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
package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.BasicRegex;
import parser.LexBase;
import parser.Source;
import static string.TokenType.BUMP;
import static string.TokenType.HEAD;
import static string.TokenType.INTEGER;
import static string.TokenType.INVALID;

/**
 *
 * @author masao
 */
public class CamelLex extends LexBase<TokenType> {
    final static boolean IGNORED=true;

    static Pattern camelHead = Pattern.compile(BasicRegex.camelHead);
    static Pattern camelBump = Pattern.compile(BasicRegex.camelBump);
    static Pattern integer = Pattern.compile(BasicRegex.integer);
    Matcher matcher;
    public CamelLex(String src,int pos) {
        super(src,pos);
        this.matcher=camelHead.matcher(src);
    }
    public CamelLex(String src) {
        this(src,0);
    }
    @Override
    public Source<String, TokenType> clone() {
        CamelLex retval=new CamelLex(src,pos);
        return retval;
    }

    public TokenType nextToken() {
        char at=src.charAt(pos);
        if ('0'<=at&&at<='9') {
            matcher=matcher.usePattern(integer);
            if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return INTEGER;}
        } else if ('A'<=at&&at<='Z') {
            matcher=matcher.usePattern(camelBump);
            if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return BUMP;}
        } else {
            matcher=matcher.usePattern(camelHead);
            if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return HEAD;}
        }
        pos=src.length();
        return INVALID;
    }
}
