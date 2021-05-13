/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.function.Supplier;

/**
 * Assertion which stays no matter what the switch should be.
 * @author masao
 */
public class Assert {
    public static void sure(Supplier<Boolean> pred,String message) {
        if(!pred.get()) {
            System.out.println(message);
            throw new RuntimeException(message);
        }
    }
}
