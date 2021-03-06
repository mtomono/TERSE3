/*
 Copyright 2017, 2018, 2019, 2020 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
import debug.Te;
import static java.lang.Integer.max;
import java.util.function.Consumer;

/**
 *
 * @author masao
 */
public class KnapsackDPa {
    @FunctionalInterface
    static public interface Row {
        void row(int i,ArrayInt r,ArrayInt u);
    }
    int items;
    int capacity;
    public final ArrayInt2 dp;
    Row solve;
    public KnapsackDPa(int items,int capacity) {
        this.items=items+1;
        this.capacity=capacity;
        this.dp=new ArrayInt2(new int[items+1][capacity+1]);
    }
    public KnapsackDPa row(Row row) {
        this.solve=row;
        return this;
    }
    public KnapsackDPa init(Consumer<ArrayInt2> init) {
        init.accept(dp);
        return this;
    }
    public ArrayInt2 solvePackages() {
        ArrayInt.range(1, items).forEach(i->solve.row(i,dp.get(i-1),dp.get(i).reset(dp.get(i-1))));
        return dp;
    }
    public ArrayInt2 solveCoin() {
        ArrayInt.range(1, items).forEach(i->solve.row(i,dp.get(i).reset(dp.get(i-1)),dp.get(i)));
        return dp;
    }
    static public KnapsackDPa knapsack(ArrayInt weight,ArrayInt value,int capacity) {
        return new KnapsackDPa(weight.length(),capacity)
                .row((i,r,u)->{
                    int w=weight.get(i-1);
                    int v=value.get(i-1);
                    ArrayInt.range(w, capacity+1).forEach(j->u.set(j,max(r.get(j),r.get(j-w)+v)));
                })
                .init(a->a.get(0).setAll(0));
    }
    static public KnapsackDPa psumCount(ArrayInt numbers,int capacity) {
        return new KnapsackDPa(numbers.length(),capacity)
                .row((i,r,u)->{
                    int x=numbers.get(i-1);
                    ArrayInt.range(x, capacity+1).forEach(j->u.set(j, r.get(j)+r.get(j-x)));
                })
                .init(a->a.get(0).setAll(0).set(0,1));
    }
    static public KnapsackDPa psumShortest(ArrayInt numbers,int capacity) {
        return new KnapsackDPa(numbers.length(),capacity)
                .row((i,r,u)->{
                    int x=numbers.get(i-1);
                    ArrayInt.range(x, capacity+1).forEach(j->arrayInt(r.get(j),r.get(j-x)+1).filterIter(k->k>0).checkEmpty(it->it.min(k->k)).ifPresent(k->u.set(j, k)));
                })
                .init(a->a.get(0).setAll(-1).set(0,0));
    }
    static public KnapsackDPa csumLimited(ArrayInt numbers, ArrayInt limits, int capacity) {
        return new KnapsackDPa(numbers.length(),capacity)
                .row((i,r,u)->{
                    int x=numbers.get(i-1);
                    int l=limits.get(i-1);
                    ArrayInt.range(0,capacity+1).filterIter(j->r.get(j)>=0).forEach(j->u.set(j,l));
                    ArrayInt.range(x,capacity+1).filterIter(j->!(r.get(j)>=0)).filter(j->u.get(j-x)>0).forEach(j->u.set(j,u.get(j-x)-1));
                })
                .init(a->a.get(0).setAll(-1).set(0, 0));
    }
    static public KnapsackDPa lis(ArrayInt numbers) {
        return new KnapsackDPa(numbers.length()-1,numbers.length()-1)
                .row((i,r,u)->ArrayInt.range(0, i).forEach(j->u.set(i, numbers.get(j)<numbers.get(i)?max(u.get(i),r.get(j)+1):u.get(i))))
                .init(a->a.get(0).setAll(1));
    }
}
