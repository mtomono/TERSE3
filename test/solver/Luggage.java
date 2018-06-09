/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

/**
 *
 * @author masao
 */
public class Luggage {
    int weight;
    int value;
    public Luggage(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    
    public int weight() {
        return weight;
    }
    
    public int value() {
        return value;
    }
    
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Luggage))
            return false;
        Luggage t = (Luggage) e;
        return t.weight==weight&&t.value==value;
    }
    
    @Override
    public String toString() {
        return "weight:"+weight+" value:"+value;
    }
}
