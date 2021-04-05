/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.ListIterator;
import java.util.Optional;

/**
 *
 * @author masao
 */
public class OnNodeIterator<T> {
    final public ListIterator<T> body;
    boolean next;
    Optional<T> now;
    public OnNodeIterator(ListIterator<T> body) {
        this.body=body;
        this.next=false;
        this.now=Optional.empty();
    }
    public Optional<T> get() {
        return now;
    }
    public OnNodeIterator<T> next() {
        if (!next && now.isPresent() && body.hasNext()) body.next();
        next=true;
        this.now=body.hasNext()?Optional.of(body.next()):Optional.empty();
        return this;
    }
    public OnNodeIterator<T> prev() {
        if (next && now.isPresent() && body.hasPrevious()) body.previous();
        next=false;
        this.now=body.hasPrevious()?Optional.of(body.previous()):Optional.empty();
        return this;
    }
}
