/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterator;

import java.util.Iterator;

/**
 *
 * @author masao
 * @param <T>
 */
public class CullIterator<T> extends AbstractBufferedIterator<T>{
    Iterator<T> body;
    final public int factor;
    public CullIterator(Iterator<T> body, int skip) {
        this.body=body;
        this.factor=skip;
    }
    @Override
    protected void findNext() {
        if (body.hasNext()) nextFound(body.next());
        for (int i=0;i<factor;i++) if (!body.hasNext()) return; else body.next();
    }
}
