/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

/**
 *
 * @author masao
 */
public class ArrayInt2 {
    int[][] body;
    public ArrayInt2(int[][] body) {
        this.body=body;
    }
    public int get(int i, int j) {
        return body[i][j];
    }
    public ArrayInt get(int i) {
        return new ArrayInt.Plain(body[i]);
    }
    public ArrayInt last() {
        return get(body.length-1);
    }
    public ArrayInt last(int i) {
        return get(body.length-1-i);
    }
    public TList<ArrayInt> asList() {
        return TList.sof(body).map(a->new ArrayInt.Plain(a));
    }
    public TList<TList<Integer>> asList2() {
        return asList().map(a->a.asList());
    }
}
