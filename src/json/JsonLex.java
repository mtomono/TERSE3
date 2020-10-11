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
package json;

import iterator.SelectIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.BasicRegex;
import parser.ParseException;
import parser.Source;
import static json.TokenType.*;

/**
 *
 * @author masao
 */
public class JsonLex extends Source<String,TokenType> implements Iterator<TokenType> {
    final static boolean IGNORED=true;

    static Pattern spaces = Pattern.compile(BasicRegex.spaces);
    static Pattern string = Pattern.compile(BasicRegex.doubleQuoteStr);
    static Pattern number = Pattern.compile(BasicRegex.number);
    static Pattern truep=Pattern.compile("true");
    static Pattern falsep=Pattern.compile("false");
    static Pattern nullp=Pattern.compile("null");
    static String trailingSpaces="[ \t\f\r\n]*";
    static Pattern bracep=Pattern.compile("\\{"+trailingSpaces);
    static Pattern commap=Pattern.compile(","+trailingSpaces);
    static Pattern colonp=Pattern.compile(":"+trailingSpaces);
    Matcher matcher;
    final public Iterator<TokenType> ignored;
    public JsonLex(String src,int pos) {
        super(src);
        this.pos=pos;
        this.matcher=spaces.matcher(src);
        this.ignored=new SelectIterator<>(this,t->!t.ignored);
    }
    public JsonLex(String src) {
        this(src,0);
    }
    @Override
    public Source<String, TokenType> clone() {
        JsonLex retval=new JsonLex(src,pos);
        return retval;
    }
    @Override
    public TokenType peek() throws ParseException {
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
    public TokenType next() {
        if (!hasNext())
            throw new NoSuchElementException("reached end of src");
        TokenType retval=nextToken();
        return retval;
    }
    
    public TokenType nextToken() {
        char at=src.charAt(pos);
        switch(at) {
            //case '{': {pos++;return BRACE;}
            case '{': {matcher=matcher.usePattern(bracep);matcher.find(pos);pos=matcher.end();return BRACE;}
            case '}': {pos++;return UNBRACE;}
            case '[': {pos++;return SQUARE;}
            case ']': {pos++;return UNSQUARE;}
            //case ',': {pos++;return COMMA;}
            case ',': {matcher=matcher.usePattern(commap);matcher.find(pos);pos=matcher.end();return COMMA;}
            //case ':': {pos++;return COLON;}
            case ':': {matcher=matcher.usePattern(colonp);matcher.find(pos);pos=matcher.end();return COLON;}
            case '"': {
                matcher=matcher.usePattern(string);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return STRING;}
                break;
            }
            case 't': {
                matcher=matcher.usePattern(truep);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return TRUE;}
                break;
            }
            case 'f': {
                matcher=matcher.usePattern(falsep);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return FALSE;}
                break;
            }
            case 'n': {
                matcher=matcher.usePattern(nullp);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return NULL;}
                break;
            }
            case'0': case '1':case'2':case'3':case'4':case'5':case'6':case'7':case'8': case'9':case'.':case'-':case'+': {
                matcher=matcher.usePattern(number);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return NUMBER;}
                break;
            }
            case' ':case'\t':case'\f':case'\r':case'\n': {
                matcher=matcher.usePattern(spaces);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return SPACES;}
                break;
            }
        }
        pos=src.length();
        return INVALID;
    }
}
