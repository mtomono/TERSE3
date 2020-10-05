/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import collection.ArrayInt;
import collection.TList;
import debug.Te;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.BasicRegex;
import parser.Lexer;
import parser.ParseException;
import parser.Parser;
import parser.Source;
import parser.Token;

/**
 *
 * @author masao
 */
public class JsonLex extends Lexer{
    static boolean IGNORED=true;

    public enum TokenTypes {
        BRACE,
        UNBRACE,
        SQUARE,
        UNSQUARE,
        COLON,
        COMMA,
        SPACES(IGNORED),
        STRING,
        NUMBER,
        TRUE,
        FALSE,
        NULL,
        INVALID;
        
        boolean ignored;
        private TokenTypes() {
            this.ignored=false;
        }
        private TokenTypes(boolean ignored) {
            this.ignored=ignored;
        }
    }
    public static Parser<String,Token,Token> is(TokenTypes... types) {
        ArrayInt typesA=ArrayInt.set(TList.sof(types).map(ti->ti.ordinal()));
        return Lexer.is(t->typesA.contains(t.type));
    }
    static Pattern spaces = Pattern.compile(BasicRegex.spaces);
    static Pattern string = Pattern.compile(BasicRegex.doubleQuoteStr);
    static Pattern number = Pattern.compile(BasicRegex.number);
    static Pattern truep=Pattern.compile("true");
    static Pattern falsep=Pattern.compile("false");
    static Pattern nullp=Pattern.compile("null");
    Matcher matcher;
    public JsonLex(String src) {
        super(src);
        this.matcher=spaces.matcher(src);
    }
    @Override
    public Source<String, Token> clone() {
        JsonLex retval=new JsonLex(src);
        retval.pos=pos;
        return retval;
    }
    @Override
    public boolean isIgnored(Token token) {
        return TokenTypes.values()[token.type].ignored;
    }
    @Override
    public Token nextToken() throws ParseException {
        char at=src.charAt(pos);
        switch(at) {
            case '{': return new Token(src,TokenTypes.BRACE.ordinal(),pos,pos+1);
            case '}': return new Token(src,TokenTypes.UNBRACE.ordinal(),pos,pos+1);
            case '[': return new Token(src,TokenTypes.SQUARE.ordinal(),pos,pos+1);
            case ']': return new Token(src,TokenTypes.UNSQUARE.ordinal(),pos,pos+1);
            case ',': return new Token(src,TokenTypes.COMMA.ordinal(),pos,pos+1);
            case ':': return new Token(src,TokenTypes.COLON.ordinal(),pos,pos+1);
        }
        matcher=matcher.usePattern(spaces);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.SPACES.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(string);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.STRING.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(truep);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.TRUE.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(falsep);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.FALSE.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(nullp);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.NULL.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(number);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.NUMBER.ordinal(),pos,matcher.end());
        return new Token(src,TokenTypes.INVALID.ordinal(),pos,src.length());
    }
    public String toString(Token token) {
        return String.format("(%s %s)", TokenTypes.values()[token.type].name(), src.substring(token.start,token.end));
    }
}
