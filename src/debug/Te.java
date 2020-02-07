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

package debug;

import java.util.function.Function;

/**
 * Tee
 * 
 * @author masao
 */
public class Te {
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
    public static <T> T e(Function<T,String> f, T value) {
        System.out.println(f.apply(value));
        return value;
    }
    public static <T> T e(String label,T value) {
        return e(v->label+v, value);
    }
}
