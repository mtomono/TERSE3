/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.function.Predicate;

/**
 *
 * @author masao
 */
abstract public class Lexer extends Source<String,Token> {
    protected Token next;
    protected boolean hasNext;
    abstract public boolean isIgnored(Token token);
    abstract public Token nextToken() throws ParseException;
    static public Parser<String,Token,Token> is(Predicate<Token> pred) {
        return s->{
            Token retval=s.peek();
            if (!pred.test(retval))
                throw new ParseException(s.explain("Reached unexpected item :"+retval));
            return retval;
        };
    }
    public Lexer(String src) {
        super(src);
        hasNext=false;
    }
    @Override
    public Token peek() throws ParseException {
        if (!hasNext())
            throw new ParseException("reached end of source");
        return next();
    }
    public boolean hasNext() throws ParseException {
        hasNext=false;
        findNext();
        return hasNext;
    }
    public Token next() throws ParseException {
        if (!hasNext&&!hasNext())
            throw new ParseException("no next symbol");
        pos=next.end;
        return next;            
    }
    public void findNext() throws ParseException {
        Token retval;
        while (pos<src.length()) {
            if(isIgnored(retval=nextToken())) 
                ignore(retval);
            else {
                nextFound(retval);
                return;
            }
        }
    }
    public void nextFound(Token next) {
        hasNext=true;
        this.next=next;
    }
    public void ignore(Token next) {
        pos=next.end;
    }
    @Override
    public String sub(int... sec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Token get(int at) throws ParseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String rest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void forward(int at) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
