/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

import java.util.AbstractList;

/**
 *
 * @author masao
 */
public class TransparentTranspose<T> extends AbstractList<TList<T>> {
    TList<TList<T>> body;
    public static <T> TList<TList<T>> transpose(TList<TList<T>> target) {
        return TList.set(new TransparentTranspose<>(target));
    }
    TransparentTranspose(TList<TList<T>> body) {
        this.body=body;
    }
    @Override
    public TList<T> get(int index) {
        return TList.set(new Column(index));
    }
    @Override
    public int size() {
        return body.isEmpty()?0:body.get(0).size();
    }
    class Column extends AbstractList<T> {
        int i;
        public Column(int i) {
            this.i=i;
        }
        @Override
        public T get(int index) {
            return body.get(index).get(i);
        }
        public T set(int index, T o) {
            return body.get(index).set(i, o);
        }
        @Override
        public int size() {
            return body.size();
        }
    }
}
