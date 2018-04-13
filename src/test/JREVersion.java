/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author masao
 */
public class JREVersion {
    public static String get() {
        return System.getProperty("java.version");
    }
    public static void print() {
        System.out.println(get());
    }
}
