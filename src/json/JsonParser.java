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

import collection.P;
import collection.TList;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import static json.TokenType.*;
import parser.ParseException;
import parser.Parser;

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
    public class NotFoundException extends RuntimeException {
        public NotFoundException(String msg) {
            super(msg);
        }
    }
    static <U> Parser<String,TokenType,U> get(String targetKey, BiFunction<String,TokenType,U> f) {
        Parser<String,TokenType,TokenType> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenType,U> target = is(STRING).l().accept(t->stripQuote(t.strip()).equals(targetKey)).next(is(COLON)).next(value.l(strip(f)));
        Parser<String,TokenType,TokenType> skipped = is(STRING).l().except(t->stripQuote(t.strip()).equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(skipped.next(is(COMMA).tor(is(UNBRACE).end())).many()).next(target);
    }
    /**
     * alternative implimentation of get.
     * 
     * @param <U>
     * @param targetKey
     * @param f
     * @return 
     */
    static <U> Parser<String,TokenType,Optional<U>> getO(String targetKey, BiFunction<String,TokenType,U> f) {
        Parser<String,TokenType,TokenType> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenType,U> target = is(STRING).l().accept(t->stripQuote(t.strip()).equals(targetKey)).next(is(COLON)).next(value.l(strip(f)));
        Parser<String,TokenType,TokenType> skipped = is(STRING).l().except(t->stripQuote(t.strip()).equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(skipped.next(is(COMMA).tor(is(UNBRACE))).many()).next(target.opt(v->v));
    }    
    static Parser<String,TokenType,Map<String,String>> getAll(Supplier<Map<String,String>> s) {
        return getAll(s,JsonParser::asString);
    }
    static <U>Parser<String,TokenType,Map<String,U>> getAll(Supplier<Map<String,U>> s,BiFunction<String,TokenType,U> f) {
        Parser<String,TokenType,TokenType> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenType,P<String,U>> property = is(STRING).l().apply(t->stripQuote(t)).tr().prev(is(COLON)).and(value.l(f));
        return is(BRACE).next(property.prev(is(COMMA).tor(is(UNBRACE))).reduce(s,(a,b)->{a.put(b.l(),b.r());return a;}));
    }
    static Parser<String,TokenType,TList<P<String,String>>> getAllList() {
        return getAllList(JsonParser::asString);
    }
    static <U>Parser<String,TokenType,TList<P<String,U>>> getAllList(BiFunction<String,TokenType,U> f) {
        Parser<String,TokenType,TokenType> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,TokenType,P<String,U>> property = is(STRING).l().apply(t->stripQuote(t)).tr().prev(is(COLON)).and(value.l(f));
        return is(BRACE).next(property.prev(is(COMMA).tor(is(UNBRACE))).many(p->p));
    }

    static public String stripQuote(String s) {
        return s.substring(1,s.length()-1);
    }
    static public <U> BiFunction<String,TokenType,U> strip(BiFunction<String,TokenType,U> f) {
        return (s,t)->f.apply(s.strip(),t);
    }
    static public Object asToken(String str, TokenType t) {
        switch(t) {
            case TRUE: return true;
            case FALSE: return false;
            case STRING: return stripQuote(str);
            case NUMBER: {
                if (str.contains(".")||str.contains("e"))
                    return Double.parseDouble(str);
                else
                    return Integer.parseInt(str);
            }
            case NULL: return null;
        }
        throw new RuntimeException("unexpected value occured");
    }
    static public Object asTokenBD(String str, TokenType t) {
        switch(t) {
            case TRUE: return true;
            case FALSE: return false;
            case STRING: return stripQuote(str);
            case NUMBER: {
                if (str.contains(".")||str.contains("e"))
                    return new BigDecimal(str);
                else
                    return Integer.parseInt(str);
            }
            case NULL: return null;
        }
        throw new RuntimeException("unexpected value occured");
    }
    static public Boolean asBoolean(String str,TokenType t) {
        switch(t) {
            case TRUE: return Boolean.TRUE;
            case FALSE: return Boolean.FALSE;
            case NULL: return Boolean.FALSE;
            case STRING: {
                if (str.equals("true")) return true; 
                if (str.equals("false")) return false;
            }
        }
        throw new RuntimeException("unexpected value occured");
    }
    static public String asString(String str,TokenType t) {
        if (t==STRING) return stripQuote(str);
        if (t==NULL) return null;
        return str;
    }
    static public Integer asInt(String str,TokenType t) {
        if (t==STRING) return Integer.parseInt(stripQuote(str));
        if (t==NUMBER) return Integer.parseInt(str);
        throw new RuntimeException("unexpected value occured");
    }
    static public Double asDouble(String str,TokenType t) {
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
    /**
     * i(gnore).
     * skip the source to the position where a token starts which is not to be ignored.
     * @return 
     */
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
