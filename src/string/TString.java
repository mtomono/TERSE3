/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import collection.TList;
import function.Transformable;
import function.Wrapper;

/**
 *
 * @author masao
 */
public class TString implements Wrapper<String,TString>,Transformable<TString> {
    final public String body;
    public TString(String body) {
        this.body=body;
    }
    public TString self() {
        return this;
    }
    public String body() {
        return body;
    }
    public TString wrap(String body) {
        return new TString(body);
    }
    public TList<TString> split(String regex) {
        return TList.sof(body.split(regex)).map(s->new TString(s));
    }
    public TList<TString> lines() {
        return split("\n");
    }
    public TList<TList<TString>> cells(String regex) {
        return lines().map(l->l.split(regex));
    }
    public TString concat(TList<String> strs) {
        StringBuilder sb = new StringBuilder(body);
        strs.stream().forEachOrdered(s->sb.append(s));
        return new TString(sb.toString());
    }
    public String toString() {
        return body;
    }
    @Override
    public boolean equals(Object e) {
        if (e == null) {
            return false;
        }
        if (!(e instanceof TString)) {
            return false;
        }
        TString t = (TString) e;
        return t.body.equals(body);
    }
}
