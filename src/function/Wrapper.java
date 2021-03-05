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
public interface Wrapper<B, W extends Wrapper<B,W>> {
    B body();
    W self();
    W wrap(B body);
    /**
     * manipulate the body.
     * @param f
     * @return 
     */
    default W m(Function<B,B> f) {
        return wrap(f.apply(body()));
    }
    public static <B,W extends Wrapper<B,W>> boolean equalsByBody(W th, Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof Wrapper)) {
            return false;
        }
        W t = (W) e;
        return th.body().equals(t.body());
    }

}
