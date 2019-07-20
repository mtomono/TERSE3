/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import collection.TList;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;
import system.OneLiner;

/**
 *
 * @author masao
 */
public class LineFilter {
    Scanner in;
    PrintStream out;
    Function<String,String> f;
    public LineFilter(InputStream in, PrintStream out, Function<String,String> f) {
        this.in = new Scanner(in);
        this.out = out;
        this.f = f;
    }
    public LineFilter(Function<String,String> f) {
        this(System.in,System.out,f);
    }
    
    public void process() {
        while(in.hasNext())
            out.println(f.apply(in.nextLine()));
    }
    
    static public LineFilter filter;
    static public void main(String args[]) {
        try(OneLiner shell = new OneLiner()) {
            shell.exec(LineFilter.class.getName()+".filter = new "+LineFilter.class.getName()+"(" + args[0]+")");
            filter.process();
        }
    }
    
}
