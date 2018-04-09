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

import java.util.function.BiFunction;
import java.util.function.Function;
import collection.P;
import static function.TIntSupplier.f;
import java.util.function.Predicate;

/**
 *
 * @author masao
 */
@FunctionalInterface
public interface Parser<S, T, U> {
    U parse(Source<S, T> s) throws ParseException;
    
    /**
     * get parsed section;
     * @param s
     * @return
     * @throws ParseException 
     */
    default int[] sec(Source<S, T> s) throws ParseException {
        int ret[] = new int[2];
        ret[0]=s.pos;
        parse(s);
        ret[1]=s.pos;
        return ret;
    }
    
    default boolean matches(Source<S, T> s) {
        try {
            parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    default Parser<S, T, U> or(Parser<S, T, U> p) {
        return s-> {
            Source<S, T> bak = s.clone();
            try {
                return parse(s);
            } catch (ParseException e) {
                if (!s.equals(bak)) {
                    throw e;
                }
                return p.parse(s);
            }
        };
    }
    
    /**
     * make parser backtrackable.
     * when candidates of "or" has common part in beginning, or repetitive pattern
     * ends with failure, use this to make the parser backtrackable.
     * @return 
     */
    default Parser<S, T, U> tr() {
        return s -> {
            Source<S, T> bak = s.clone();
            try {
                return parse(s);
            } catch (ParseException e) {
                s.revert(bak);
                throw e;
            }
        };
    }
    
    default Parser<S, T, U> tor(Parser<S, T, U> p) {
        return tr().or(p);
    }
    
    default Parser<S, T, U> left(String e) {
        return or(s->{
            throw new ParseException(s.explain(e));
        });
    }
    
    /**
     * element at start
     * @return 
     */
    default Parser<S, T, T> t() {
        return s->{
            T ret = s.get(s.pos);
            parse(s);
            return ret;
        };
    }

    /**
     * literal
     * @return 
     */
    default Parser<S, T, S> l() {
        return s->{
            int start = s.pos;
            parse(s);
            int end = s.pos;
            return s.sub(start, end);
        };
    }
    
    default <V> Parser<S, T, V> apply(Function<U, V> f) {
        return s-> f.apply(parse(s));
    }
    
    default <V> Parser<S, T, Function<V, V>> apply(BiFunction<V, U, V> f) {
        return s-> {
            U x = parse(s);
            return y -> f.apply(y, x);
        };
    }
    
    /**
     * parse dual.
     * if you are so greedy and want to parse a part in two ways, use this method.
     * after parsing, source position goes to the parse end of parameter p.
     * @param <V>
     * @param p
     * @return 
     */
    default <V> Parser<S, T, P<U, V>> dual(Parser<S, T, V> p) {
        return s->{
            Source<S, T> bak = s.clone();
            U l = parse(s);
            s.revert(bak);
            V r = p.parse(s);
            return P.p(l, r);
        };
    }
    
    default Parser<S, T, U> accept(Predicate<U> p) {
        return s-> {
            U x = parse(s);
            if (!p.test(x))
                throw new ParseException("Hit exception");
            return x;
        };
    }
    
    default Parser<S, T, U> except(Predicate<U> p) {
        return accept(p.negate());
    }
    
    /**
     * this can be handy for a simple reduce purpose, but with this reduce, 
     * you cannot switch the functions to apply throughout the reduce process. 
     * if you need to switch them, consult Parsers::reduce;
     * @param <V>
     * @param f
     * @param start
     * @return 
     */
    default <V> Parser<S, T, V> reduce(BiFunction<V, U, V> f, V start) {
        return s-> {
            return reduceBase(s, f, start);
        };
    }
    
    default <V> V reduceBase(Source<S, T> s, BiFunction<V, U, V> f, V start) {
        try {
            return reduceBase(s, f, f.apply(start, parse(s)));
        } catch (ParseException e) {
            return start;
        }
    }
    
    default <V, W> Parser<S, T, W> relay(Parser<S, T, V> p, BiFunction<U, V, W> f) {
        return s-> f.apply(parse(s), p.parse(s));
    }
    
    default <V> Parser<S, T, U> prev(Parser<S, T, V> p) {
        return s-> {
            U ret = parse(s);
            p.parse(s);
            return ret;
        };
    }
    
    default <V> Parser<S, T, V> next(Parser<S, T, V> p) {
        return s-> {
            parse(s);
            return p.parse(s);
        };
    }
    
    default <V> Parser<S, T, P<U, V>> and(Parser<S, T, V> p) {
        return s-> {
            return new P<>(parse(s), p.parse(s));
        };
    }
    
}
