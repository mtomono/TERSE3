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
public enum TokenType {
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
    private TokenType() {
        this.ignored=false;
    }
    private TokenType(boolean ignored) {
        this.ignored=ignored;
    }
}
