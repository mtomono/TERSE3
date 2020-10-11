/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author masao
 */
public class ParseCompleteException extends RuntimeException {
    public ParseCompleteException() {
        super("parsing completed before reaching the expected end");
    }
}
