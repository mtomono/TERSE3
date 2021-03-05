/*
 Copyright 2017, 2018, 2019, 2020, 2021 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package math;

import java.math.BigDecimal;

/**
 * Operations for Rational.
 * @author masao
 */
public class RationalOp implements Op<Rational> {

    @Override
    public Rational b(int v) {
        return new Rational(v,1);
    }

    @Override
    public Rational b(long v) {
        return new Rational(v,1);
    }

    @Override
    public Rational b(float v) {
        throw new UnsupportedOperationException("Not supported.");
        //Rational cannot be built from decimal directly. translate decimal to Rational first by Rational.valueof(target,threshold).
        //and then, call b(Rational).
    }

    @Override
    public Rational b(double v) {
        throw new UnsupportedOperationException("Not supported.");
        //Rational cannot be built from decimal directly. translate decimal to Rational first by Rational.valueof(target,threshold).
        //and then, call b(Rational).
    }

    @Override
    public Rational b(String v) {
        return Rational.parseRational(v);
    }
    
    @Override
    public Rational b(BigDecimal n) {
        throw new UnsupportedOperationException("Not supported.");
        //Rational cannot be built from decimal directly. translate decimal to Rational first by Rational.valueof(target,threshold).
        //and then, call b(Rational).
    }
    
    @Override
    public Rational b(Rational n) {
        return n;
    }


    @Override
    public Rational add(Rational v0, Rational v1) {
        return v0.add(v1);
    }

    @Override
    public Rational sub(Rational v0, Rational v1) {
        return v0.sub(v1);
    }

    @Override
    public Rational mul(Rational v0, Rational v1) {
        return v0.mul(v1);
    }

    @Override
    public Rational div(Rational v0, Rational v1) {
        return v0.div(v1);
    }

    @Override
    public Rational negate(Rational v0) {
        return v0.negate();
    }

    @Override
    public Rational abs(Rational v0) {
        return v0.abs();
    }

    @Override
    public Rational sqrt(Rational v0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Rational zero() {
        return Rational.ZERO;
    }

    @Override
    public Rational one() {
        return Rational.ONE;
    }

}
