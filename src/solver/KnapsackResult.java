/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import collection.TList;

/**
 *
 * @author masao
 * @param <T>
 */
public class KnapsackResult {
    final TList<Integer> content;
    final int value;
    public KnapsackResult() {
        this(TList.c(), 0);
    }
    
    public KnapsackResult(TList<Integer> content, int value) {
        this.content = content;
        this.value = value;
    }
    
    public KnapsackResult add(int addedIndex, int addedValue) {
        return new KnapsackResult(content.append(TList.wrap(addedIndex)), value+addedValue);
    }
}
