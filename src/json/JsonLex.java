/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import debug.Te;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static json.JsonLex.TokenTypes.isIgnored;
import parser.Lexer;
import parser.ParseException;
import parser.Source;
import parser.Token;

/**
 *
 * @author masao
 */
public class JsonLex extends Lexer{
    static boolean IGNORED=true;

    public enum TokenTypes {
        SPACES(IGNORED),
        BRACE,
        UNBRACE,
        SQUARE,
        UNSQUARE,
        COLON,
        COMMA,
        STRING,
        INVALID;
        boolean ignored;
        private TokenTypes() {
            this.ignored=false;
        }
        private TokenTypes(boolean ignored) {
            this.ignored=ignored;
        }
        static boolean isIgnored(int type) {
            return values()[type].ignored;
        }
    }
    static Pattern spaces = Pattern.compile("[ \t\f\r\n]+");
    static Pattern string = Pattern.compile("\"[^\"]*\"");
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
    public void findNext() throws ParseException {
        Token retval;
        while (pos<src.length()-1) {
            if(isIgnored((retval=peek()).type)) 
                ignore(retval);
            else {
                nextFound(retval);
                return;
            }
        }
    }
    
    @Override
    public Token peek() throws ParseException {
        char at=src.charAt(pos);
        switch(at) {
            case '{': return new Token(TokenTypes.BRACE.ordinal(),pos,pos+1);
            case '}': return new Token(TokenTypes.UNBRACE.ordinal(),pos,pos+1);
            case '[': return new Token(TokenTypes.SQUARE.ordinal(),pos,pos+1);
            case ']': return new Token(TokenTypes.UNSQUARE.ordinal(),pos,pos+1);
            case ',': return new Token(TokenTypes.COMMA.ordinal(),pos,pos+1);
            case ':': return new Token(TokenTypes.COLON.ordinal(),pos,pos+1);
        }
        matcher=matcher.usePattern(spaces);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(TokenTypes.SPACES.ordinal(),pos,matcher.end());
        matcher=matcher.usePattern(string);
        if (matcher.find(pos)&&matcher.start()==pos) return new Token(TokenTypes.STRING.ordinal(),pos,matcher.end());
        return new Token(TokenTypes.INVALID.ordinal(),pos,src.length());
    }
    public String toString(Token token) {
        return String.format("(%s %s)", TokenTypes.values()[token.type].name(), src.substring(token.start,token.end));
    }
}
