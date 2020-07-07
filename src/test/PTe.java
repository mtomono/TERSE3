/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package test;

import java.util.function.Function;

/**
 * Permanent Tee.
 * debug.Te was implemented for debugging.
 * because of its nature, they should not stay in program permanently.
 * but for some testing, the function found to be very handy.
 * thus, this class.
 * 
 * @author masao
 */
public class PTe {
    public static double e(double value) {
        System.out.println(value);
        return value;
    }
    public static int e(int value) {
        System.out.println(value);
        return value;
    }
    public static long e(long value) {
        System.out.println(value);
        return value;
    }
    public static boolean e(boolean value) {
        System.out.println(value);
        return value;
    }
    public static char e(char value) {
        System.out.println(value);
        return value;
    }
    public static <T> T e(T value) {
        System.out.println(value);
        return value;
    }
    public static <T> T ex(T value) {
        return value;
    }
    public static <T> T e(T value, Function<T,String> f) {
        System.out.println(f.apply(value));
        return value;
    }
    public static <T> T ex(T value, Function<T,String> f) {
        return value;
    }
    public static <T> T e(T value, String label) {
        return e(value, v->label+v);
    }
    public static <T> T ex(T value, String label) {
        return value;
    }
    public static <T> T e(T value, String label, Function<T,String> f) {
        return e(value, v->label+f.apply(v));
    }
    public static <T> T ex(T value, String label, Function<T,String> f) {
        return value;
    }
}
