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
 */
public class SearchResult {
    final TList<Integer> content;
    final int value;

    public SearchResult() {
        this(TList.c(), 0);
    }
    
    public SearchResult(TList<Integer> content, int value) {
        this.content = content;
        this.value = value;
    }

    public SearchResult add(int addedIndex, int addedValue) {
        return new SearchResult(content.append(TList.wrap(addedIndex)), value+addedValue);
    }
    
}
