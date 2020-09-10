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
}
