/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.lp;

import collection.TList;

/**
 *
 * @author masao
 */
public class StandardForm<K> {
    TList<K> objectiveFunction; //minimize this.
    TList<TList<K>> constraintsLeft;
    TList<K> constraintsRight;
}
