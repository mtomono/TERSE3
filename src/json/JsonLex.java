/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import iterator.SelectIterator;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parser.BasicRegex;
import parser.ParseException;
import parser.Source;
import parser.Token;

/**
 *
 * @author masao
 */
public class JsonLex extends Source<String,Token> implements Iterator<Token> {
    final static boolean IGNORED=true;
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
        
        final public boolean ignored;
        private TokenTypes() {
            this.ignored=false;
        }
        private TokenTypes(boolean ignored) {
            this.ignored=ignored;
        }
    }
    static public boolean asBoolean(Token t) {
        switch(TokenTypes.values()[t.type]) {
            case TRUE: return true;
            case FALSE: return false;
            case STRING: {
                if (t.substring().equals("true")) return true; 
                if (t.substring().equals("false")) return false;
            }
        }
        throw new RuntimeException("unexpected value occured");
    }
    static public String asString(Token t) {
        if (t.type==TokenTypes.STRING.ordinal()) return t.stripQuote();
        return t.substring();
    }
    static public int asInt(Token t) {
        if (t.type==TokenTypes.STRING.ordinal()) return Integer.parseInt(t.stripQuote());
        if (t.type==TokenTypes.NUMBER.ordinal()) return Integer.parseInt(t.substring());
        throw new RuntimeException("unexpected value occured");
    }
    static public double asDouble(Token t) {
        if (t.type==TokenTypes.STRING.ordinal()) return Double.parseDouble(t.stripQuote());
        if (t.type==TokenTypes.NUMBER.ordinal()) return Double.parseDouble(t.substring());
        throw new RuntimeException("unexpected value occured");
    }
    static public BigDecimal asBigDecimal(Token t) {
        if (t.type==TokenTypes.STRING.ordinal()) return new BigDecimal(t.stripQuote());
        if (t.type==TokenTypes.NUMBER.ordinal()) return new BigDecimal(t.substring());
        throw new RuntimeException("unexpected value occured");
    }
    static Pattern spaces = Pattern.compile(BasicRegex.spaces);
    static Pattern string = Pattern.compile(BasicRegex.doubleQuoteStr);
    static Pattern number = Pattern.compile(BasicRegex.number);
    static Pattern truep=Pattern.compile("true");
    static Pattern falsep=Pattern.compile("false");
    static Pattern nullp=Pattern.compile("null");
    static Predicate<Token> isIgnored=t->!TokenTypes.values()[t.type].ignored;
    Matcher matcher;
    final public Iterator<Token> ignored;
    public JsonLex(String src,int pos) {
        super(src);
        this.pos=pos;
        this.matcher=spaces.matcher(src);
        this.ignored=new SelectIterator<>(this,t->!TokenTypes.values()[t.type].ignored);
    }
    public JsonLex(String src) {
        this(src,0);
    }
    @Override
    public Source<String, Token> clone() {
        JsonLex retval=new JsonLex(src,pos);
        return retval;
    }
    @Override
    public Token peek() throws ParseException {
        try {
            return ignored.next();
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
    public Token next() {
        if (!hasNext())
            throw new NoSuchElementException("reached end of src");
        Token retval=nextToken();
        pos=retval.end;
        return retval;
    }
    
    public Token nextToken() {
        char at=src.charAt(pos);
        switch(at) {
            case '{': return new Token(src,TokenTypes.BRACE.ordinal(),pos,pos+1);
            case '}': return new Token(src,TokenTypes.UNBRACE.ordinal(),pos,pos+1);
            case '[': return new Token(src,TokenTypes.SQUARE.ordinal(),pos,pos+1);
            case ']': return new Token(src,TokenTypes.UNSQUARE.ordinal(),pos,pos+1);
            case ',': return new Token(src,TokenTypes.COMMA.ordinal(),pos,pos+1);
            case ':': return new Token(src,TokenTypes.COLON.ordinal(),pos,pos+1);
            case '"': {
                matcher=matcher.usePattern(string);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.STRING.ordinal(),pos,matcher.end());
            }
            case 't': {
                matcher=matcher.usePattern(truep);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.TRUE.ordinal(),pos,matcher.end());
            }
            case 'f': {
                matcher=matcher.usePattern(falsep);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.FALSE.ordinal(),pos,matcher.end());
            }
            case 'n': {
                matcher=matcher.usePattern(nullp);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.NULL.ordinal(),pos,matcher.end());
            }
            case'0': case '1':case'2':case'3':case'4':case'5':case'6':case'7':case'8': case'9':case'.': {
                matcher=matcher.usePattern(number);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.NUMBER.ordinal(),pos,matcher.end());
            }
            case' ':case'\t':case'\f':case'\r':case'\n': {
                matcher=matcher.usePattern(spaces);
                if (matcher.find(pos)&&matcher.start()==pos) return new Token(src,TokenTypes.SPACES.ordinal(),pos,matcher.end());
            }
        }
        return new Token(src,TokenTypes.INVALID.ordinal(),pos,src.length());
    }
    static public String toString(Token token) {
        return String.format("(%s %s)", JsonLex.TokenTypes.values()[token.type].name(), token.substring());
    }
}
