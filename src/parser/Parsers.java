/*
 Copyright 2017, 2018 Masao Tomono

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and limitations under the License.
 */

package parser;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static parser.Parser.reduce;
import static parser.Parser.seq;
import static parser.Parser.tor;

/**
 *
 * @author masao
 */
public class Parsers {
    public static <T> void parseTest(Parser p, String src) {
        StrSource s = new StrSource(src);
        try {
            System.out.println(s.sub(p.sec(s)));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static final <S, T> Parser<S, T, T> satisfy(Predicate<T> f) {
        return s-> {
            if (!f.test(s.peek()))
                throw new ParseException(s.explain("Reached unexpected item"));
            s.pos++;
            return null;
        };
    }
    
    public static final <S, T> Parser<S, T, T> any() {
        return satisfy(s->true);
    }
    
    public static final Parser<String, Character, Character> anyChar = any();
    
    public static final Parser<String, Character, Character> chr(char ch) {
        return satisfy(c->c==ch);
    }
    
    public static final Parser<String, Character, Character> in(String str) {
        return satisfy(c->str.indexOf(c)!=-1);
    }
    
    public static final boolean isAlphaNum(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch);
    }
    
    public static final boolean isAlpha(char ch) {
        return Character.isAlphabetic(ch);
    }
    
    public static final Parser<String, Character, Character> digit= satisfy(Character::isDigit);
    public static final Parser<String, Character, Character> upper = satisfy(Character::isUpperCase);
    public static final Parser<String, Character, Character> lower = satisfy(Character::isLowerCase);
    public static final Parser<String, Character, Character> alphaNum = satisfy(Parsers::isAlphaNum);
    public static final Parser<String, Character, Character> letter = satisfy(Character::isLetter);
    public static final Parser<String, Character, Character> alpha = satisfy(Parsers::isAlpha);
    public static final Parser<String, Character, Character> dot = chr('.');
    public static final Parser<String, Character, Character> space = chr(' ');
    public static final Parser<String, Character, Character> tab = chr('\t');
    public static final Parser<String, Character, Character> cr = chr('\n');
    public static final Parser<String, Character, Character> comma = chr(',');
    public static final Parser<String, Character, Character> semicolon = chr(';');
    public static final Parser<String, Character, Character> underbar = chr('_');
            
    /**
     * parser factory for string like structure.
     * still leaving the possibility for char to Object encoding.
     * for the real string matching, use str()
     * @param <S>
     * @param <T>
     * @param <U>
     * @param str
     * @param f
     * @return 
     */
    public static final <S, T, U> Parser<S, T, U> string(String str, Function<Character, Parser<S, T, U>> f) {
        return s-> {
            for (int i = 0; i < str.length(); ++i) {
                f.apply(str.charAt(i)).parse(s);
            }
            return null;
        };
    }
    
    public static final Parser<String, Character, String> str(String str) {
        return string(str, c->chr(c)).l();
    }
    
    public static final Parser<String, Character, String> regex(String regex) {
        Pattern p = Pattern.compile(regex);
        return s->{
            Matcher m = p.matcher(s.src);
            if(!m.find(s.pos)||m.start()!=s.pos)
                throw new ParseException("lex : regex didn't matched");
            s.pos=m.end();
            return m.group();
        };
    }

    public static <S,T> Parser<S,T,T> not(Predicate<T> p) {
        return Parsers.<S,T>any().t().except(p).tr().many();
    }
    public static <S,T> Parser<S,T,S> notL(Predicate<S> p) {
        return Parsers.<S,T>any().l().except(p).tr().many();
    }
    public static <S,T> Parser<S,T,T> not(T c) {
        return Parsers.not(x->x.equals(c));
    }
    public static <S,T> Parser<S,T,S> notL(S s) {
        return Parsers.notL(x->x.equals(s));
    }
    
    public static final Parser<String, Character, String> integerStr = Parser.seq(chr('-').upto(1), digit.than(1)).l();
    public static final Parser<String, Character, String> integerStrDelimited = Parser.seq(chr('-').upto(1), digit.tor(comma)).than(1).l();
    public static final Parser<String, Character, Integer> integer = integerStr.apply(s->Integer.parseInt(s));
    public static final Parser<String, Character, String> numberStr = Parser.seq(integerStr, seq(dot, digit.many()).many(0,1).l()).l();
    public static final Parser<String, Character, String> numberStrDelimited = Parser.seq(integerStrDelimited, seq(dot, digit.many()).many(0,1).l()).l();
    public static final Parser<String, Character, Double> number = numberStr.apply(s->Double.parseDouble(s));
    public static final Parser<String, Character, String> spaces = space.tor(tab).tor(cr).many().l();
    public static final Parser<String, Character, Character> octal = digit.t().accept(c->c<'8');
    public static final Parser<String, Character, Character> hex = tor(digit, alpha.t().apply(c->Character.toLowerCase(c)).accept(c->'a'<=c&&c<='f'));
    public static final Parser<String, Character, Character> esc = chr('\\').next(tor(
                            chr('t').apply(s->'\t'),
                            chr('n').apply(s->'\n'),
                            chr('\'').apply(s->'\''),
                            chr('\"').apply(s->'\"'), 
                            chr('\\').apply(s->'\\'),
                            chr('b').apply(s->'\b'),
                            chr('r').apply(s->'\r'),
                            chr('f').apply(s->'\f'),
                            octal.rep(3).l().apply(s->(char)Integer.parseInt(s, 8)),
                            chr('u').next(hex.rep(4).l().apply(s->(char)Integer.parseInt(s, 16)))
                        ));
    public static final Parser<String, Character, Character> escTab = Parser.seq(chr('\\'), chr('t')).apply(s->'\t');
    public static final Parser<String, Character, Character> escBackSlash = Parser.seq(chr('\\'), chr('\\')).apply(s->'\\');
    public static final Parser<String, Character, Character> escNewLine = Parser.seq(chr('\\'), chr('n')).apply(s->'\n');
    public static final Parser<String, Character, Character> escQuote = Parser.seq(chr('\\'), chr('\'')).apply(s->'\'');
    public static final Parser<String, Character, Character> escDoubleQuote = Parser.seq(chr('\\'), chr('\"')).apply(s->'\"');
    public static final Parser<String, Character, Character> escapes = tor(escBackSlash, escTab, escNewLine, escQuote, escDoubleQuote);
    public static final Parser<String, Character, Character> charsInSingleQuote = tor(esc, anyChar.t().except(c->c==('\'')));
    public static final Parser<String, Character, Character> charsInDoubleQuote = tor(esc, anyChar.t().except(c->c==('\"')));
    public static final Parser<String, Character, String> wholeDoubleQuote = chr('\"').next(charsInDoubleQuote.many()).prev(chr('\"')).l();
    public static final Parser<String, Character, String> wholeSingleQuote = chr('\'').next(charsInSingleQuote.many()).prev(chr('\'')).l();
    public static final Parser<String, Character, String> doubleQuote = chr('\"').next(charsInDoubleQuote.reduce(()->new StringBuilder(),(a,b)->a.append(b)).apply(sb->sb.toString())).prev(chr('\"'));
    public static final Parser<String, Character, String> singleQuote = chr('\'').next(charsInSingleQuote.reduce(()->new StringBuilder(),(a,b)->a.append(b)).apply(sb->sb.toString())).prev(chr('\''));

    //----- formula -------------
    
    public static final <U> Parser<String, Character, U> s(Parser<String, Character, U> p) {
        return spaces.next(p).prev(spaces);
    }
    
    public static final Parser<String, Character, Double> factor = new Parser<String, Character, Double>() {
        @Override
        public Double parse(Source<String, Character> s) throws ParseException {
            return factor_.parse(s);
        }
    };

    public static final Parser<String, Character, Double> term = 
        factor.reduce(tor(
                chr('*').next(factor.apply((u, v)->u*v)), 
                chr('/').next(factor.apply((u, v)->u/v))
            ));
    
    public static final Parser<String, Character, Double> expr =
        term.reduce(tor(
                chr('+').next(term.apply((u, v)->u+v)), 
                chr('-').next(term.apply((u, v)->u-v))
            ));    
    
    public static final Parser<String, Character, Double> factor_ =
            spaces.next(tor(chr('(').next(expr).prev(chr(')')), number)).prev(spaces);
}
