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

import function.Op;
import java.math.BigDecimal;

/**
 * Operations for Double.
 * @author masao
 */
public class StringOp implements Op<String> {
    @Override
    public String b(int v) {
        return Integer.toString(v);
    }

    @Override
    public String b(long v) {
        return Long.toString(v);
    }

    @Override
    public String b(float v) {
        return Float.toString(v);
    }

    @Override
    public String b(double v) {
        return Double.toString(v);
    }

    @Override
    public String b(String v) {
        return v;
    }
    
    @Override
    public String b(BigDecimal n) {
        return n.toString();
    }

    @Override
    public String b(Rational n) {
        return n.toString();
    }


    @Override
    public String add(String v0, String v1) {
        return v0+"+"+v1;
    }

    @Override
    public String sub(String v0, String v1) {
        return v0+"-"+v1;
    }

    @Override
    public String mul(String v0, String v1) {
        return v0+"*"+v1;
    }

    @Override
    public String div(String v0, String v1) {
        return v0+"/"+v1;
    }

    @Override
    public String negate(String v0) {
        return "-1*"+v0;
    }

    @Override
    public String abs(String v0) {
        return "|"+v0+"|";
    }

    @Override
    public String sqrt(String v0) {
        return "sqrt("+v0+")";
    }

    @Override
    public String zero() {
        return "0";
    }

    @Override
    public String one() {
        return "1";
    }

    @Override
    public String sign(String v0) {
        return "sgn("+v0+")";
    }

    @Override
    public String sin(String v0) {
        return "sin("+v0+")";
    }

    @Override
    public String cos(String v0) {
        return "cos("+v0+")";
    }

    @Override
    public String tan(String v0) {
        return "tan("+v0+")";
    }

    @Override
    public String log(String v0) {
        return "log("+v0+")";
    }

    @Override
    public String log10(String v0) {
        return "log10("+v0+")";
    }
}
