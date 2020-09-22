/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import collection.ArrayInt;
import static collection.ArrayInt.arrayInt;
import collection.ArrayInt2;
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
                    ArrayInt.range(x, capacity+1).forEach(j->arrayInt(r.get(j),r.get(j-x)).filterIter(k->k>0).check(it->it.min(k->k)).ifPresent(k->u.set(j, k)));
                })
                .init(a->a.get(0).setAll(-1).set(0,0));
    }
}
