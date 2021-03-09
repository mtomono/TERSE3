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
package function;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface IntBiPredicate {
    public boolean test(int a, int b);
    static public IntBiPredicate lt=(a,b)->a<b;
    static public IntBiPredicate gt=(a,b)->a>b;
    static public IntBiPredicate le=(a,b)->a<=b;
    static public IntBiPredicate ge=(a,b)->a>=b;
    static public IntBiPredicate eq=(a,b)->a==b;
    static public IntBiPredicate ne=(a,b)->a!=b;
}
