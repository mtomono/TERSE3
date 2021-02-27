/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import function.Order;

/**
 *
 * @author masao
 */
public interface ContextComparableBuilder<K extends Comparable<? super K>, CONTEXT extends Comparable<? super CONTEXT>&Context<K,CONTEXT>> extends ContextBuilder<K,CONTEXT>{
    Order<CONTEXT> order();
}
