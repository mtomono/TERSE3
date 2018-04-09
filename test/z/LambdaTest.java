/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

import java.util.function.Function;

/**
 *
 * @author masao
 */
public class LambdaTest {
    String test(int input) {
        return Integer.toString(input);
    }
    
    void acceptLambda(Function<Integer, String> func) {
        System.out.println(func.apply(20));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LambdaTest tested = new LambdaTest();
        tested.acceptLambda(tested::test);
    }
    
}
