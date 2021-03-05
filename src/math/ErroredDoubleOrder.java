/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.EqCenteredOrder;
import static java.lang.Math.abs;

/**
 *
 * @author masao
 */
public class ErroredDoubleOrder implements EqCenteredOrder<Double>{
    double eps;
    public ErroredDoubleOrder(double eps) {
        this.eps=eps;
    }
    @Override
    public boolean eq(Double c1, Double c2) {
        return abs(c1-c2)<eps;
    }
}


/**
 * Order and error.
 * error means a calculation result might be shifted to up and down than what it should be.
 * so with a number system with error like double. often time its user will face a need to 
 * cancel the effect of it.
 * one of those occasion is a comparison. you cannot expect the exact value as a result because 
 * of the error in the calculation.
 * when you realize that cancellation in Order, which means a value within epsilon away from 
 * the value in question is considered to be equal by means of comparison. 
 * there is an argument that this idea will leave this loophole. 
 * a=a+ε
 * a+ε=(a+ε)+ε=a+2ε
 * a+2ε=(a+2ε)+ε=a+3ε
 * 　　:
 * thus anything would be considered as the same value.
 * it is wrong.
 * you are super-duper incredibly lucky if you see a value somewhere far away as the same thing 
 * by this way. 
 * this inaccuracy in comparison also should be considered as an error in double calculation.
 * calculation in double comes with error. but it is tremendously faster. and most of all, it works fine.
 */
