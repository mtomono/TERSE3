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
import static json.TokenTypes.*;
import parser.ParseException;
import parser.Parser;
import static parser.Parsers.many;

/**
 *
 * @author masao
 */
public interface JsonParser extends Parser<String,TokenTypes,TokenTypes> {
    public static Parser<String,TokenTypes,TokenTypes> is(TokenTypes... types) {
        TList<TokenTypes> typeList=TList.sof(types);
        return s->{
            TokenTypes retval=s.peek();
            for (;retval.ignored;retval=s.peek());
            if (!typeList.contains(retval))
                throw new ParseException(s.explain("Reached unexpected item :"+retval));
            return retval;
        };
    }
    public static Parser<String,TokenTypes,TokenTypes> i() {
        return s->{
            int tokenHead=s.pos;
            TokenTypes retval=s.peek();
            while (retval.ignored) {
                tokenHead=s.pos;
                retval=s.peek();
            }
            s.pos=tokenHead;
            return null;
        };
    }
    static <U> Parser<String,TokenTypes,U> get(String targetKey, BiFunction<String,TokenTypes,U> f) {
        Parser<String,TokenTypes,TokenTypes> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenTypes,U> target = i().next(is(STRING).l().accept(t->stripQuote(t).equals(targetKey))).next(is(COLON)).next(value.l(trim(f)));
        Parser<String,TokenTypes,TokenTypes> skipped = i().next(is(STRING).l().except(t->stripQuote(t).equals(targetKey))).tr().next(is(COLON)).next(value);
        return is(BRACE).next(many(skipped.next(is(COMMA)))).next(target.tor(is(UNBRACE).apply(x->null)));
    }

    static public String stripQuote(String s) {
        return s.substring(1,s.length()-1);
    }
    static public <U> BiFunction<String,TokenTypes,U> trim(BiFunction<String,TokenTypes,U> f) {
        return (s,t)->f.apply(s.trim(),t);
    }
    static public boolean asBoolean(String str,TokenTypes t) {
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
    static public String asString(String str,TokenTypes t) {
        if (t==STRING) return stripQuote(str);
        return str;
    }
    static public int asInt(String str,TokenTypes t) {
        if (t==STRING) return Integer.parseInt(stripQuote(str));
        if (t==NUMBER) return Integer.parseInt(str);
        throw new RuntimeException("unexpected value occured");
    }
    static public double asDouble(String str,TokenTypes t) {
        if (t==STRING) return Double.parseDouble(stripQuote(str));
        if (t==NUMBER) return Double.parseDouble(str);
        throw new RuntimeException("unexpected value occured");
    }
    static public BigDecimal asBigDecimal(String str,TokenTypes t) {
        if (t==STRING) return new BigDecimal(stripQuote(str));
        if (t==NUMBER) return new BigDecimal(str);
        throw new RuntimeException("unexpected value occured");
    }
}
