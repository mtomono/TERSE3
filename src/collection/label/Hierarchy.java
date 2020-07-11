/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.label;

import collection.TList;
import java.util.function.BiConsumer;

/**
 *
 * @author masao
 */
public class Hierarchy<T> {
    final public TList<Integer> hierarchy;
    final public BiConsumer<Hierarchy,T> process;
    final public int start;
    static public <T>Hierarchy<T> create(int start, BiConsumer<Hierarchy,T> process) {
        return new Hierarchy<>(TList.empty(),start-1,process);
    }
    public Hierarchy(TList<Integer> hierarchy, int start, BiConsumer<Hierarchy,T> process) {
        this.hierarchy=hierarchy;
        this.process=process;
        this.start=start;
    }
    public Hierarchy create(TList<Integer> hierarchy) {
        return new Hierarchy(hierarchy,start, process);
    }
    public Hierarchy up() {
        return create(hierarchy.seek(-1));
    }
    public Hierarchy down() {
        return create(hierarchy.append(start));
    }
    public Hierarchy next() {
        return create(hierarchy.seek(-1).append(hierarchy.last()+1));
    }
    public Hierarchy put(T t) {
        Hierarchy retval=next();
        process.accept(retval, t);
        return retval;
    }
    public int indent() {
        return hierarchy.size()-1;
    }
    @Override
    public String toString() {
        return hierarchy.toString();
    }
}
