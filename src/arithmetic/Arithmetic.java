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
package arithmetic;

/**
 *
 * @author mtomono
 */
public class Arithmetic {
    static public BiOperator add = new Add();
    static public BiOperator sub = new Sub();
    static public BiOperator mul = new Mul();
    static public BiOperator div = new Div();
    static public BiOperator rem = new Rem();
    static public BiOperator mod = new Mod();
    static public BiOperator mods = new ModShift();
    static public BiPredicate eq = new Eq();
    static public BiPredicate ne = new Ne();
    static public BiPredicate lt = new Lt();
    static public BiPredicate gt = new Gt();
    static public BiPredicate le = new Le();
    static public BiPredicate ge = new Ge();
    static public UniOperator abs = new Abs();
    static public UniOperator ceil = new Ceil();
    static public UniOperator ceilx = new CeilX();
    static public UniOperator floor = new Floor();
}
