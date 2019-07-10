/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author masao
 */
public class PlaceHolder<S,T,U> implements Parser<S,T,U> {
    Parser<S,T,U> body = s->{throw new RuntimeException("Not set yet.");};

    public PlaceHolder set(Parser<S,T,U> body) {
        this.body=body;
        return this;
    }
    @Override
    public U parse(Source<S, T> s) throws ParseException {
        return this.body.parse(s);
    }
    
}
