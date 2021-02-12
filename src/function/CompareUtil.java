/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.function.Function;

/**
 *
 * @author masao
 */
public class CompareUtil {
    static public <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b)<0?a:b;
    }
    static public <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b)>0?a:b;
    }
    static public <T extends Comparable<T>> boolean eq(T a, T b) {
        return a.compareTo(b)==0;
    }
    static public <T extends Comparable<T>> boolean ne(T a, T b) {
        return a.compareTo(b)!=0;
    }
    static public <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b)>0;
    }
    static public <T extends Comparable<T>> boolean ge(T a,T b) {
        return a.compareTo(b)>=0;
    }
    static public <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b)<0;
    }
    static public <T extends Comparable<T>> boolean le(T a,T b) {
        return a.compareTo(b)<=0;
    }
    static public <S,T extends Comparable<T>> map<S,T> map(Function<S,T> f) {
        return new map(f);
    }
    static public class map<S,T extends Comparable<T>> {
        Function<S,T> f;
        map(Function<S,T> f) {
            this.f=f;
        }
        public boolean eq(S a, S b) {
            return CompareUtil.eq(f.apply(a),f.apply(b));
        }
        public boolean ne(S a, S b) {
            return CompareUtil.ne(f.apply(a),f.apply(b));
        }
        public boolean gt(S a, S b) {
            return CompareUtil.gt(f.apply(a),f.apply(b));
        }
        public boolean ge(S a, S b) {
            return CompareUtil.ge(f.apply(a),f.apply(b));
        }
        public boolean lt(S a, S b) {
            return CompareUtil.lt(f.apply(a),f.apply(b));
        }
        public boolean le(S a, S b) {
            return CompareUtil.le(f.apply(a),f.apply(b));
        }
    }
}
