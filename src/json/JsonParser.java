/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import static json.JsonLex.TokenTypes.BRACE;
import static json.JsonLex.TokenTypes.COLON;
import static json.JsonLex.TokenTypes.COMMA;
import static json.JsonLex.TokenTypes.FALSE;
import static json.JsonLex.TokenTypes.NULL;
import static json.JsonLex.TokenTypes.NUMBER;
import static json.JsonLex.TokenTypes.STRING;
import static json.JsonLex.TokenTypes.TRUE;
import static json.JsonLex.is;
import parser.Parser;
import static parser.Parsers.many;
import parser.Token;

/**
 *
 * @author masao
 */
public class JsonParser {
    static Parser<String,Token,Token> create(String targetKey) {
        Parser<String,Token,Token> value =is(STRING,TRUE,FALSE,NULL,NUMBER);
        Parser<String,Token,Token> target = is(STRING).accept(t->t.substring().equals(targetKey)).next(is(COLON)).next(value);
        Parser<String,Token,Token> skipped = is(STRING).except(t->t.substring().equals(targetKey)).tr().next(is(COLON)).next(value);
        return is(BRACE).next(many(skipped.next(is(COMMA)))).next(target);
    }
}
