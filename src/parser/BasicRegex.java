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
public class BasicRegex {
    public static String spaces="[ \t\f\r\n]+";
    public static String doubleQuoteStr="\"([^\"]*)\"";
    public static String singleQuoteStr="\'([^\']*)\'";
    public static String integer="[+-]?\\d+";
    public static String decimal="[+-]?(?:\\d+.?\\d*|.\\d+)";
    public static String number="[+-]?(?:\\d+.?\\d*|.\\d+)(?:[eE][+-]?\\d+)?";
}
