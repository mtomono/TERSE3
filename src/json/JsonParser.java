/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.function.Function;
import static json.JsonLex.TokenTypes.BRACE;
import static json.JsonLex.TokenTypes.COLON;
import static json.JsonLex.TokenTypes.COMMA;
import static json.JsonLex.TokenTypes.FALSE;
import static json.JsonLex.TokenTypes.NULL;
import static json.JsonLex.TokenTypes.NUMBER;
import static json.JsonLex.TokenTypes.STRING;
import static json.JsonLex.TokenTypes.TRUE;
import static json.JsonLex.TokenTypes.UNBRACE;
import static json.JsonLex.asString;
import static json.JsonLex.is;
import parser.Parser;
import static parser.Parsers.many;
import parser.Token;

/**
 *
 * @author masao
 */
public class JsonParser {
    static <U> Parser<String,Token,U> get(String targetKey, Function<Token,U> f) {
        Parser<String,Token,Token> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,Token,Token> target = is(STRING).accept(t->asString(t).equals(targetKey)).next(is(COLON)).next(value);
        Parser<String,Token,Token> skipped = is(STRING).except(t->asString(t).equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(many(skipped.next(is(COMMA)))).next(target.apply(f).tor(is(UNBRACE).apply(x->null)));
    }
}
