/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            case '{': {pos++;return BRACE;}
            case '}': {pos++;return UNBRACE;}
            case '[': {pos++;return SQUARE;}
            case ']': {pos++;return UNSQUARE;}
            case ',': {pos++;return COMMA;}
            case ':': {pos++;return COLON;}
            case '"': {
                matcher=matcher.usePattern(string);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return STRING;}
            }
            case 't': {
                matcher=matcher.usePattern(truep);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return TRUE;}
            }
            case 'f': {
                matcher=matcher.usePattern(falsep);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return FALSE;}
            }
            case 'n': {
                matcher=matcher.usePattern(nullp);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return NULL;}
            }
            case'0': case '1':case'2':case'3':case'4':case'5':case'6':case'7':case'8': case'9':case'.':case'-':case'+': {
                matcher=matcher.usePattern(number);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return NUMBER;}
            }
            case' ':case'\t':case'\f':case'\r':case'\n': {
                matcher=matcher.usePattern(spaces);
                if (matcher.find(pos)&&matcher.start()==pos) {pos=matcher.end();return SPACES;}
            }
        }
        pos=src.length();
        return INVALID;
    }
}
