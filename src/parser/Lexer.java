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
abstract public class Lexer extends Source<String,Token> {
    protected Token next;
    protected boolean hasNext;
    abstract public void findNext() throws ParseException;
    public Lexer(String src) {
        super(src);
        hasNext=false;
    }
    public boolean hasNext() throws ParseException{
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
    public Token nextNoSeek() throws ParseException {
        if (!hasNext)
            throw new ParseException("no next symbol");
        return next;            
    }
    public void nextFound(Token next) {
        hasNext=true;
        this.next=next;
    }
    public void ignore(Token next) {
        pos=next.end;
    }
    @Override
    public void seek() {
        if (hasNext)
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
