/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z;

/**
 *
 * @author masao
 */
public class NullToString {
    static public void main(String[] args) {
        String o = null;
        String result = o.toString();
        System.out.println(result);
    }
}
