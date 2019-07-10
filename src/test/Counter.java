/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import collection.P;
import collection.TList;
import java.util.List;
import parser.ListSource;
import parser.Parenthesis;
import parser.ParseException;

/**
 *
 * @author masao
 */
public class Counter {
    public class Line {
        final public String mark;
        long time;
        int number;
        public Line(String mark, long time, int number) {
            this.mark = mark;
            this.time = time;
            this.number = number;
        }
        public String toString() {
            return "{"+mark+","+time+","+number+"}";
        }
    }
    final public TList<Line> times;
    int counter;
    public Counter() {
        this.times = TList.c();
        this.counter = 0;
    }
        
    public void record(String mark) {
        times.add(new Line(mark,System.currentTimeMillis(),counter++));
    }
    
    public int count(String mark) {
        return times.filter(p->p.mark.equals(mark)).size();
    }
    
    public TList<List<Integer>> toRange(String start, String end) {
        try {
            TList<List<Integer>> retval = TList.c();
            new Parenthesis<List<Line>,Line>(p->p.mark.equals(start), p->p.mark.equals(end), retval).content.parse(new ListSource<>(times));
            return retval;
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
