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
public class Functions {
    public static <S,T> Function<S,T> f(Function<S,T> f) {
        return f;
    }
}
