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
public class LinesNFilter {
    Scanner in;
    PrintStream out;
    Function<TList<String>,String> f;
    int lines;
    public LinesNFilter(InputStream in, PrintStream out, int lines, Function<TList<String>,String> f) {
        this.in = new Scanner(in);
        this.out = out;
        this.lines = lines;
        this.f = f;
    }
    public LinesNFilter(int lines,Function<TList<String>,String> f) {
        this(System.in,System.out,lines,f);
    }
    
    public void process() {
        while(in.hasNext()) {
            TList<String> input = TList.c();
            for (int i=0;i<lines;i++)
                if (!in.hasNext())
                    throw new RuntimeException("LinesNFilter : last line ended in the middle of required number of lines.");
                else 
                    input.add(in.nextLine());
            out.println(f.apply(input));
        }
    }
}
