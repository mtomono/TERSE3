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
@FunctionalInterface
public interface ParserFunction<S,T> {
    T apply(S s) throws ParseException;
}
