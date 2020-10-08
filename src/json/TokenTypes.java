/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.function.Predicate;
import static json.JsonLex.IGNORED;
import parser.ParseException;
import parser.Parser;
import parser.Token;

/**
 *
 * @author masao
 */
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
    static public Parser<String,TokenTypes,TokenTypes> is(Predicate<TokenTypes> pred) {
        return s->{
            TokenTypes retval=s.peek();
            if (!pred.test(retval))
                throw new ParseException(s.explain("Reached unexpected item :"+retval));
            return retval;
        };
    }
}
