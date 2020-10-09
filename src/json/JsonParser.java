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

import collection.TList;
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
        Parser<String,TokenType,U> target = is(STRING).l().accept(t->stripQuote(t.strip()).equals(targetKey)).next(is(COLON)).next(value.l(strip(f)));
        Parser<String,TokenType,TokenType> skipped = is(STRING).l().except(t->stripQuote(t.strip()).equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(many(skipped.next(is(COMMA)))).next(target.tor(is(UNBRACE).apply(x->null)));
    }

    static public String stripQuote(String s) {
        return s.substring(1,s.length()-1);
    }
    static public <U> BiFunction<String,TokenType,U> strip(BiFunction<String,TokenType,U> f) {
        return (s,t)->f.apply(s.strip(),t);
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
