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

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

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
            s.seek();
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
    
    
    public static final <S, T, U> Parser<S, T, U> sequence(Parser<S, T, U>... args) {
        return s-> {
            for (Parser arg : args) 
                arg.parse(s);
            return null;
        };
    }
    
    public static final <S, T, U> Parser<S, T, U> seq(Parser<S, T, U>... args) {
        return sequence(args);
    }
    
    public static final <S, T, U> Parser<S, T, U> replicate(int n, Parser<S, T, U> p) {
        return s-> {
            for(int i = 0; i < n; ++i) {
                p.parse(s);
            }
            return null;
        };
    }
    
    public static final <S, T, U> Parser<S, T, U> rep(int n, Parser<S, T, U> p) {
        return replicate(n, p);
    }
    
    public static final <S, T, U> Parser<S, T, U> many(Parser<S, T, U> p) {
        return s-> {
            try {
                for (;;) {
                    p.parse(s);
                }
            } catch (ParseException e) {
            }
            return null;
        };
    }
    
    public static final <S, T, U> U reduce(Source<S, T> s, U start, Parser<S, T, Function<U, U>> p) {
        try {
            return reduce(s, p.parse(s).apply(start), p);
        } catch (ParseException e) {
            return start;
        }
    }
    
    public static final <S, T, U> Parser<S, T, U> reduce(Supplier<U> start, Parser<S, T, Function<U, U>> p) {
        return s-> reduce(s, start.get(), p);
    }
    
    /**
     * p reduce
     * reduce in which all the values (initial values and step values) are collected
     * from parser.
     * @param <S>
     * @param <T>
     * @param <U>
     * @param start
     * @param p
     * @return 
     */
    public static final <S, T, U> Parser<S, T, U> preduce(Parser<S, T, U> start, Parser<S, T, Function<U, U>> p) {
        return s-> {
            U init = start.parse(s);
            return reduce(s, init, p);
        };
    }
    
    public static final <S, T, U> Parser<S, T, U> many(int min, Parser<S, T, U> p) {
        return sequence(replicate(min, p), many(p));
    }
    
    public static final <S, T, U> Parser<S, T, U> upto(int max, Parser<S, T, U> p) {
        return s-> {
            try {
                for (int i=0; i<max; i++) {
                    p.parse(s);
                }
            } catch (ParseException e) {
            }
            return null;
        };
    }
    
    public static final <S, T, U> Parser<S, T, U> manyUpto(int min, int max, Parser<S, T, U> p) {
        return sequence(replicate(min, p), upto(max-min, p));
    }
    
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
    
    public static final Parser<String, Character, String> lex(String regex) {
        Pattern p = Pattern.compile("^"+regex);
        return s->{
            Matcher m = p.matcher(s.rest());
            if(!m.find())
                throw new ParseException("lex : regex didn't matched");
            s.forward(m.end());
            return m.group();
        };
    }
                
    public static final <S, T, U> Parser<S, T, U> or(Parser<S, T, U>... ps) {
        return s-> {
            ParseException thrown = new ParseException("Or is empty.");
            for (Parser<S, T, U> p : ps) {
                Source<S, T> bak = s.clone();
                try {
                    return p.parse(s);
                } catch (ParseException e) {
                    thrown = e;
                    if (!s.equals(bak)) {
                        throw e;
                    }
                }
            }
            throw thrown;
        };
    }
    
    public static final <S, T, U> Parser<S, T, U> tor(Parser<S, T, U>... ps) {
        return or(Arrays.asList(ps).stream().map(p->p.tr()).collect(toList()).toArray(ps));
    }
    
    public static <S,T> Parser<S,T,T> not(Predicate<T> p) {
        return many(Parsers.<S,T>any().t().except(p).tr());
    }
    public static <S,T> Parser<S,T,S> notL(Predicate<S> p) {
        return many(Parsers.<S,T>any().l().except(p).tr());
    }
    public static <S,T> Parser<S,T,T> not(T c) {
        return Parsers.not(x->x.equals(c));
    }
    public static <S,T> Parser<S,T,S> notL(S s) {
        return Parsers.notL(x->x.equals(s));
    }
    
    public static final Parser<String, Character, String> integerStr = seq(upto(1, chr('-')), many(1, digit)).l();
    public static final Parser<String, Character, String> integerStrDelimited = seq(upto(1, chr('-')), many(1, digit.or(comma))).l();
    public static final Parser<String, Character, Integer> integer = integerStr.apply(s->Integer.parseInt(s));
    public static final Parser<String, Character, String> numberStr = seq(integerStr, manyUpto(0, 1, sequence(dot, many(digit))).l()).l();
    public static final Parser<String, Character, String> numberStrDelimited = seq(integerStrDelimited, manyUpto(0, 1, sequence(dot, many(digit))).l()).l();
    public static final Parser<String, Character, Double> number = numberStr.apply(s->Double.parseDouble(s));
    public static final Parser<String, Character, String> spaces = many(space.or(tab).or(cr)).l();
    public static final Parser<String, Character, Character> octal = digit.t().accept(c->c<'8');
    public static final Parser<String, Character, Character> hex = or(digit, alpha.t().apply(c->Character.toLowerCase(c)).accept(c->'a'<=c&&c<='f'));
    public static final Parser<String, Character, Character> esc = chr('\\').next(or(
                            chr('t').apply(s->'\t'),
                            chr('n').apply(s->'\n'),
                            chr('\'').apply(s->'\''),
                            chr('\"').apply(s->'\"'), 
                            chr('\\').apply(s->'\\'),
                            chr('b').apply(s->'\b'),
                            chr('r').apply(s->'\r'),
                            chr('f').apply(s->'\f'),
                            rep(3, octal).l().apply(s->(char)Integer.parseInt(s, 8)),
                            chr('u').next(rep(4, hex).l().apply(s->(char)Integer.parseInt(s, 16)))
                        ));
    public static final Parser<String, Character, Character> escTab = seq(chr('\\'), chr('t')).apply(s->'\t');
    public static final Parser<String, Character, Character> escBackSlash = seq(chr('\\'), chr('\\')).apply(s->'\\');
    public static final Parser<String, Character, Character> escNewLine = seq(chr('\\'), chr('n')).apply(s->'\n');
    public static final Parser<String, Character, Character> escQuote = seq(chr('\\'), chr('\'')).apply(s->'\'');
    public static final Parser<String, Character, Character> escDoubleQuote = seq(chr('\\'), chr('\"')).apply(s->'\"');
    public static final Parser<String, Character, Character> escapes = tor(escBackSlash, escTab, escNewLine, escQuote, escDoubleQuote);
    public static final Parser<String, Character, Character> charsInSingleQuote = tor(esc, anyChar.t().except(c->c==('\'')));
    public static final Parser<String, Character, Character> charsInDoubleQuote = tor(esc, anyChar.t().except(c->c==('\"')));
    public static final Parser<String, Character, String> wholeDoubleQuote = chr('\"').next(many(charsInDoubleQuote)).prev(chr('\"')).l();
    public static final Parser<String, Character, String> wholeSingleQuote = chr('\'').next(many(charsInSingleQuote)).prev(chr('\'')).l();
    public static final Parser<String, Character, String> doubleQuote = chr('\"').next(reduce(()->new StringBuilder(), charsInDoubleQuote.apply(c->(sb->sb.append(c)))).apply(sb->sb.toString())).prev(chr('\"'));
    public static final Parser<String, Character, String> singleQuote = chr('\'').next(reduce(()->new StringBuilder(), charsInSingleQuote.apply(c->(sb->sb.append(c)))).apply(sb->sb.toString())).prev(chr('\''));

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
        preduce(factor, or(
                chr('*').next(factor.apply((u, v)->u*v)), 
                chr('/').next(factor.apply((u, v)->u/v))
            ));
    
    public static final Parser<String, Character, Double> expr =
        preduce(term, or(
                chr('+').next(term.apply((u, v)->u+v)), 
                chr('-').next(term.apply((u, v)->u-v))
            ));    
    
    public static final Parser<String, Character, Double> factor_ =
            spaces.next(or(chr('(').next(expr).prev(chr(')')), number)).prev(spaces);
}
