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

/**
 *
 * @author masao
 */
public class WholeFilter {
    Scanner in;
    PrintStream out;
    Function<TList<String>,String> f;
    public WholeFilter(InputStream in, PrintStream out, Function<TList<String>,String> f) {
        this.in = new Scanner(in);
        this.out = out;
        this.f = f;
    }
    public WholeFilter(Function<TList<String>,String> f) {
        this(System.in,System.out,f);
    }
    
    public void process() {
        TList<String> input = TList.c();
        while(in.hasNext())
            input.add(in.nextLine());
        out.println(f.apply(input));
    }
}
