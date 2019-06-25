/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author masao
 */
public abstract class LineFilter {
    Scanner in;
    PrintStream out;
    public LineFilter() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }
    abstract public String processLine(String line);
    
    public void process() {
        while(in.hasNext())
            out.println(processLine(in.nextLine()));
    }
}
