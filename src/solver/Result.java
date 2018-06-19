/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;
import java.util.function.UnaryOperator;

/**
 *
 * @author masao
 */
public class Result<T> {
    final TList<Integer> content;
    final T value;
    
    public Result(T init) {
        this(TList.c(), init);
    }
    
    public Result(TList<Integer> content, T value) {
        this.content = content;
        this.value = value;
    }

    public Result<T> add(int addedIndex, UnaryOperator<T> add) {
        return new Result<>(content.append(TList.wrap(addedIndex)), add.apply(value));
    }
    
}
