/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import collection.TList;
import debug.Te;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import static json.TokenType.*;
import parser.ParseException;
import parser.Parser;
import static parser.Parsers.many;

/**
 *
 * @author masao
 */
public interface JsonParser extends Parser<String,TokenType,TokenType> {
    public static Parser<String,TokenType,TokenType> is(TokenType... types) {
        TList<TokenType> typeList=TList.sof(types);
        return s->{
            TokenType retval=s.peek();
            for (;retval.ignored;retval=s.peek());
            if (!typeList.contains(retval))
                throw new ParseException(s.explain("Reached unexpected item :"+retval));
            return retval;
        };
    }
    static <U> Parser<String,TokenType,U> get(String targetKey, BiFunction<String,TokenType,U> f) {
        Parser<String,TokenType,TokenType> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenType,U> target = is(STRING).l().accept(t->stripQuote(t.trim()).equals(targetKey)).next(is(COLON)).next(value.l(trim(f)));
        Parser<String,TokenType,TokenType> skipped = is(STRING).l().except(t->stripQuote(t.trim()).equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(many(skipped.next(is(COMMA)))).next(target.tor(is(UNBRACE).apply(x->null)));
    }

    static public String stripQuote(String s) {
        return s.substring(1,s.length()-1);
    }
    static public <U> BiFunction<String,TokenType,U> trim(BiFunction<String,TokenType,U> f) {
        return (s,t)->f.apply(s.trim(),t);
    }
    static public boolean asBoolean(String str,TokenType t) {
        switch(t) {
            case TRUE: return true;
            case FALSE: return false;
            case STRING: {
                if (str.equals("true")) return true; 
                if (str.equals("false")) return false;
            }
        }
        throw new RuntimeException("unexpected value occured");
    }
    static public String asString(String str,TokenType t) {
        if (t==STRING) return stripQuote(str);
        return str;
    }
    static public int asInt(String str,TokenType t) {
        if (t==STRING) return Integer.parseInt(stripQuote(str));
        if (t==NUMBER) return Integer.parseInt(str);
        throw new RuntimeException("unexpected value occured");
    }
    static public double asDouble(String str,TokenType t) {
        if (t==STRING) return Double.parseDouble(stripQuote(str));
        if (t==NUMBER) return Double.parseDouble(str);
        throw new RuntimeException("unexpected value occured");
    }
    static public BigDecimal asBigDecimal(String str,TokenType t) {
        if (t==STRING) return new BigDecimal(stripQuote(str));
        if (t==NUMBER) return new BigDecimal(str);
        throw new RuntimeException("unexpected value occured");
    }

    // not used. only for explanation, this remains here.
    public static Parser<String,TokenType,TokenType> i() {
        return s->{
            int tokenHead=s.pos;
            TokenType retval=s.peek();
            while (retval.ignored) {
                tokenHead=s.pos;
                retval=s.peek();
            }
            s.pos=tokenHead;
            return null;
        };
    }
}
