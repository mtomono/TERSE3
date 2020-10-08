/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.function.Predicate;

/**
 *
 * @author masao
 */
public class Token {
    static public Parser<String,Token,Token> is(Predicate<Token> pred) {
        return s->{
            Token retval=s.peek();
            if (!pred.test(retval))
                throw new ParseException(s.explain("Reached unexpected item :"+retval));
            return retval;
        };
    }
    final public String src;
    final public int type;
    final public int start;
    final public int end;
    public Token(String src,int type,int start,int end) {
        this.src=src;
        this.type=type;
        this.start=start;
        this.end=end;
    }
    public String substring() {
        return src.substring(start,end);
    }
    public String stripQuote() {
        return src.substring(start+1,end-1);
    }
    public String toString() {
        return String.format("(%d %d %d : %s )", type,start,end,substring());
    }
}
