/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.function.Function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface FunctionWithCheckedException<S,T> {
    T apply(S s) throws Exception;
    default Function<S,T> uncheck() {
        return s->{
            try {
                return apply(s);
            } catch(Exception u) {
                throw new RuntimeException(u);
            }
        };
    }
    static public <S,T> Function<S,T> pass(FunctionWithCheckedException<S,T> f) {
        return f.uncheck();
    }
}
