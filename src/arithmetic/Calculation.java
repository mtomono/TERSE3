/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 *
 * @author masao
 */
public class Calculation {
    public static double roundAt(double target, int digits) {
        double scale = pow(10,digits);
        return round(target*scale)/scale;
    }
}
