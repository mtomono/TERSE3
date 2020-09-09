/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import collection.TList;
import java.util.function.Function;

/**
 *
 * @author masao
 */
public class Clock {
    TList<Long> time;
    TList<String> label;
    public Clock() {
        this.time=TList.c();
        this.label=TList.c();
    }
    public Clock record(String label) {
        this.time.add(System.currentTimeMillis());
        this.label.add(label);
        return this;
    }
    public Clock record() {
        return record("["+label.size()+"]");
    }
    public Clock show() {
        System.out.println(label.last());
        return this;
    }
    public <T> Function<T,String> lastLap() {
        return v->length(time.last(1),time.last())+v;
    }
    public String length(long from, long to) {
        return "["+(to-from)+"ms]";
    }
    public String laps() {
        return time.diff((a,b)->length(a,b)).pair(label.seek(1),(a,b)->a+b).toWrappedString();
    }
    public String total() {
        return length(time.get(0),time.last());
    }
}
