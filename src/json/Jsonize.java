/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import collection.TList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import static string.Strings.asString;
import static string.Strings.doubleQuote;
import static string.Strings.singleQuote;

/**
 *
 * @author masao
 */
public interface Jsonize {
    static public Jsonize c() {
        return new Jsonize() {};
    }
    default public <S,T> BiConsumer<StringBuilder,S> value(Function<S,T> f) {
        return (sb, t)->sb.append(f.apply(t));
    }
    default public <T> BiConsumer<StringBuilder,T> string(Function<T,String> f) {
        return simpleString(f);
    }
    default public <T> BiConsumer<StringBuilder,T> simpleString(Function<T,String> f) {
        return (sb, t)->sb.append(doubleQuote(f.apply(t)));
    }
    default public <T> BiConsumer<StringBuilder,T> escapedString(Function<T,String> f) {
        return (sb, t)->sb.append(asString(f.apply(t)));
    }
    default public <T> BiConsumer<StringBuilder,T> chara(Function<T,Character> f) {
        return (sb, t)->sb.append(singleQuote(f.apply(t)));
    }
    default public <S,T> BiConsumer<StringBuilder,S> object(Function<S,T> f, BiConsumer<StringBuilder,T>... cs) {
        return (sb, s)->{
            T t = f.apply(s);
            sb.append('{');
            TList.sof(cs).delimitByValue(c->c, (sx,tx)->sx.append(',')).forEach(c->c.accept(sb,t));
            sb.append('}');
        };
    }
    default public <T> BiConsumer<StringBuilder,T> object(BiConsumer<StringBuilder,T>... cs) {
        return object(o->o, cs);
    }
    default public <S,T> BiConsumer<StringBuilder,Collection<S>> array(Function<S,T> f, BiConsumer<StringBuilder,T> cs) {
        return (sb, ts)->{
            sb.append('[');
            TList.set(ts).<Consumer<StringBuilder>>delimitByValue(t->(s->cs.accept(s, f.apply(t))), s->s.append(',')).forEach(c->c.accept(sb));
            sb.append(']');
        };
    }
    default public <T> BiConsumer<StringBuilder,Collection<T>> array(BiConsumer<StringBuilder,T> cs) {
        return array(o->o, cs);
    }
    default public <T> BiConsumer<StringBuilder, T> attr(String name, BiConsumer<StringBuilder, T> c) {
        return (sb, t)->{
            sb.append(doubleQuote(name));
            sb.append(':');
            c.accept(sb,t);
        };
    }
}
