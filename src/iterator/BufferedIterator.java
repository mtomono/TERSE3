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
 */
public class BufferedIterator<T> extends AbstractBufferedIterator<T> {
    Iterator<T> body;
    public BufferedIterator(Iterator<T> body) {
        this.body=body;
    }
    @Override
    protected void findNext() {
        if (body.hasNext())
            nextFound(body.next());
    }
    
}
