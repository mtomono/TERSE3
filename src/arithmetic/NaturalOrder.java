/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetic;

/**
 *
 * @author masao
 */
public class NaturalOrder {
    static public NaturalOrder order=new NaturalOrder();
    public boolean eq(double c1, double c2) { return c1 == c2; }
    public boolean eq(long   c1, long   c2) { return c1 == c2; }
    public boolean eq(int    c1, int    c2) { return c1 == c2; }

    public boolean lt(double c1, double c2) { return c1 < c2; }
    public boolean lt(long   c1, long   c2) { return c1 < c2; }
    public boolean lt(int    c1, int    c2) { return c1 < c2; }
    public boolean ne(double c1, double c2) { return !eq(c1, c2); }
    public boolean ne(long   c1, long   c2) { return !eq(c1, c2); }
    public boolean ne(int    c1, int    c2) { return !eq(c1, c2); }
    
    public boolean gt(double c1, double c2) { return  lt(c2, c1); }
    public boolean gt(long   c1, long   c2) { return  lt(c2, c1); }
    public boolean gt(int    c1, int    c2) { return  lt(c2, c1); }

    public boolean ge(double c1, double c2) { return !lt(c1, c2); }
    public boolean ge(long   c1, long   c2) { return !lt(c1, c2); }
    public boolean ge(int    c1, int    c2) { return !lt(c1, c2); }

    public boolean le(double c1, double c2) { return !gt(c1, c2); }
    public boolean le(long   c1, long   c2) { return !gt(c1, c2); }
    public boolean le(int    c1, int    c2) { return !gt(c1, c2); }
}
