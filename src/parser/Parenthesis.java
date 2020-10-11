/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import collection.TList;
import java.util.List;
import java.util.function.Predicate;
import static parser.Parser.seq;
import static parser.Parsers.not;
import static parser.Parsers.satisfy;

/**
 *
 * @author masao
 */
public class Parenthesis<S,T> {
    public static final <S,T> Parser<S,T,T> parenthesis(Parser<S,T,T> content, Predicate<T> start, Predicate<T> end) {
        return seq(not(start.or(end)),satisfy(start),content,satisfy(end));
    };
    
    public static final <S,T> Parser<S,T,T> content(Parser<S,T,T> parenthesis, Predicate<T> start, Predicate<T> end) {
        return seq(not(start.or(end)),seq(parenthesis,not(start.or(end))).many());
    }
    
    final public PlaceHolder<S,T,T> parenthesis;
    final public Parser<S,T,T> content;
    
    public Parenthesis(Predicate<T> start, Predicate<T> end, TList<List<Integer>> retval) {
        this.parenthesis = new PlaceHolder<>();
        this.content = content(parenthesis, start, end);
        this.parenthesis.set(parenthesis(content, start, end).sec().apply(s->retval.addOne(s).transform(w->null)));
    }
    
    public Parenthesis(T start, T end, TList<List<Integer>> retval) {
        this(c->c.equals(start),c->c.equals(end),retval);
    }
}
