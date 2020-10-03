/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author masao
 */
public class Token {
    final public int type;
    final public int start;
    final public int end;
    public Token(int type,int start,int end) {
        this.type=type;
        this.start=start;
        this.end=end;
    }
    public String toString() {
        return String.format("(%d %d %d)", type,start,end);
    }
}
