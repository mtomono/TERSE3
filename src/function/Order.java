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

import java.util.Comparator;
import java.util.function.Function;

/**
 *
 * @author masao
 * @param <T>
 */
public interface Order<T> extends Comparator<T>{
    default T min(T a, T b) {return lt(a,b)?a:b;}
    default T max(T a, T b) {return gt(a,b)?a:b;}
    public int compare(T a, T b);
    public boolean eq(T a, T b);
    public boolean ne(T a, T b);
    public boolean lt(T a, T b);
    public boolean le(T a, T b);
    public boolean gt(T a, T b);
    public boolean ge(T a, T b);
}
